#ifndef SHARE_VM_RUNTIME_THREADLOCALSTORAGE_HPP
#define SHARE_VM_RUNTIME_THREADLOCALSTORAGE_HPP

#include "gc_implementation/shared/gcUtil.hpp"
#include "runtime/os.hpp"
#include "utilities/top.hpp"

// Interface for thread local storage

// Fast variant of ThreadLocalStorage::get_thread_slow
extern "C" Thread*   get_thread();

// Get raw thread id: e.g., %g7 on sparc, fs or gs on x86
extern "C" uintptr_t _raw_thread_id();

class ThreadLocalStorage : AllStatic {
 public:
  static void    set_thread(Thread* thread);
  static Thread* get_thread_slow();
  static void    invalidate_all() { pd_invalidate_all(); }

  // Machine dependent stuff
#ifdef TARGET_OS_ARCH_linux_x86
# include "threadLS_linux_x86.hpp"
#endif
#ifdef TARGET_OS_ARCH_linux_sparc
# include "threadLS_linux_sparc.hpp"
#endif
#ifdef TARGET_OS_ARCH_linux_zero
# include "threadLS_linux_zero.hpp"
#endif
#ifdef TARGET_OS_ARCH_solaris_x86
# include "threadLS_solaris_x86.hpp"
#endif
#ifdef TARGET_OS_ARCH_solaris_sparc
# include "threadLS_solaris_sparc.hpp"
#endif
#ifdef TARGET_OS_ARCH_windows_x86
# include "threadLS_windows_x86.hpp"
#endif
#ifdef TARGET_OS_ARCH_linux_arm
# include "threadLS_linux_arm.hpp"
#endif
#ifdef TARGET_OS_ARCH_linux_ppc
# include "threadLS_linux_ppc.hpp"
#endif
#ifdef TARGET_OS_ARCH_bsd_x86
# include "threadLS_bsd_x86.hpp"
#endif
#ifdef TARGET_OS_ARCH_bsd_zero
# include "threadLS_bsd_zero.hpp"
#endif


 public:
  // Accessor
  static inline int  thread_index()              { return _thread_index; }
  static inline void set_thread_index(int index) { _thread_index = index; }

  // Initialization
  // Called explicitly from VMThread::activate_system instead of init_globals.
  static void init();
  static bool is_initialized();

 private:
  static int     _thread_index;

  static void    generate_code_for_get_thread();

  // Processor dependent parts of set_thread and initialization
  static void pd_set_thread(Thread* thread);
  static void pd_init();
  // Invalidate any thread cacheing or optimization schemes.
  static void pd_invalidate_all();

};

#endif // SHARE_VM_RUNTIME_THREADLOCALSTORAGE_HPP
