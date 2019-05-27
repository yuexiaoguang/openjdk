#include "precompiled.hpp"
#include "classfile/classLoaderData.hpp"
#include "memory/cardTableRS.hpp"
#include "memory/genRemSet.hpp"

// This kind of "BarrierSet" allows a "CollectedHeap" to detect and
// enumerate ref fields that have been modified (since the last
// enumeration.)

uintx GenRemSet::max_alignment_constraint(Name nm) {
  assert(nm == GenRemSet::CardTable, "Unrecognized GenRemSet type.");
  return CardTableRS::ct_max_alignment_constraint();
}

class HasAccumulatedModifiedOopsClosure : public KlassClosure {
  bool _found;
 public:
  HasAccumulatedModifiedOopsClosure() : _found(false) {}
  void do_klass(Klass* klass) {
    if (_found) {
      return;
    }

    if (klass->has_accumulated_modified_oops()) {
      _found = true;
    }
  }
  bool found() {
    return _found;
  }
};

bool KlassRemSet::mod_union_is_clear() {
  HasAccumulatedModifiedOopsClosure closure;
  ClassLoaderDataGraph::classes_do(&closure);

  return !closure.found();
}


class ClearKlassModUnionClosure : public KlassClosure {
 public:
  void do_klass(Klass* klass) {
    if (klass->has_accumulated_modified_oops()) {
      klass->clear_accumulated_modified_oops();
    }
  }
};

void KlassRemSet::clear_mod_union() {
  ClearKlassModUnionClosure closure;
  ClassLoaderDataGraph::classes_do(&closure);
}
