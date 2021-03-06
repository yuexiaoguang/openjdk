
#include "precompiled.hpp"
#include "register_x86.hpp"

#ifndef AMD64
const int ConcreteRegisterImpl::max_gpr = RegisterImpl::number_of_registers;
#else
const int ConcreteRegisterImpl::max_gpr = RegisterImpl::number_of_registers << 1;
#endif // AMD64


const int ConcreteRegisterImpl::max_fpr = ConcreteRegisterImpl::max_gpr +
                                                                 2 * FloatRegisterImpl::number_of_registers;
const int ConcreteRegisterImpl::max_xmm = ConcreteRegisterImpl::max_fpr +
                                                                 8 * XMMRegisterImpl::number_of_registers;
const char* RegisterImpl::name() const {
  const char* names[number_of_registers] = {
#ifndef AMD64
    "eax", "ecx", "edx", "ebx", "esp", "ebp", "esi", "edi"
#else
    "rax", "rcx", "rdx", "rbx", "rsp", "rbp", "rsi", "rdi",
    "r8",  "r9",  "r10", "r11", "r12", "r13", "r14", "r15"
#endif // AMD64
  };
  return is_valid() ? names[encoding()] : "noreg";
}

const char* FloatRegisterImpl::name() const {
  const char* names[number_of_registers] = {
    "st0", "st1", "st2", "st3", "st4", "st5", "st6", "st7"
  };
  return is_valid() ? names[encoding()] : "noreg";
}

const char* XMMRegisterImpl::name() const {
  const char* names[number_of_registers] = {
    "xmm0","xmm1","xmm2","xmm3","xmm4","xmm5","xmm6","xmm7"
#ifdef AMD64
    ,"xmm8",  "xmm9", "xmm10", "xmm11", "xmm12", "xmm13", "xmm14", "xmm15"
#endif // AMD64
  };
  return is_valid() ? names[encoding()] : "xnoreg";
}
