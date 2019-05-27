#ifndef SHARE_VM_GC_IMPLEMENTATION_G1_G1ALLOCREGION_INLINE_HPP
#define SHARE_VM_GC_IMPLEMENTATION_G1_G1ALLOCREGION_INLINE_HPP

#include "gc_implementation/g1/g1AllocRegion.hpp"

inline HeapWord* G1AllocRegion::allocate(HeapRegion* alloc_region,
                                         size_t word_size,
                                         bool bot_updates) {
  assert(alloc_region != NULL, err_msg("pre-condition"));

  if (!bot_updates) {
    return alloc_region->allocate_no_bot_updates(word_size);
  } else {
    return alloc_region->allocate(word_size);
  }
}

inline HeapWord* G1AllocRegion::par_allocate(HeapRegion* alloc_region,
                                             size_t word_size,
                                             bool bot_updates) {
  assert(alloc_region != NULL, err_msg("pre-condition"));
  assert(!alloc_region->is_empty(), err_msg("pre-condition"));

  if (!bot_updates) {
    return alloc_region->par_allocate_no_bot_updates(word_size);
  } else {
    return alloc_region->par_allocate(word_size);
  }
}

inline HeapWord* G1AllocRegion::attempt_allocation(size_t word_size,
                                                   bool bot_updates) {
  assert(bot_updates == _bot_updates, ar_ext_msg(this, "pre-condition"));

  HeapRegion* alloc_region = _alloc_region;
  assert(alloc_region != NULL, ar_ext_msg(this, "not initialized properly"));

  HeapWord* result = par_allocate(alloc_region, word_size, bot_updates);
  if (result != NULL) {
    trace("alloc", word_size, result);
    return result;
  }
  trace("alloc failed", word_size);
  return NULL;
}

inline HeapWord* G1AllocRegion::attempt_allocation_locked(size_t word_size,
                                                          bool bot_updates) {
  // First we have to tedo the allocation, assuming we're holding the
  // appropriate lock, in case another thread changed the region while
  // we were waiting to get the lock.
  HeapWord* result = attempt_allocation(word_size, bot_updates);
  if (result != NULL) {
    return result;
  }

  retire(true /* fill_up */);
  result = new_alloc_region_and_allocate(word_size, false /* force */);
  if (result != NULL) {
    trace("alloc locked (second attempt)", word_size, result);
    return result;
  }
  trace("alloc locked failed", word_size);
  return NULL;
}

inline HeapWord* G1AllocRegion::attempt_allocation_force(size_t word_size,
                                                         bool bot_updates) {
  assert(bot_updates == _bot_updates, ar_ext_msg(this, "pre-condition"));
  assert(_alloc_region != NULL, ar_ext_msg(this, "not initialized properly"));

  trace("forcing alloc");
  HeapWord* result = new_alloc_region_and_allocate(word_size, true /* force */);
  if (result != NULL) {
    trace("alloc forced", word_size, result);
    return result;
  }
  trace("alloc forced failed", word_size);
  return NULL;
}

#endif // SHARE_VM_GC_IMPLEMENTATION_G1_G1ALLOCREGION_INLINE_HPP
