
#ifndef OS_WINDOWS_VM_OSTHREAD_WINDOWS_HPP
#define OS_WINDOWS_VM_OSTHREAD_WINDOWS_HPP

  typedef void* HANDLE;
 public:
  typedef unsigned long thread_id_t;

 private:
  // Win32-specific thread information
  HANDLE _thread_handle;        // Win32 thread handle
  HANDLE _interrupt_event;      // Event signalled on thread interrupt
  ThreadState _last_state;

 public:
  // The following will only apply in the Win32 implementation, and should only
  // be visible in the concrete class, not this which should be an abstract base class
  HANDLE thread_handle() const                     { return _thread_handle; }
  void set_thread_handle(HANDLE handle)            { _thread_handle = handle; }
  HANDLE interrupt_event() const                   { return _interrupt_event; }
  void set_interrupt_event(HANDLE interrupt_event) { _interrupt_event = interrupt_event; }

#ifndef PRODUCT
  // Used for debugging, return a unique integer for each thread.
  int thread_identifier() const                    { return _thread_id; }
#endif
#ifdef ASSERT
  // We expect no reposition failures so kill vm if we get one
  //
  bool valid_reposition_failure() {
    return false;
  }
#endif // ASSERT
  bool is_try_mutex_enter()                        { return false; }

  // This is a temporary fix for the thread states during
  // suspend/resume until we throw away OSThread completely.
  // NEEDS_CLEANUP
  void set_last_state(ThreadState state)           { _last_state = state; }
  ThreadState get_last_state()                     { return _last_state; }

 private:
  void pd_initialize();
  void pd_destroy();

#endif // OS_WINDOWS_VM_OSTHREAD_WINDOWS_HPP
