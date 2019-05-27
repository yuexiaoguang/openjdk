#ifndef SHARE_VM_ASM_ASSEMBLER_INLINE_HPP
#define SHARE_VM_ASM_ASSEMBLER_INLINE_HPP

#include "asm/assembler.hpp"

#ifdef TARGET_ARCH_x86
# include "assembler_x86.inline.hpp"
#endif
#ifdef TARGET_ARCH_sparc
# include "assembler_sparc.inline.hpp"
#endif
#ifdef TARGET_ARCH_zero
# include "assembler_zero.inline.hpp"
#endif
#ifdef TARGET_ARCH_arm
# include "assembler_arm.inline.hpp"
#endif
#ifdef TARGET_ARCH_ppc
# include "assembler_ppc.inline.hpp"
#endif

#endif // SHARE_VM_ASM_ASSEMBLER_INLINE_HPP
