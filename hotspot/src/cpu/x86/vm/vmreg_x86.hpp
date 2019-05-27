
#ifndef CPU_X86_VM_VMREG_X86_HPP
#define CPU_X86_VM_VMREG_X86_HPP

  bool is_Register();
  Register as_Register();

  bool is_FloatRegister();
  FloatRegister as_FloatRegister();

  bool is_XMMRegister();
  XMMRegister as_XMMRegister();

#endif // CPU_X86_VM_VMREG_X86_HPP
