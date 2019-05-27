#ifndef SHARE_VM_CI_CIMETHODTYPE_HPP
#define SHARE_VM_CI_CIMETHODTYPE_HPP

#include "ci/ciInstance.hpp"
#include "ci/ciUtilities.hpp"
#include "classfile/javaClasses.hpp"

// ciMethodType
//
// The class represents a java.lang.invoke.MethodType object.
class ciMethodType : public ciInstance {
private:
  ciType* class_to_citype(oop klass_oop) const {
    if (java_lang_Class::is_primitive(klass_oop)) {
      BasicType bt = java_lang_Class::primitive_type(klass_oop);
      return ciType::make(bt);
    } else {
      Klass* k = java_lang_Class::as_Klass(klass_oop);
      return CURRENT_ENV->get_klass(k);
    }
  }

public:
  ciMethodType(instanceHandle h_i) : ciInstance(h_i) {}

  // What kind of ciObject is this?
  bool is_method_type() const { return true; }

  ciType* rtype() const {
    GUARDED_VM_ENTRY(
      oop rtype = java_lang_invoke_MethodType::rtype(get_oop());
      return class_to_citype(rtype);
    )
  }

  int ptype_count() const {
    GUARDED_VM_ENTRY(return java_lang_invoke_MethodType::ptype_count(get_oop());)
  }

  int ptype_slot_count() const {
    GUARDED_VM_ENTRY(return java_lang_invoke_MethodType::ptype_slot_count(get_oop());)
  }

  ciType* ptype_at(int index) const {
    GUARDED_VM_ENTRY(
      oop ptype = java_lang_invoke_MethodType::ptype(get_oop(), index);
      return class_to_citype(ptype);
    )
  }
};

#endif // SHARE_VM_CI_CIMETHODTYPE_HPP
