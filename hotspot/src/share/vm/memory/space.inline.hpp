#ifndef SHARE_VM_MEMORY_SPACE_INLINE_HPP
#define SHARE_VM_MEMORY_SPACE_INLINE_HPP

#include "gc_interface/collectedHeap.hpp"
#include "memory/space.hpp"
#include "memory/universe.hpp"
#include "runtime/safepoint.hpp"

inline HeapWord* Space::block_start(const void* p) {
  return block_start_const(p);
}

inline HeapWord* OffsetTableContigSpace::allocate(size_t size) {
  HeapWord* res = ContiguousSpace::allocate(size);
  if (res != NULL) {
    _offsets.alloc_block(res, size);
  }
  return res;
}

// Because of the requirement of keeping "_offsets" up to date with the
// allocations, we sequentialize these with a lock.  Therefore, best if
// this is used for larger LAB allocations only.
inline HeapWord* OffsetTableContigSpace::par_allocate(size_t size) {
  MutexLocker x(&_par_alloc_lock);
  // This ought to be just "allocate", because of the lock above, but that
  // ContiguousSpace::allocate asserts that either the allocating thread
  // holds the heap lock or it is the VM thread and we're at a safepoint.
  // The best I (dld) could figure was to put a field in ContiguousSpace
  // meaning "locking at safepoint taken care of", and set/reset that
  // here.  But this will do for now, especially in light of the comment
  // above.  Perhaps in the future some lock-free manner of keeping the
  // coordination.
  HeapWord* res = ContiguousSpace::par_allocate(size);
  if (res != NULL) {
    _offsets.alloc_block(res, size);
  }
  return res;
}

inline HeapWord*
OffsetTableContigSpace::block_start_const(const void* p) const {
  return _offsets.block_start(p);
}

#endif // SHARE_VM_MEMORY_SPACE_INLINE_HPP
