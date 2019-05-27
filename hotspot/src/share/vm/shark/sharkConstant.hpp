#ifndef SHARE_VM_SHARK_SHARKCONSTANT_HPP
#define SHARE_VM_SHARK_SHARKCONSTANT_HPP

#include "ci/ciStreams.hpp"
#include "memory/allocation.hpp"
#include "shark/sharkBuilder.hpp"
#include "shark/sharkValue.hpp"

class SharkConstant : public ResourceObj {
 public:
  static SharkConstant* for_ldc(ciBytecodeStream* iter);
  static SharkConstant* for_field(ciBytecodeStream* iter);

 private:
  SharkConstant(ciConstant constant, ciType* type);

 private:
  SharkValue* _value;
  ciObject*   _object;
  ciType*     _type;
  bool        _is_loaded;
  bool        _is_nonzero;
  bool        _is_two_word;

 public:
  bool is_loaded() const {
    return _is_loaded;
  }
  bool is_nonzero() const {
    assert(is_loaded(), "should be");
    return _is_nonzero;
  }
  bool is_two_word() const {
    assert(is_loaded(), "should be");
    return _is_two_word;
  }

 public:
  SharkValue* value(SharkBuilder* builder) {
    assert(is_loaded(), "should be");
    if (_value == NULL) {
      _value = SharkValue::create_generic(
        _type, builder->CreateInlineOop(_object), _is_nonzero);
    }
    return _value;
  }
};

#endif // SHARE_VM_SHARK_SHARKCONSTANT_HPP
