#ifndef SHARE_VM_SHARK_SHARKFUNCTION_HPP
#define SHARE_VM_SHARK_SHARKFUNCTION_HPP

#include "ci/ciEnv.hpp"
#include "ci/ciStreams.hpp"
#include "ci/ciTypeFlow.hpp"
#include "memory/allocation.hpp"
#include "shark/llvmHeaders.hpp"
#include "shark/llvmValue.hpp"
#include "shark/sharkBuilder.hpp"
#include "shark/sharkContext.hpp"
#include "shark/sharkInvariants.hpp"
#include "shark/sharkStack.hpp"

class SharkTopLevelBlock;
class DeferredZeroCheck;

class SharkFunction : public SharkTargetInvariants {
 friend class SharkStackWithNormalFrame;

 public:
  static llvm::Function* build(ciEnv*        env,
                               SharkBuilder* builder,
                               ciTypeFlow*   flow,
                               const char*   name) {
    SharkFunction function(env, builder, flow, name);
    return function.function();
  }

 private:
  SharkFunction(ciEnv*        env,
                SharkBuilder* builder,
                ciTypeFlow*   flow,
                const char*   name)
    : SharkTargetInvariants(env, builder, flow) { initialize(name); }

 private:
  void initialize(const char* name);

 private:
  llvm::Function*                   _function;
  SharkTopLevelBlock**              _blocks;
  GrowableArray<DeferredZeroCheck*> _deferred_zero_checks;
  SharkStack*                       _stack;

 public:
  llvm::Function* function() const {
    return _function;
  }
  int block_count() const {
    return flow()->block_count();
  }
  SharkTopLevelBlock* block(int i) const {
    assert(i < block_count(), "should be");
    return _blocks[i];
  }
  GrowableArray<DeferredZeroCheck*>* deferred_zero_checks() {
    return &_deferred_zero_checks;
  }
  SharkStack* stack() const {
    return _stack;
  }

  // On-stack replacement
 private:
  bool is_osr() const {
    return flow()->is_osr_flow();
  }
  llvm::FunctionType* entry_point_type() const {
    if (is_osr())
      return SharkType::osr_entry_point_type();
    else
      return SharkType::entry_point_type();
  }

  // Block management
 private:
  llvm::BasicBlock* _block_insertion_point;

  void set_block_insertion_point(llvm::BasicBlock* block_insertion_point) {
    _block_insertion_point = block_insertion_point;
  }
  llvm::BasicBlock* block_insertion_point() const {
    return _block_insertion_point;
  }

 public:
  llvm::BasicBlock* CreateBlock(const char* name = "") const {
    return llvm::BasicBlock::Create(
      SharkContext::current(), name, function(), block_insertion_point());
  }

  // Deferred zero checks
 public:
  void add_deferred_zero_check(SharkTopLevelBlock* block,
                               SharkValue*         value);

 private:
  void do_deferred_zero_checks();
};

#endif // SHARE_VM_SHARK_SHARKFUNCTION_HPP
