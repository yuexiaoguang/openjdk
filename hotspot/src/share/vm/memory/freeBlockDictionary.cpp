#include "precompiled.hpp"
#include "utilities/macros.hpp"
#if INCLUDE_ALL_GCS
#include "gc_implementation/concurrentMarkSweep/freeChunk.hpp"
#endif // INCLUDE_ALL_GCS
#include "memory/freeBlockDictionary.hpp"
#include "memory/metachunk.hpp"
#include "runtime/thread.inline.hpp"
#include "utilities/macros.hpp"

#ifndef PRODUCT
template <class Chunk> Mutex* FreeBlockDictionary<Chunk>::par_lock() const {
  return _lock;
}

template <class Chunk> void FreeBlockDictionary<Chunk>::set_par_lock(Mutex* lock) {
  _lock = lock;
}

template <class Chunk> void FreeBlockDictionary<Chunk>::verify_par_locked() const {
#ifdef ASSERT
  if (ParallelGCThreads > 0) {
    Thread* my_thread = Thread::current();
    if (my_thread->is_GC_task_thread()) {
      assert(par_lock() != NULL, "Should be using locking?");
      assert_lock_strong(par_lock());
    }
  }
#endif // ASSERT
}
#endif

template class FreeBlockDictionary<Metablock>;
template class FreeBlockDictionary<Metachunk>;

#if INCLUDE_ALL_GCS
// Explicitly instantiate for FreeChunk
template class FreeBlockDictionary<FreeChunk>;
#endif // INCLUDE_ALL_GCS
