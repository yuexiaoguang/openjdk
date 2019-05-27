
// no precompiled headers
#include "runtime/atomic.hpp"
#include "runtime/handles.inline.hpp"
#include "runtime/mutexLocker.hpp"
#include "runtime/os.hpp"
#include "runtime/osThread.hpp"
#include "runtime/safepoint.hpp"
#include "runtime/vmThread.hpp"

#include <signal.h>

 // ***************************************************************
 // Platform dependent initialization and cleanup
 // ***************************************************************

void OSThread::pd_initialize() {
  _thread_id                         = 0;
  sigemptyset(&_caller_sigmask);

  _saved_interrupt_thread_state      = _thread_new;
  _vm_created_thread                 = false;
}

void OSThread::pd_destroy() {
}

// copied from synchronizer.cpp

void OSThread::handle_spinlock_contention(int tries) {
  if (NoYieldsInMicrolock) return;

  if (tries > 10) {
    os::yield_all(tries); // Yield to threads of any priority
  } else if (tries > 5) {
    os::yield();          // Yield to threads of same or higher priority
  }
}

void OSThread::SR_handler(Thread* thread, ucontext_t* uc) {
  os::Solaris::SR_handler(thread, uc);
}
