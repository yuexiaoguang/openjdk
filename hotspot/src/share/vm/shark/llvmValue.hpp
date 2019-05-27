#ifndef SHARE_VM_SHARK_LLVMVALUE_HPP
#define SHARE_VM_SHARK_LLVMVALUE_HPP

#include "shark/llvmHeaders.hpp"
#include "shark/sharkContext.hpp"
#include "shark/sharkType.hpp"

class LLVMValue : public AllStatic {
 public:
  static llvm::ConstantInt* jbyte_constant(jbyte value)
  {
    return llvm::ConstantInt::get(SharkType::jbyte_type(), value, true);
  }
  static llvm::ConstantInt* jint_constant(jint value)
  {
    return llvm::ConstantInt::get(SharkType::jint_type(), value, true);
  }
  static llvm::ConstantInt* jlong_constant(jlong value)
  {
    return llvm::ConstantInt::get(SharkType::jlong_type(), value, true);
  }
  static llvm::ConstantFP* jfloat_constant(jfloat value)
  {
    return llvm::ConstantFP::get(SharkContext::current(), llvm::APFloat(value));
  }
  static llvm::ConstantFP* jdouble_constant(jdouble value)
  {
    return llvm::ConstantFP::get(SharkContext::current(), llvm::APFloat(value));
  }
  static llvm::ConstantPointerNull* null()
  {
    return llvm::ConstantPointerNull::get(SharkType::oop_type());
  }
  static llvm::ConstantPointerNull* nullKlass()
  {
    return llvm::ConstantPointerNull::get(SharkType::klass_type());
  }

 public:
  static llvm::ConstantInt* bit_constant(int value)
  {
    return llvm::ConstantInt::get(SharkType::bit_type(), value, false);
  }
  static llvm::ConstantInt* intptr_constant(intptr_t value)
  {
    return llvm::ConstantInt::get(SharkType::intptr_type(), value, false);
  }
};

#endif // SHARE_VM_SHARK_LLVMVALUE_HPP
