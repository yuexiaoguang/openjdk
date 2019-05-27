#ifndef SHARE_VM_GC_IMPLEMENTATION_CONCURRENTMARKSWEEP_CMSOOPCLOSURES_INLINE_HPP
#define SHARE_VM_GC_IMPLEMENTATION_CONCURRENTMARKSWEEP_CMSOOPCLOSURES_INLINE_HPP

#include "gc_implementation/concurrentMarkSweep/cmsOopClosures.hpp"
#include "gc_implementation/concurrentMarkSweep/concurrentMarkSweepGeneration.hpp"
#include "oops/oop.inline.hpp"

// Trim our work_queue so its length is below max at return
inline void Par_MarkRefsIntoAndScanClosure::trim_queue(uint max) {
  while (_work_queue->size() > max) {
    oop newOop;
    if (_work_queue->pop_local(newOop)) {
      assert(newOop->is_oop(), "Expected an oop");
      assert(_bit_map->isMarked((HeapWord*)newOop),
             "only grey objects on this stack");
      // iterate over the oops in this oop, marking and pushing
      // the ones in CMS heap (i.e. in _span).
      newOop->oop_iterate(&_par_pushAndMarkClosure);
    }
  }
}

// CMSOopClosure and CMSoopsInGenClosure are duplicated,
// until we get rid of OopsInGenClosure.

inline void CMSOopClosure::do_klass(Klass* k)       { do_klass_nv(k); }
inline void CMSOopsInGenClosure::do_klass(Klass* k) { do_klass_nv(k); }

inline void CMSOopClosure::do_klass_nv(Klass* k) {
  ClassLoaderData* cld = k->class_loader_data();
  do_class_loader_data(cld);
}
inline void CMSOopsInGenClosure::do_klass_nv(Klass* k) {
  ClassLoaderData* cld = k->class_loader_data();
  do_class_loader_data(cld);
}

inline void CMSOopClosure::do_class_loader_data(ClassLoaderData* cld) {
  assert(_klass_closure._oop_closure == this, "Must be");

  bool claim = true;  // Must claim the class loader data before processing.
  cld->oops_do(_klass_closure._oop_closure, &_klass_closure, claim);
}
inline void CMSOopsInGenClosure::do_class_loader_data(ClassLoaderData* cld) {
  assert(_klass_closure._oop_closure == this, "Must be");

  bool claim = true;  // Must claim the class loader data before processing.
  cld->oops_do(_klass_closure._oop_closure, &_klass_closure, claim);
}


#endif // SHARE_VM_GC_IMPLEMENTATION_CONCURRENTMARKSWEEP_CMSOOPCLOSURES_INLINE_HPP
