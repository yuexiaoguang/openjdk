#include "precompiled.hpp"
#include "gc_interface/collectedHeap.hpp"
#include "memory/barrierSet.inline.hpp"
#include "memory/universe.hpp"

// count is number of array elements being written
void BarrierSet::static_write_ref_array_pre(HeapWord* start, size_t count) {
  assert(count <= (size_t)max_intx, "count too large");
#if 0
  warning("Pre: \t" INTPTR_FORMAT "[" SIZE_FORMAT "]\t",
                   start,            count);
#endif
  if (UseCompressedOops) {
    Universe::heap()->barrier_set()->write_ref_array_pre((narrowOop*)start, (int)count, false);
  } else {
    Universe::heap()->barrier_set()->write_ref_array_pre(      (oop*)start, (int)count, false);
  }
}

// count is number of array elements being written
void BarrierSet::static_write_ref_array_post(HeapWord* start, size_t count) {
  // simply delegate to instance method
  Universe::heap()->barrier_set()->write_ref_array(start, count);
}
