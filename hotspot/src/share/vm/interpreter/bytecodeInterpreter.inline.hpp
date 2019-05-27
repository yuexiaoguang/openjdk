#ifndef SHARE_VM_INTERPRETER_BYTECODEINTERPRETER_INLINE_HPP
#define SHARE_VM_INTERPRETER_BYTECODEINTERPRETER_INLINE_HPP

#include "interpreter/bytecodeInterpreter.hpp"
#include "runtime/stubRoutines.hpp"

// This file holds platform-independent bodies of inline functions for the C++ based interpreter

#ifdef CC_INTERP

#ifdef ASSERT
#define VERIFY_OOP(o_) \
      if (VerifyOops) { \
        assert((oop(o_))->is_oop_or_null(), "Not an oop!"); \
        StubRoutines::_verify_oop_count++;  \
      }
#else
#define VERIFY_OOP(o)
#endif

// Platform dependent data manipulation
#ifdef TARGET_ARCH_x86
# include "bytecodeInterpreter_x86.inline.hpp"
#endif
#ifdef TARGET_ARCH_sparc
# include "bytecodeInterpreter_sparc.inline.hpp"
#endif
#ifdef TARGET_ARCH_zero
# include "bytecodeInterpreter_zero.inline.hpp"
#endif
#ifdef TARGET_ARCH_arm
# include "bytecodeInterpreter_arm.inline.hpp"
#endif
#ifdef TARGET_ARCH_ppc
# include "bytecodeInterpreter_ppc.inline.hpp"
#endif

#endif // CC_INTERP

#endif // SHARE_VM_INTERPRETER_BYTECODEINTERPRETER_INLINE_HPP
