#ifndef SHARE_VM_RUNTIME_EXTENDEDPC_HPP
#define SHARE_VM_RUNTIME_EXTENDEDPC_HPP

// An ExtendedPC contains the _pc from a signal handler in a platform
// independent way.

class ExtendedPC VALUE_OBJ_CLASS_SPEC {
 private:
  address _pc;

 public:
  address pc() const { return _pc; }
  ExtendedPC(address pc) { _pc  = pc;   }
  ExtendedPC()           { _pc  = NULL; }
};

#endif // SHARE_VM_RUNTIME_EXTENDEDPC_HPP
