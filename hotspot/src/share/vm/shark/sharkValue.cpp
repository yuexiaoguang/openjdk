#include "precompiled.hpp"
#include "ci/ciType.hpp"
#include "shark/llvmHeaders.hpp"
#include "shark/llvmValue.hpp"
#include "shark/sharkBuilder.hpp"
#include "shark/sharkValue.hpp"

using namespace llvm;

// Cloning

SharkValue* SharkNormalValue::clone() const {
  return SharkValue::create_generic(type(), generic_value(), zero_checked());
}
SharkValue* SharkPHIValue::clone() const {
  return SharkValue::create_phi(type(), (PHINode *) generic_value(), this);
}
SharkValue* SharkAddressValue::clone() const {
  return SharkValue::address_constant(address_value());
}

// Casting

bool SharkValue::is_phi() const {
  return false;
}
bool SharkPHIValue::is_phi() const {
  return true;
}
SharkPHIValue* SharkValue::as_phi() {
  ShouldNotCallThis();
}
SharkPHIValue* SharkPHIValue::as_phi() {
  return this;
}

// Comparison

bool SharkNormalValue::equal_to(SharkValue *other) const {
  return (this->type()          == other->type() &&
          this->generic_value() == other->generic_value() &&
          this->zero_checked()  == other->zero_checked());
}
bool SharkAddressValue::equal_to(SharkValue *other) const {
  return (this->address_value() == other->address_value());
}

// Type access

ciType* SharkValue::type() const {
  ShouldNotCallThis();
}
ciType* SharkNormalValue::type() const {
  return _type;
}

BasicType SharkNormalValue::basic_type() const {
  return type()->basic_type();
}
BasicType SharkAddressValue::basic_type() const {
  return T_ADDRESS;
}

int SharkNormalValue::size() const {
  return type()->size();
}
int SharkAddressValue::size() const {
  return 1;
}

bool SharkValue::is_jint() const {
  return false;
}
bool SharkValue::is_jlong() const {
  return false;
}
bool SharkValue::is_jfloat() const {
  return false;
}
bool SharkValue::is_jdouble() const {
  return false;
}
bool SharkValue::is_jobject() const {
  return false;
}
bool SharkValue::is_jarray() const {
  return false;
}
bool SharkValue::is_address() const {
  return false;
}

bool SharkNormalValue::is_jint() const {
  return llvm_value()->getType() == SharkType::jint_type();
}
bool SharkNormalValue::is_jlong() const {
  return llvm_value()->getType() == SharkType::jlong_type();
}
bool SharkNormalValue::is_jfloat() const {
  return llvm_value()->getType() == SharkType::jfloat_type();
}
bool SharkNormalValue::is_jdouble() const {
  return llvm_value()->getType() == SharkType::jdouble_type();
}
bool SharkNormalValue::is_jobject() const {
  return llvm_value()->getType() == SharkType::oop_type();
}
bool SharkNormalValue::is_jarray() const {
  return basic_type() == T_ARRAY;
}
bool SharkAddressValue::is_address() const {
  return true;
}

// Typed conversions from SharkValues

Value* SharkValue::jint_value() const {
  ShouldNotCallThis();
}
Value* SharkValue::jlong_value() const {
  ShouldNotCallThis();
}
Value* SharkValue::jfloat_value() const {
  ShouldNotCallThis();
}
Value* SharkValue::jdouble_value() const {
  ShouldNotCallThis();
}
Value* SharkValue::jobject_value() const {
  ShouldNotCallThis();
}
Value* SharkValue::jarray_value() const {
  ShouldNotCallThis();
}
int SharkValue::address_value() const {
  ShouldNotCallThis();
}

Value* SharkNormalValue::jint_value() const {
  assert(is_jint(), "should be");
  return llvm_value();
}
Value* SharkNormalValue::jlong_value() const {
  assert(is_jlong(), "should be");
  return llvm_value();
}
Value* SharkNormalValue::jfloat_value() const {
  assert(is_jfloat(), "should be");
  return llvm_value();
}
Value* SharkNormalValue::jdouble_value() const {
  assert(is_jdouble(), "should be");
  return llvm_value();
}
Value* SharkNormalValue::jobject_value() const {
  assert(is_jobject(), "should be");
  return llvm_value();
}
Value* SharkNormalValue::jarray_value() const {
  // XXX assert(is_jarray(), "should be");
  // XXX http://icedtea.classpath.org/bugzilla/show_bug.cgi?id=324
  assert(is_jobject(), "should be");
  return llvm_value();
}
int SharkAddressValue::address_value() const {
  return _bci;
}

// Type-losing conversions -- use with care!

Value* SharkNormalValue::generic_value() const {
  return llvm_value();
}
Value* SharkAddressValue::generic_value() const {
  return LLVMValue::intptr_constant(address_value());
}

Value* SharkValue::intptr_value(SharkBuilder* builder) const {
  ShouldNotCallThis();
}
Value* SharkNormalValue::intptr_value(SharkBuilder* builder) const {
  return builder->CreatePtrToInt(jobject_value(), SharkType::intptr_type());
}

// Phi-style stuff for SharkPHIState::add_incoming

void SharkValue::addIncoming(SharkValue *value, BasicBlock* block) {
  ShouldNotCallThis();
}
void SharkPHIValue::addIncoming(SharkValue *value, BasicBlock* block) {
  assert(!is_clone(), "shouldn't be");
  ((llvm::PHINode *) generic_value())->addIncoming(
      value->generic_value(), block);
  if (!value->zero_checked())
    _all_incomers_zero_checked = false;
}
void SharkAddressValue::addIncoming(SharkValue *value, BasicBlock* block) {
  assert(this->equal_to(value), "should be");
}

// Phi-style stuff for SharkState::merge

SharkValue* SharkNormalValue::merge(SharkBuilder* builder,
                                    SharkValue*   other,
                                    BasicBlock*   other_block,
                                    BasicBlock*   this_block,
                                    const char*   name) {
  assert(type() == other->type(), "should be");
  assert(zero_checked() == other->zero_checked(), "should be");

  PHINode *phi = builder->CreatePHI(SharkType::to_stackType(type()), 0, name);
  phi->addIncoming(this->generic_value(), this_block);
  phi->addIncoming(other->generic_value(), other_block);
  return SharkValue::create_generic(type(), phi, zero_checked());
}
SharkValue* SharkAddressValue::merge(SharkBuilder* builder,
                                     SharkValue*   other,
                                     BasicBlock*   other_block,
                                     BasicBlock*   this_block,
                                     const char*   name) {
  assert(this->equal_to(other), "should be");
  return this;
}

// Repeated null and divide-by-zero check removal

bool SharkValue::zero_checked() const {
  ShouldNotCallThis();
}
void SharkValue::set_zero_checked(bool zero_checked) {
  ShouldNotCallThis();
}

bool SharkNormalValue::zero_checked() const {
  return _zero_checked;
}
void SharkNormalValue::set_zero_checked(bool zero_checked) {
  _zero_checked = zero_checked;
}
