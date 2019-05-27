#ifndef SHARE_VM_ASM_MACROASSEMBLER_INLINE_HPP
#define SHARE_VM_ASM_MACROASSEMBLER_INLINE_HPP

#include "asm/macroAssembler.hpp"

#ifdef TARGET_ARCH_x86
// no macroAssembler_x86.inline.hpp
#endif
#ifdef TARGET_ARCH_sparc
# include "macroAssembler_sparc.inline.hpp"
#endif
#ifdef TARGET_ARCH_zero
# include "assembler_zero.inline.hpp"
#endif
#ifdef TARGET_ARCH_arm
# include "macroAssembler_arm.inline.hpp"
#endif
#ifdef TARGET_ARCH_ppc
# include "macroAssembler_ppc.inline.hpp"
#endif

#endif // SHARE_VM_ASM_MACROASSEMBLER_INLINE_HPP
