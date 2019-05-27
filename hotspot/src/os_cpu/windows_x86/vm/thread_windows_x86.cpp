
#include "precompiled.hpp"
#include "runtime/frame.inline.hpp"
#include "runtime/thread.inline.hpp"

// For Forte Analyzer AsyncGetCallTrace profiling support - thread is
// currently interrupted by SIGPROF
bool JavaThread::pd_get_top_frame_for_signal_handler(frame* fr_addr,
  void* ucontext, bool isInJava) {

  assert(Thread::current() == this, "caller must be current thread");
  return pd_get_top_frame(fr_addr, ucontext, isInJava);
}

bool JavaThread::pd_get_top_frame_for_profiling(frame* fr_addr, void* ucontext, bool isInJava) {
  return pd_get_top_frame(fr_addr, ucontext, isInJava);
}

bool JavaThread::pd_get_top_frame(frame* fr_addr, void* ucontext, bool isInJava) {

  assert(this->is_Java_thread(), "must be JavaThread");

  JavaThread* jt = (JavaThread *)this;

  // If we have a last_Java_frame, then we should use it even if
  // isInJava == true.  It should be more reliable than CONTEXT info.
  if (jt->has_last_Java_frame()) {
    *fr_addr = jt->pd_last_frame();
    return true;
  }

  // At this point, we don't have a last_Java_frame, so
  // we try to glean some information out of the CONTEXT
  // if we were running Java code when SIGPROF came in.
  if (isInJava) {
    CONTEXT* uc = (CONTEXT*)ucontext;

#ifdef AMD64
    intptr_t* ret_fp = (intptr_t*) uc->Rbp;
    intptr_t* ret_sp = (intptr_t*) uc->Rsp;
    ExtendedPC addr = ExtendedPC((address)uc->Rip);
#else
    intptr_t* ret_fp = (intptr_t*) uc->Ebp;
    intptr_t* ret_sp = (intptr_t*) uc->Esp;
    ExtendedPC addr = ExtendedPC((address)uc->Eip);
#endif // AMD64
    if (addr.pc() == NULL || ret_sp == NULL ) {
      // CONTEXT wasn't useful
      return false;
    }

    frame ret_frame(ret_sp, ret_fp, addr.pc());
    if (!ret_frame.safe_for_sender(jt)) {
#ifdef COMPILER2
      // C2 uses ebp as a general register see if NULL fp helps
      frame ret_frame2(ret_sp, NULL, addr.pc());
      if (!ret_frame2.safe_for_sender(jt)) {
        // nothing else to try if the frame isn't good
        return false;
      }
      ret_frame = ret_frame2;
#else
      // nothing else to try if the frame isn't good
      return false;
#endif /* COMPILER2 */
    }
    *fr_addr = ret_frame;
    return true;
  }

  // nothing else to try
  return false;
}

void JavaThread::cache_global_variables() { }
