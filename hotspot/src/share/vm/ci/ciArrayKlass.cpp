#include "precompiled.hpp"
#include "ci/ciArrayKlass.hpp"
#include "ci/ciObjArrayKlass.hpp"
#include "ci/ciTypeArrayKlass.hpp"
#include "ci/ciUtilities.hpp"

// ciArrayKlass
//
// This class represents a Klass* in the HotSpot virtual machine
// whose Klass part in an ArrayKlass.

// ------------------------------------------------------------------
// ciArrayKlass::ciArrayKlass
//
// Loaded array klass.
ciArrayKlass::ciArrayKlass(KlassHandle h_k) : ciKlass(h_k) {
  assert(get_Klass()->oop_is_array(), "wrong type");
  _dimension = get_ArrayKlass()->dimension();
}

// ------------------------------------------------------------------
// ciArrayKlass::ciArrayKlass
//
// Unloaded array klass.
ciArrayKlass::ciArrayKlass(ciSymbol* name, int dimension, BasicType bt)
  : ciKlass(name, bt) {
  _dimension = dimension;
}

// ------------------------------------------------------------------
// ciArrayKlass::element_type
//
// What type is obtained when this array is indexed once?
ciType* ciArrayKlass::element_type() {
  if (is_type_array_klass()) {
    return ciType::make(as_type_array_klass()->element_type());
  } else {
    return as_obj_array_klass()->element_klass()->as_klass();
  }
}


// ------------------------------------------------------------------
// ciArrayKlass::base_element_type
//
// What type is obtained when this array is indexed as many times as possible?
ciType* ciArrayKlass::base_element_type() {
  if (is_type_array_klass()) {
    return ciType::make(as_type_array_klass()->element_type());
  } else {
    ciKlass* ek = as_obj_array_klass()->base_element_klass();
    if (ek->is_type_array_klass()) {
      return ciType::make(ek->as_type_array_klass()->element_type());
    }
    return ek;
  }
}


// ------------------------------------------------------------------
// ciArrayKlass::is_leaf_type
bool ciArrayKlass::is_leaf_type() {
  if (is_type_array_klass()) {
    return true;
  } else {
    return as_obj_array_klass()->base_element_klass()->is_leaf_type();
  }
}


// ------------------------------------------------------------------
// ciArrayKlass::base_element_type
//
// What type is obtained when this array is indexed as many times as possible?
ciArrayKlass* ciArrayKlass::make(ciType* element_type) {
  if (element_type->is_primitive_type()) {
    return ciTypeArrayKlass::make(element_type->basic_type());
  } else {
    return ciObjArrayKlass::make(element_type->as_klass());
  }
}
