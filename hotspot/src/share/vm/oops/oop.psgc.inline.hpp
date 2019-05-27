#ifndef SHARE_VM_OOPS_OOP_PSGC_INLINE_HPP
#define SHARE_VM_OOPS_OOP_PSGC_INLINE_HPP

#include "utilities/macros.hpp"
#if INCLUDE_ALL_GCS
#include "gc_implementation/parallelScavenge/parallelScavengeHeap.hpp"
#include "gc_implementation/parallelScavenge/psScavenge.hpp"
#include "gc_implementation/parallelScavenge/psScavenge.inline.hpp"
#endif // INCLUDE_ALL_GCS

// ParallelScavengeHeap methods

inline void oopDesc::push_contents(PSPromotionManager* pm) {
  Klass* k = klass();
  if (!k->oop_is_typeArray()) {
    // It might contain oops beyond the header, so take the virtual call.
    k->oop_push_contents(pm, this);
  }
  // Else skip it.  The TypeArrayKlass in the header never needs scavenging.
}

#endif // SHARE_VM_OOPS_OOP_PSGC_INLINE_HPP
