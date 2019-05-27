#ifndef SHARE_VM_SHARK_SHARKENTRY_HPP
#define SHARE_VM_SHARK_SHARKENTRY_HPP

#include "shark/llvmHeaders.hpp"

class SharkContext;

class SharkEntry : public ZeroEntry {
 private:
  address         _code_limit;
  SharkContext*   _context;
  llvm::Function* _function;

 public:
  address code_start() const {
    return entry_point();
  }
  address code_limit() const {
    return _code_limit;
  }
  SharkContext* context() const {
    return _context;
  }
  llvm::Function* function() const {
    return _function;
  }

 public:
  void set_code_limit(address code_limit) {
    _code_limit = code_limit;
  }
  void set_context(SharkContext* context) {
    _context = context;
  }
  void set_function(llvm::Function* function) {
    _function = function;
  }
};

#endif // SHARE_VM_SHARK_SHARKENTRY_HPP
