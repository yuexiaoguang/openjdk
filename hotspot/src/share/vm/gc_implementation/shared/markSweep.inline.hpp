#ifndef SHARE_VM_GC_IMPLEMENTATION_SHARED_MARKSWEEP_INLINE_HPP
#define SHARE_VM_GC_IMPLEMENTATION_SHARED_MARKSWEEP_INLINE_HPP

#include "gc_implementation/shared/markSweep.hpp"
#include "gc_interface/collectedHeap.hpp"
#include "utilities/stack.inline.hpp"
#include "utilities/macros.hpp"
#if INCLUDE_ALL_GCS
#include "gc_implementation/parallelScavenge/psParallelCompact.hpp"
#endif // INCLUDE_ALL_GCS

inline void MarkSweep::mark_object(oop obj) {
  // some marks may contain information we need to preserve so we store them away
  // and overwrite the mark.  We'll restore it at the end of markSweep.
  markOop mark = obj->mark();
  obj->set_mark(markOopDesc::prototype()->set_marked());

  if (mark->must_be_preserved(obj)) {
    preserve_mark(obj, mark);
  }
}

inline void MarkSweep::follow_klass(Klass* klass) {
  oop op = klass->klass_holder();
  MarkSweep::mark_and_push(&op);
}

template <class T> inline void MarkSweep::follow_root(T* p) {
  assert(!Universe::heap()->is_in_reserved(p),
         "roots shouldn't be things within the heap");
  T heap_oop = oopDesc::load_heap_oop(p);
  if (!oopDesc::is_null(heap_oop)) {
    oop obj = oopDesc::decode_heap_oop_not_null(heap_oop);
    if (!obj->mark()->is_marked()) {
      mark_object(obj);
      obj->follow_contents();
    }
  }
  follow_stack();
}

template <class T> inline void MarkSweep::mark_and_push(T* p) {
//  assert(Universe::heap()->is_in_reserved(p), "should be in object space");
  T heap_oop = oopDesc::load_heap_oop(p);
  if (!oopDesc::is_null(heap_oop)) {
    oop obj = oopDesc::decode_heap_oop_not_null(heap_oop);
    if (!obj->mark()->is_marked()) {
      mark_object(obj);
      _marking_stack.push(obj);
    }
  }
}

void MarkSweep::push_objarray(oop obj, size_t index) {
  ObjArrayTask task(obj, index);
  assert(task.is_valid(), "bad ObjArrayTask");
  _objarray_stack.push(task);
}

template <class T> inline void MarkSweep::adjust_pointer(T* p) {
  T heap_oop = oopDesc::load_heap_oop(p);
  if (!oopDesc::is_null(heap_oop)) {
    oop obj     = oopDesc::decode_heap_oop_not_null(heap_oop);
    oop new_obj = oop(obj->mark()->decode_pointer());
    assert(new_obj != NULL ||                         // is forwarding ptr?
           obj->mark() == markOopDesc::prototype() || // not gc marked?
           (UseBiasedLocking && obj->mark()->has_bias_pattern()),
                                                      // not gc marked?
           "should be forwarded");
    if (new_obj != NULL) {
      assert(Universe::heap()->is_in_reserved(new_obj),
             "should be in object space");
      oopDesc::encode_store_heap_oop_not_null(p, new_obj);
    }
  }
}

template <class T> inline void MarkSweep::KeepAliveClosure::do_oop_work(T* p) {
  mark_and_push(p);
}

#endif // SHARE_VM_GC_IMPLEMENTATION_SHARED_MARKSWEEP_INLINE_HPP
