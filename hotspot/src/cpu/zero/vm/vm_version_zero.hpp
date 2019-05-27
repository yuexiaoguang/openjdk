
#ifndef CPU_ZERO_VM_VM_VERSION_ZERO_HPP
#define CPU_ZERO_VM_VM_VERSION_ZERO_HPP

#include "runtime/globals_extension.hpp"
#include "runtime/vm_version.hpp"

class VM_Version : public Abstract_VM_Version {
 public:
  static const char* cpu_features() {
    return "";
  }
};

#endif // CPU_ZERO_VM_VM_VERSION_ZERO_HPP
