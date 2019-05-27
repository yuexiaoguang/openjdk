
#ifndef CPU_X86_VM_INTERP_MASM_X86_HPP
#define CPU_X86_VM_INTERP_MASM_X86_HPP

#include "asm/macroAssembler.hpp"
#include "asm/macroAssembler.inline.hpp"
#include "interpreter/invocationCounter.hpp"
#include "runtime/frame.hpp"

// This file specializes the assember with interpreter-specific macros


class InterpreterMacroAssembler: public MacroAssembler {

#ifdef TARGET_ARCH_MODEL_x86_32
# include "interp_masm_x86_32.hpp"
#endif
#ifdef TARGET_ARCH_MODEL_x86_64
# include "interp_masm_x86_64.hpp"
#endif

 private:

  Register _locals_register; // register that contains the pointer to the locals
  Register _bcp_register; // register that contains the bcp

 public:
#ifndef CC_INTERP
  void profile_obj_type(Register obj, const Address& mdo_addr);
  void profile_arguments_type(Register mdp, Register callee, Register tmp, bool is_virtual);
  void profile_return_type(Register mdp, Register ret, Register tmp);
  void profile_parameters_type(Register mdp, Register tmp1, Register tmp2);
#endif /* !CC_INTERP */

};

#endif // CPU_X86_VM_INTERP_MASM_X86_HPP
