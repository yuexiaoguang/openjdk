#ifndef SHARE_VM_RUNTIME_STACKVALUE_HPP
#define SHARE_VM_RUNTIME_STACKVALUE_HPP

#include "code/location.hpp"
#include "runtime/handles.hpp"
#include "utilities/top.hpp"

class StackValue : public ResourceObj {
 private:
  BasicType _type;
  intptr_t  _i; // Blank java stack slot value
  Handle    _o; // Java stack slot value interpreted as a Handle
 public:

  StackValue(intptr_t value) {
    _type  = T_INT;
    _i     = value;
  }

  StackValue(Handle value, intptr_t scalar_replaced = 0) {
    _type    = T_OBJECT;
    _i       = scalar_replaced;
    _o       = value;
    assert(_i == 0 || _o.is_null(), "not null object should not be marked as scalar replaced");
  }

  StackValue() {
    _type   = T_CONFLICT;
    _i      = 0;
  }

  // Only used during deopt- preserve object type.
  StackValue(intptr_t o, BasicType t) {
    assert(t == T_OBJECT, "should not be used");
    _type   = t;
    _i      = o;
  }

  Handle get_obj() const {
    assert(type() == T_OBJECT, "type check");
    return _o;
  }

  bool obj_is_scalar_replaced() const {
    assert(type() == T_OBJECT, "type check");
    return _i != 0;
  }

  void set_obj(Handle value) {
    assert(type() == T_OBJECT, "type check");
    _o = value;
  }

  intptr_t get_int() const {
    assert(type() == T_INT, "type check");
    return _i;
  }

  // For special case in deopt.
  intptr_t get_int(BasicType t) const {
    assert(t == T_OBJECT && type() == T_OBJECT, "type check");
    return _i;
  }

  void set_int(intptr_t value) {
    assert(type() == T_INT, "type check");
    _i = value;
  }

  BasicType type() const { return  _type; }

  bool equal(StackValue *value) {
    if (_type != value->_type) return false;
    if (_type == T_OBJECT)
      return (_o == value->_o);
    else {
      assert(_type == T_INT, "sanity check");
      // [phh] compare only low addressed portions of intptr_t slots
      return (*(int *)&_i == *(int *)&value->_i);
    }
  }

  static StackValue* create_stack_value(const frame* fr, const RegisterMap* reg_map, ScopeValue* sv);
  static BasicLock*  resolve_monitor_lock(const frame* fr, Location location);

#ifndef PRODUCT
 public:
  // Printing
  void print_on(outputStream* st) const;
#endif
};

#endif // SHARE_VM_RUNTIME_STACKVALUE_HPP
