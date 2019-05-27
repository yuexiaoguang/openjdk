
#include "precompiled.hpp"
#include "runtime/thread.inline.hpp"
#include "runtime/threadLocalStorage.hpp"

// Provides an entry point we can link against and
// a buffer we can emit code into. The buffer is
// filled by ThreadLocalStorage::generate_code_for_get_thread
// and called from ThreadLocalStorage::thread()

int ThreadLocalStorage::_thread_ptr_offset = 0;

static void call_wrapper_dummy() {}

// We need to call the os_exception_wrapper once so that it sets
// up the offset from FS of the thread pointer.
void ThreadLocalStorage::generate_code_for_get_thread() {
      os::os_exception_wrapper( (java_call_t)call_wrapper_dummy,
                                NULL, NULL, NULL, NULL);
}

void ThreadLocalStorage::pd_init() { }

void ThreadLocalStorage::pd_set_thread(Thread* thread)  {
  os::thread_local_storage_at_put(ThreadLocalStorage::thread_index(), thread);
}
