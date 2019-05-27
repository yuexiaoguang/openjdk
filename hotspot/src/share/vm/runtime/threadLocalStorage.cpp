#include "precompiled.hpp"
#include "runtime/thread.inline.hpp"
#include "runtime/threadLocalStorage.hpp"
#ifdef TARGET_OS_FAMILY_linux
# include "os_linux.inline.hpp"
#endif
#ifdef TARGET_OS_FAMILY_solaris
# include "os_solaris.inline.hpp"
#endif
#ifdef TARGET_OS_FAMILY_windows
# include "os_windows.inline.hpp"
#endif
#ifdef TARGET_OS_FAMILY_bsd
# include "os_bsd.inline.hpp"
#endif

// static member initialization
int ThreadLocalStorage::_thread_index = -1;

Thread* ThreadLocalStorage::get_thread_slow() {
  return (Thread*) os::thread_local_storage_at(ThreadLocalStorage::thread_index());
}

void ThreadLocalStorage::set_thread(Thread* thread) {
  pd_set_thread(thread);

  // The following ensure that any optimization tricks we have tried
  // did not backfire on us:
  guarantee(get_thread()      == thread, "must be the same thread, quickly");
  guarantee(get_thread_slow() == thread, "must be the same thread, slowly");
}

void ThreadLocalStorage::init() {
  assert(!is_initialized(),
         "More than one attempt to initialize threadLocalStorage");
  pd_init();
  set_thread_index(os::allocate_thread_local_storage());
  generate_code_for_get_thread();
}

bool ThreadLocalStorage::is_initialized() {
    return (thread_index() != -1);
}
