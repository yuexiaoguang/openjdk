#ifndef CPU_SPARC_VM_DISASSEMBLER_SPARC_HPP
#define CPU_SPARC_VM_DISASSEMBLER_SPARC_HPP

  static int pd_instruction_alignment() {
    return sizeof(int);
  }

  static const char* pd_cpu_opts() {
    return "v9only";
  }

#endif // CPU_SPARC_VM_DISASSEMBLER_SPARC_HPP
