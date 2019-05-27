#ifndef SHARE_VM_OOPS_OBJARRAYKLASS_INLINE_HPP
#define SHARE_VM_OOPS_OBJARRAYKLASS_INLINE_HPP

#include "gc_implementation/shared/markSweep.inline.hpp"
#include "oops/objArrayKlass.hpp"
#include "utilities/macros.hpp"
#if INCLUDE_ALL_GCS
#include "gc_implementation/parallelScavenge/psCompactionManager.inline.hpp"
#include "gc_implementation/parallelScavenge/psParallelCompact.hpp"
#endif // INCLUDE_ALL_GCS

void ObjArrayKlass::oop_follow_contents(oop obj, int index) {
  if (UseCompressedOops) {
    objarray_follow_contents<narrowOop>(obj, index);
  } else {
    objarray_follow_contents<oop>(obj, index);
  }
}

template <class T>
void ObjArrayKlass::objarray_follow_contents(oop obj, int index) {
  objArrayOop a = objArrayOop(obj);
  const size_t len = size_t(a->length());
  const size_t beg_index = size_t(index);
  assert(beg_index < len || len == 0, "index too large");

  const size_t stride = MIN2(len - beg_index, ObjArrayMarkingStride);
  const size_t end_index = beg_index + stride;
  T* const base = (T*)a->base();
  T* const beg = base + beg_index;
  T* const end = base + end_index;

  // Push the non-NULL elements of the next stride on the marking stack.
  for (T* e = beg; e < end; e++) {
    MarkSweep::mark_and_push<T>(e);
  }

  if (end_index < len) {
    MarkSweep::push_objarray(a, end_index); // Push the continuation.
  }
}

#if INCLUDE_ALL_GCS
void ObjArrayKlass::oop_follow_contents(ParCompactionManager* cm, oop obj,
                                        int index) {
  if (UseCompressedOops) {
    objarray_follow_contents<narrowOop>(cm, obj, index);
  } else {
    objarray_follow_contents<oop>(cm, obj, index);
  }
}

template <class T>
void ObjArrayKlass::objarray_follow_contents(ParCompactionManager* cm, oop obj,
                                             int index) {
  objArrayOop a = objArrayOop(obj);
  const size_t len = size_t(a->length());
  const size_t beg_index = size_t(index);
  assert(beg_index < len || len == 0, "index too large");

  const size_t stride = MIN2(len - beg_index, ObjArrayMarkingStride);
  const size_t end_index = beg_index + stride;
  T* const base = (T*)a->base();
  T* const beg = base + beg_index;
  T* const end = base + end_index;

  // Push the non-NULL elements of the next stride on the marking stack.
  for (T* e = beg; e < end; e++) {
    PSParallelCompact::mark_and_push<T>(cm, e);
  }

  if (end_index < len) {
    cm->push_objarray(a, end_index); // Push the continuation.
  }
}
#endif // INCLUDE_ALL_GCS

#endif // SHARE_VM_OOPS_OBJARRAYKLASS_INLINE_HPP
