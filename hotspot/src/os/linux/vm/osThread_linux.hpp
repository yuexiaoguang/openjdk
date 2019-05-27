
#ifndef OS_LINUX_VM_OSTHREAD_LINUX_HPP
#define OS_LINUX_VM_OSTHREAD_LINUX_HPP
 public:
  typedef pid_t thread_id_t;

 private:
  int _thread_type;

 public:

  int thread_type() const {
    return _thread_type;
  }
  void set_thread_type(int type) {
    _thread_type = type;
  }

  // _pthread_id is the pthread id, which is used by library calls
  // (e.g. pthread_kill).
  pthread_t _pthread_id;

  sigset_t _caller_sigmask; // Caller's signal mask

 public:

  // Methods to save/restore caller's signal mask
  sigset_t  caller_sigmask() const       { return _caller_sigmask; }
  void    set_caller_sigmask(sigset_t sigmask)  { _caller_sigmask = sigmask; }

#ifndef PRODUCT
  // Used for debugging, return a unique integer for each thread.
  int thread_identifier() const   { return _thread_id; }
#endif
#ifdef ASSERT
  // We expect no reposition failures so kill vm if we get one.
  //
  bool valid_reposition_failure() {
    return false;
  }
#endif // ASSERT
  pthread_t pthread_id() const {
    return _pthread_id;
  }
  void set_pthread_id(pthread_t tid) {
    _pthread_id = tid;
  }

  // ***************************************************************
  // suspension support.
  // ***************************************************************

public:
  // flags that support signal based suspend/resume on Linux are in a
  // separate class to avoid confusion with many flags in OSThread that
  // are used by VM level suspend/resume.
  os::SuspendResume sr;

  // _ucontext and _siginfo are used by SR_handler() to save thread context,
  // and they will later be used to walk the stack or reposition thread PC.
  // If the thread is not suspended in SR_handler() (e.g. self suspend),
  // the value in _ucontext is meaningless, so we must use the last Java
  // frame information as the frame. This will mean that for threads
  // that are parked on a mutex the profiler (and safepoint mechanism)
  // will see the thread as if it were still in the Java frame. This
  // not a problem for the profiler since the Java frame is a close
  // enough result. For the safepoint mechanism when the give it the
  // Java frame we are not at a point where the safepoint needs the
  // frame to that accurate (like for a compiled safepoint) since we
  // should be in a place where we are native and will block ourselves
  // if we transition.
private:
  void* _siginfo;
  ucontext_t* _ucontext;
  int _expanding_stack;                 /* non zero if manually expanding stack */
  address _alt_sig_stack;               /* address of base of alternate signal stack */

public:
  void* siginfo() const                   { return _siginfo;  }
  void set_siginfo(void* ptr)             { _siginfo = ptr;   }
  ucontext_t* ucontext() const            { return _ucontext; }
  void set_ucontext(ucontext_t* ptr)      { _ucontext = ptr;  }
  void set_expanding_stack(void)          { _expanding_stack = 1;  }
  void clear_expanding_stack(void)        { _expanding_stack = 0;  }
  int  expanding_stack(void)              { return _expanding_stack;  }

  void set_alt_sig_stack(address val)     { _alt_sig_stack = val; }
  address alt_sig_stack(void)             { return _alt_sig_stack; }

private:
  Monitor* _startThread_lock;     // sync parent and child in thread creation

public:

  Monitor* startThread_lock() const {
    return _startThread_lock;
  }

  // ***************************************************************
  // Platform dependent initialization and cleanup
  // ***************************************************************

private:

  void pd_initialize();
  void pd_destroy();

// Reconciliation History
// osThread_solaris.hpp 1.24 99/08/27 13:11:54
// End

#endif // OS_LINUX_VM_OSTHREAD_LINUX_HPP
