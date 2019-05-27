#ifndef SHARE_VM_SHARK_SHARKINLINER_HPP
#define SHARE_VM_SHARK_SHARKINLINER_HPP

#include "ci/ciMethod.hpp"
#include "memory/allocation.hpp"
#include "shark/llvmHeaders.hpp"
#include "shark/sharkState.hpp"

class SharkInliner : public AllStatic {
 public:
  static bool attempt_inline(ciMethod* target, SharkState* state);

 private:
  static bool may_be_inlinable(ciMethod* target);
};

#endif // SHARE_VM_SHARK_SHARKINLINER_HPP
