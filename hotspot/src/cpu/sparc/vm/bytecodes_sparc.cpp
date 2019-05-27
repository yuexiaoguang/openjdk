#include "precompiled.hpp"
#include "interpreter/bytecodes.hpp"


void Bytecodes::pd_initialize() {
  // (nothing)
}

Bytecodes::Code Bytecodes::pd_base_code_for(Code code) {
  return code;
}
