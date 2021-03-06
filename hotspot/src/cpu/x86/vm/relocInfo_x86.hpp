
#ifndef CPU_X86_VM_RELOCINFO_X86_HPP
#define CPU_X86_VM_RELOCINFO_X86_HPP

  // machine-dependent parts of class relocInfo
 private:
  enum {
    // Intel instructions are byte-aligned.
    offset_unit        =  1,

    // Encodes Assembler::disp32_operand vs. Assembler::imm32_operand.
#ifndef AMD64
    format_width       =  1
#else
    // vs Assembler::narrow_oop_operand.
    format_width       =  2
#endif
  };

#endif // CPU_X86_VM_RELOCINFO_X86_HPP
