
#ifndef CPU_X86_VM_STUBROUTINES_X86_32_HPP
#define CPU_X86_VM_STUBROUTINES_X86_32_HPP

// This file holds the platform specific parts of the StubRoutines
// definition. See stubRoutines.hpp for a description on how to
// extend it.

enum platform_dependent_constants {
  code_size1 =  9000,           // simply increase if too small (assembler will crash if too small)
  code_size2 = 22000            // simply increase if too small (assembler will crash if too small)
};

class x86 {
 friend class StubGenerator;
 friend class VMStructs;

 private:
  static address _verify_fpu_cntrl_wrd_entry;

 public:
  static address verify_fpu_cntrl_wrd_entry()                { return _verify_fpu_cntrl_wrd_entry; }

# include "stubRoutines_x86.hpp"

};

  static bool    returns_to_call_stub(address return_pc)     { return return_pc == _call_stub_return_address; }

#endif // CPU_X86_VM_STUBROUTINES_X86_32_HPP
