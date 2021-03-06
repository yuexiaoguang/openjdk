#include "precompiled.hpp"
#include "ci/ciTypeArrayKlass.hpp"
#include "ci/ciUtilities.hpp"

// ciTypeArrayKlass
//
// This class represents a Klass* in the HotSpot virtual machine
// whose Klass part in a TypeArrayKlass.

// ------------------------------------------------------------------
// ciTypeArrayKlass::ciTypeArrayKlass
ciTypeArrayKlass::ciTypeArrayKlass(KlassHandle h_k) : ciArrayKlass(h_k) {
  assert(get_Klass()->oop_is_typeArray(), "wrong type");
  assert(element_type() == get_TypeArrayKlass()->element_type(), "");
}

// ------------------------------------------------------------------
// ciTypeArrayKlass::make_impl
//
// Implementation of make.
ciTypeArrayKlass* ciTypeArrayKlass::make_impl(BasicType t) {
  Klass* k = Universe::typeArrayKlassObj(t);
  return CURRENT_ENV->get_type_array_klass(k);
}

// ------------------------------------------------------------------
// ciTypeArrayKlass::make
//
// Make an array klass corresponding to the specified primitive type.
ciTypeArrayKlass* ciTypeArrayKlass::make(BasicType t) {
  GUARDED_VM_ENTRY(return make_impl(t);)
}
