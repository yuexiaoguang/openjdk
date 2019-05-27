#ifndef SHARE_VM_GC_IMPLEMENTATION_PARALLELSCAVENGE_PARALLELSCAVENGEHEAP_INLINE_HPP
#define SHARE_VM_GC_IMPLEMENTATION_PARALLELSCAVENGE_PARALLELSCAVENGEHEAP_INLINE_HPP

#include "gc_implementation/parallelScavenge/parallelScavengeHeap.hpp"
#include "gc_implementation/parallelScavenge/psMarkSweep.hpp"
#include "gc_implementation/parallelScavenge/psParallelCompact.hpp"
#include "gc_implementation/parallelScavenge/psScavenge.hpp"

inline size_t ParallelScavengeHeap::total_invocations()
{
  return UseParallelOldGC ? PSParallelCompact::total_invocations() :
    PSMarkSweep::total_invocations();
}

inline bool ParallelScavengeHeap::should_alloc_in_eden(const size_t size) const
{
  const size_t eden_size = young_gen()->eden_space()->capacity_in_words();
  return size < eden_size / 2;
}

inline void ParallelScavengeHeap::invoke_scavenge()
{
  PSScavenge::invoke();
}

inline bool ParallelScavengeHeap::is_in_young(oop p) {
  // Assumes the the old gen address range is lower than that of the young gen.
  const void* loc = (void*) p;
  bool result = ((HeapWord*)p) >= young_gen()->reserved().start();
  assert(result == young_gen()->is_in_reserved(p),
        err_msg("incorrect test - result=%d, p=" PTR_FORMAT, result, (void*)p));
  return result;
}
#endif // SHARE_VM_GC_IMPLEMENTATION_PARALLELSCAVENGE_PARALLELSCAVENGEHEAP_INLINE_HPP
