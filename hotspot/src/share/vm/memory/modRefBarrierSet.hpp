#ifndef SHARE_VM_MEMORY_MODREFBARRIERSET_HPP
#define SHARE_VM_MEMORY_MODREFBARRIERSET_HPP

#include "memory/barrierSet.hpp"

// This kind of "BarrierSet" allows a "CollectedHeap" to detect and
// enumerate ref fields that have been modified (since the last
// enumeration), using a card table.

class OopClosure;
class Generation;

class ModRefBarrierSet: public BarrierSet {
public:

  ModRefBarrierSet() { _kind = BarrierSet::ModRef; }

  bool is_a(BarrierSet::Name bsn) {
    return bsn == BarrierSet::ModRef;
  }

  // Barriers only on ref writes.
  bool has_read_ref_barrier() { return false; }
  bool has_read_prim_barrier() { return false; }
  bool has_write_ref_barrier() { return true; }
  bool has_write_prim_barrier() { return false; }

  bool read_ref_needs_barrier(void* field) { return false; }
  bool read_prim_needs_barrier(HeapWord* field, size_t bytes) { return false; }
  bool write_prim_needs_barrier(HeapWord* field, size_t bytes,
                                juint val1, juint val2) { return false; }

  void write_prim_field(oop obj, size_t offset, size_t bytes,
                        juint val1, juint val2) {}

  void read_ref_field(void* field) {}
  void read_prim_field(HeapWord* field, size_t bytes) {}
protected:
  virtual void write_ref_field_work(void* field, oop new_val) = 0;
public:
  void write_prim_field(HeapWord* field, size_t bytes,
                        juint val1, juint val2) {}

  bool has_read_ref_array_opt() { return false; }
  bool has_read_prim_array_opt() { return false; }
  bool has_write_prim_array_opt() { return false; }

  bool has_read_region_opt() { return false; }


  // These operations should assert false unless the correponding operation
  // above returns true.
  void read_ref_array(MemRegion mr) {
    assert(false, "can't call");
  }
  void read_prim_array(MemRegion mr) {
    assert(false, "can't call");
  }
  void write_prim_array(MemRegion mr) {
    assert(false, "can't call");
  }
  void read_region(MemRegion mr) {
    assert(false, "can't call");
  }

  // Causes all refs in "mr" to be assumed to be modified.  If "whole_heap"
  // is true, the caller asserts that the entire heap is being invalidated,
  // which may admit an optimized implementation for some barriers.
  virtual void invalidate(MemRegion mr, bool whole_heap = false) = 0;

  // The caller guarantees that "mr" contains no references.  (Perhaps it's
  // objects have been moved elsewhere.)
  virtual void clear(MemRegion mr) = 0;

  // Pass along the argument to the superclass.
  ModRefBarrierSet(int max_covered_regions) :
    BarrierSet(max_covered_regions) {}
};

#endif // SHARE_VM_MEMORY_MODREFBARRIERSET_HPP