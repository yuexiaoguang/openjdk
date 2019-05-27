#ifndef SHARE_VM_GC_IMPLEMENTATION_G1_G1REMSET_INLINE_HPP
#define SHARE_VM_GC_IMPLEMENTATION_G1_G1REMSET_INLINE_HPP

#include "gc_implementation/g1/g1RemSet.hpp"
#include "gc_implementation/g1/heapRegionRemSet.hpp"
#include "oops/oop.inline.hpp"

inline uint G1RemSet::n_workers() {
  if (_g1->workers() != NULL) {
    return _g1->workers()->total_workers();
  } else {
    return 1;
  }
}

template <class T>
inline void G1RemSet::write_ref(HeapRegion* from, T* p) {
  par_write_ref(from, p, 0);
}

template <class T>
inline void G1RemSet::par_write_ref(HeapRegion* from, T* p, int tid) {
  oop obj = oopDesc::load_decode_heap_oop(p);
#ifdef ASSERT
  // can't do because of races
  // assert(obj == NULL || obj->is_oop(), "expected an oop");

  // Do the safe subset of is_oop
  if (obj != NULL) {
#ifdef CHECK_UNHANDLED_OOPS
    oopDesc* o = obj.obj();
#else
    oopDesc* o = obj;
#endif // CHECK_UNHANDLED_OOPS
    assert((intptr_t)o % MinObjAlignmentInBytes == 0, "not oop aligned");
    assert(Universe::heap()->is_in_reserved(obj), "must be in heap");
  }
#endif // ASSERT

  assert(from == NULL || from->is_in_reserved(p), "p is not in from");

  HeapRegion* to = _g1->heap_region_containing(obj);
  if (to != NULL && from != to) {
    assert(to->rem_set() != NULL, "Need per-region 'into' remsets.");
    to->rem_set()->add_reference(p, tid);
  }
}

template <class T>
inline void UpdateRSOopClosure::do_oop_work(T* p) {
  assert(_from != NULL, "from region must be non-NULL");
  _rs->par_write_ref(_from, p, _worker_i);
}

template <class T>
inline void UpdateRSetImmediate::do_oop_work(T* p) {
  assert(_from->is_in_reserved(p), "paranoia");
  T heap_oop = oopDesc::load_heap_oop(p);
  if (!oopDesc::is_null(heap_oop) && !_from->is_survivor()) {
    _g1_rem_set->par_write_ref(_from, p, 0);
  }
}

#endif // SHARE_VM_GC_IMPLEMENTATION_G1_G1REMSET_INLINE_HPP
