
#ifndef CPU_X86_VM_ASSEMBLER_X86_INLINE_HPP
#define CPU_X86_VM_ASSEMBLER_X86_INLINE_HPP

#include "asm/assembler.inline.hpp"
#include "asm/codeBuffer.hpp"
#include "code/codeCache.hpp"

#ifndef _LP64
inline int Assembler::prefix_and_encode(int reg_enc, bool byteinst) { return reg_enc; }
inline int Assembler::prefixq_and_encode(int reg_enc) { return reg_enc; }

inline int Assembler::prefix_and_encode(int dst_enc, int src_enc, bool byteinst) { return dst_enc << 3 | src_enc; }
inline int Assembler::prefixq_and_encode(int dst_enc, int src_enc) { return dst_enc << 3 | src_enc; }

inline void Assembler::prefix(Register reg) {}
inline void Assembler::prefix(Address adr) {}
inline void Assembler::prefixq(Address adr) {}

inline void Assembler::prefix(Address adr, Register reg,  bool byteinst) {}
inline void Assembler::prefixq(Address adr, Register reg) {}

inline void Assembler::prefix(Address adr, XMMRegister reg) {}
inline void Assembler::prefixq(Address adr, XMMRegister reg) {}
#endif // _LP64

#endif // CPU_X86_VM_ASSEMBLER_X86_INLINE_HPP
