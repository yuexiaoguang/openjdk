#ifndef CPU_SPARC_VM_TEMPLATEINTERPRETERGENERATOR_SPARC_HPP
#define CPU_SPARC_VM_TEMPLATEINTERPRETERGENERATOR_SPARC_HPP

  protected:

  void generate_fixed_frame(bool native_call); // template interpreter only
  void generate_stack_overflow_check(Register Rframe_size, Register Rscratch,
                                     Register Rscratch2);

#endif // CPU_SPARC_VM_TEMPLATEINTERPRETERGENERATOR_SPARC_HPP
