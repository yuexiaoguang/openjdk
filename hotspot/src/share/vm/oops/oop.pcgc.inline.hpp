#ifndef SHARE_VM_OOPS_OOP_PCGC_INLINE_HPP
#define SHARE_VM_OOPS_OOP_PCGC_INLINE_HPP

#include "utilities/macros.hpp"
#if INCLUDE_ALL_GCS
#include "gc_implementation/parNew/parNewGeneration.hpp"
#include "gc_implementation/parallelScavenge/parallelScavengeHeap.hpp"
#include "gc_implementation/parallelScavenge/psCompactionManager.hpp"
#include "gc_implementation/parallelScavenge/psParallelCompact.hpp"
#include "gc_implementation/parallelScavenge/psScavenge.hpp"
#include "gc_implementation/parallelScavenge/psScavenge.inline.hpp"
#endif // INCLUDE_ALL_GCS

inline void oopDesc::update_contents(ParCompactionManager* cm) {
  // The klass field must be updated before anything else
  // can be done.
  DEBUG_ONLY(Klass* original_klass = klass());

  Klass* new_klass = klass();
  if (!new_klass->oop_is_typeArray()) {
    // It might contain oops beyond the header, so take the virtual call.
    new_klass->oop_update_pointers(cm, this);
  }
  // Else skip it.  The TypeArrayKlass in the header never needs scavenging.
}

inline void oopDesc::follow_contents(ParCompactionManager* cm) {
  assert (PSParallelCompact::mark_bitmap()->is_marked(this),
    "should be marked");
  klass()->oop_follow_contents(cm, this);
}

// Used by parallel old GC.

inline oop oopDesc::forward_to_atomic(oop p) {
  assert(ParNewGeneration::is_legal_forward_ptr(p),
         "illegal forwarding pointer value.");
  markOop oldMark = mark();
  markOop forwardPtrMark = markOopDesc::encode_pointer_as_mark(p);
  markOop curMark;

  assert(forwardPtrMark->decode_pointer() == p, "encoding must be reversable");
  assert(sizeof(markOop) == sizeof(intptr_t), "CAS below requires this.");

  while (!oldMark->is_marked()) {
    curMark = (markOop)Atomic::cmpxchg_ptr(forwardPtrMark, &_mark, oldMark);
    assert(is_forwarded(), "object should have been forwarded");
    if (curMark == oldMark) {
      return NULL;
    }
    // If the CAS was unsuccessful then curMark->is_marked()
    // should return true as another thread has CAS'd in another
    // forwarding pointer.
    oldMark = curMark;
  }
  return forwardee();
}

#endif // SHARE_VM_OOPS_OOP_PCGC_INLINE_HPP
