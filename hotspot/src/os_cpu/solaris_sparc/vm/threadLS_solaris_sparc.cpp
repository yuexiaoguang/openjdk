
#include "precompiled.hpp"
#include "runtime/thread.inline.hpp"
#include "runtime/threadLocalStorage.hpp"

// Provides an entry point we can link against and
// a buffer we can emit code into. The buffer is
// filled by ThreadLocalStorage::generate_code_for_get_thread
// and called from ThreadLocalStorage::thread()

#include <sys/systeminfo.h>

// The portable TLS mechanism (get_thread_via_cache) is enough on SPARC.
// There is no need for hand-assembling a special function.
void ThreadLocalStorage::generate_code_for_get_thread() {
}

void ThreadLocalStorage::set_thread_in_slot (Thread * self) {}

extern "C" Thread* get_thread() {
  return ThreadLocalStorage::thread();
}
