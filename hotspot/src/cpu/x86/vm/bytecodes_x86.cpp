
#include "precompiled.hpp"
#include "interpreter/bytecodes.hpp"


void Bytecodes::pd_initialize() {
  // No i486 specific initialization
}


Bytecodes::Code Bytecodes::pd_base_code_for(Code code) {
  // No i486 specific bytecodes
  return code;
}
