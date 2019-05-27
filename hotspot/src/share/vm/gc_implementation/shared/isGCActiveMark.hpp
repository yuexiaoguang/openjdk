#ifndef SHARE_VM_GC_IMPLEMENTATION_SHARED_ISGCACTIVEMARK_HPP
#define SHARE_VM_GC_IMPLEMENTATION_SHARED_ISGCACTIVEMARK_HPP

#include "utilities/macros.hpp"
#if INCLUDE_ALL_GCS
#include "gc_implementation/parallelScavenge/parallelScavengeHeap.hpp"
#endif // INCLUDE_ALL_GCS

// This class provides a method for block structured setting of the
// _is_gc_active state without requiring accessors in CollectedHeap

class IsGCActiveMark : public StackObj {
 public:
  IsGCActiveMark() {
    CollectedHeap* heap = Universe::heap();
    assert(!heap->is_gc_active(), "Not reentrant");
    heap->_is_gc_active = true;
  }

  ~IsGCActiveMark() {
    CollectedHeap* heap = Universe::heap();
    assert(heap->is_gc_active(), "Sanity");
    heap->_is_gc_active = false;
  }
};

#endif // SHARE_VM_GC_IMPLEMENTATION_SHARED_ISGCACTIVEMARK_HPP
