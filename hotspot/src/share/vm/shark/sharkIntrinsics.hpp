#ifndef SHARE_VM_SHARK_SHARKINTRINSICS_HPP
#define SHARE_VM_SHARK_SHARKINTRINSICS_HPP

#include "ci/ciMethod.hpp"
#include "memory/allocation.hpp"
#include "shark/llvmHeaders.hpp"
#include "shark/sharkState.hpp"

class SharkIntrinsics : public SharkTargetInvariants {
 public:
  static bool is_intrinsic(ciMethod* target);
  static void inline_intrinsic(ciMethod* target, SharkState* state);

 private:
  SharkIntrinsics(SharkState* state, ciMethod* target)
    : SharkTargetInvariants(state, target), _state(state) {}

 private:
  SharkState* _state;

 private:
  SharkState* state() const {
    return _state;
  }

 private:
  void do_intrinsic();

 private:
  void do_Math_minmax(llvm::ICmpInst::Predicate p);
  void do_Math_1to1(llvm::Value* function);
  void do_Math_2to1(llvm::Value* function);
  void do_Object_getClass();
  void do_System_currentTimeMillis();
  void do_Thread_currentThread();
  void do_Unsafe_compareAndSwapInt();
};

#endif // SHARE_VM_SHARK_SHARKINTRINSICS_HPP
