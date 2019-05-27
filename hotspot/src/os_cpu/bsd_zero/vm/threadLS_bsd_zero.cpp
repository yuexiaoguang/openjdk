
#include "precompiled.hpp"
#include "runtime/thread.inline.hpp"
#include "runtime/threadLocalStorage.hpp"

void ThreadLocalStorage::generate_code_for_get_thread() {
  // nothing to do
}

void ThreadLocalStorage::pd_init() {
  // nothing to do
}

void ThreadLocalStorage::pd_set_thread(Thread* thread) {
  os::thread_local_storage_at_put(ThreadLocalStorage::thread_index(), thread);
}
