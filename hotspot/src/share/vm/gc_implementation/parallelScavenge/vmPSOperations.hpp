#ifndef SHARE_VM_GC_IMPLEMENTATION_PARALLELSCAVENGE_VMPSOPERATIONS_HPP
#define SHARE_VM_GC_IMPLEMENTATION_PARALLELSCAVENGE_VMPSOPERATIONS_HPP

#include "gc_implementation/parallelScavenge/parallelScavengeHeap.hpp"
#include "gc_implementation/shared/vmGCOperations.hpp"
#include "gc_interface/gcCause.hpp"

class VM_ParallelGCFailedAllocation: public VM_GC_Operation {
 private:
  size_t    _size;
  HeapWord* _result;

 public:
  VM_ParallelGCFailedAllocation(size_t size, unsigned int gc_count);

  virtual VMOp_Type type() const {
    return VMOp_ParallelGCFailedAllocation;
  }
  virtual void doit();

  HeapWord* result() const       { return _result; }
};

class VM_ParallelGCSystemGC: public VM_GC_Operation {
 public:
  VM_ParallelGCSystemGC(unsigned int gc_count, unsigned int full_gc_count,
                        GCCause::Cause gc_cause);
  virtual VMOp_Type type() const { return VMOp_ParallelGCSystemGC; }
  virtual void doit();
};

#endif // SHARE_VM_GC_IMPLEMENTATION_PARALLELSCAVENGE_VMPSOPERATIONS_HPP
