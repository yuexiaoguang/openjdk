#ifndef SHARE_VM_ASM_MACROASSEMBLER_HPP
#define SHARE_VM_ASM_MACROASSEMBLER_HPP

#include "asm/assembler.hpp"

#ifdef TARGET_ARCH_x86
# include "macroAssembler_x86.hpp"
#endif
#ifdef TARGET_ARCH_sparc
# include "macroAssembler_sparc.hpp"
#endif
#ifdef TARGET_ARCH_zero
# include "assembler_zero.hpp"
#endif
#ifdef TARGET_ARCH_arm
# include "macroAssembler_arm.hpp"
#endif
#ifdef TARGET_ARCH_ppc
# include "macroAssembler_ppc.hpp"
#endif

#endif // SHARE_VM_ASM_MACROASSEMBLER_HPP
