#include "precompiled.hpp"
#include "gc_implementation/parallelScavenge/parallelScavengeHeap.inline.hpp"
#include "gc_implementation/parallelScavenge/psMarkSweep.hpp"
#include "gc_implementation/parallelScavenge/psScavenge.hpp"
#include "gc_implementation/parallelScavenge/psScavenge.inline.hpp"
#include "gc_implementation/parallelScavenge/vmPSOperations.hpp"
#include "memory/gcLocker.inline.hpp"
#include "utilities/dtrace.hpp"

// The following methods are used by the parallel scavenge collector
VM_ParallelGCFailedAllocation::VM_ParallelGCFailedAllocation(size_t size,
                                                      unsigned int gc_count) :
  VM_GC_Operation(gc_count, GCCause::_allocation_failure),
  _size(size),
  _result(NULL)
{
}

void VM_ParallelGCFailedAllocation::doit() {
  SvcGCMarker sgcm(SvcGCMarker::MINOR);

  ParallelScavengeHeap* heap = (ParallelScavengeHeap*)Universe::heap();
  assert(heap->kind() == CollectedHeap::ParallelScavengeHeap, "must be a ParallelScavengeHeap");

  GCCauseSetter gccs(heap, _gc_cause);
  _result = heap->failed_mem_allocate(_size);

  if (_result == NULL && GC_locker::is_active_and_needs_gc()) {
    set_gc_locked();
  }
}

// Only used for System.gc() calls
VM_ParallelGCSystemGC::VM_ParallelGCSystemGC(unsigned int gc_count,
                                             unsigned int full_gc_count,
                                             GCCause::Cause gc_cause) :
  VM_GC_Operation(gc_count, gc_cause, full_gc_count, true /* full */)
{
}

void VM_ParallelGCSystemGC::doit() {
  SvcGCMarker sgcm(SvcGCMarker::FULL);

  ParallelScavengeHeap* heap = (ParallelScavengeHeap*)Universe::heap();
  assert(heap->kind() == CollectedHeap::ParallelScavengeHeap,
    "must be a ParallelScavengeHeap");

  GCCauseSetter gccs(heap, _gc_cause);
  if (_gc_cause == GCCause::_gc_locker
      DEBUG_ONLY(|| _gc_cause == GCCause::_scavenge_alot)) {
    // If (and only if) the scavenge fails, this will invoke a full gc.
    heap->invoke_scavenge();
  } else {
    heap->do_full_collection(false);
  }
}
