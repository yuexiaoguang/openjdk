#ifndef SHARE_VM_INTERPRETER_INTERPRETERGENERATOR_HPP
#define SHARE_VM_INTERPRETER_INTERPRETERGENERATOR_HPP

#include "interpreter/cppInterpreter.hpp"
#include "interpreter/cppInterpreterGenerator.hpp"
#include "interpreter/templateInterpreter.hpp"
#include "interpreter/templateInterpreterGenerator.hpp"

// This file contains the platform-independent parts
// of the interpreter generator.
class InterpreterGenerator: public CC_INTERP_ONLY(CppInterpreterGenerator)
                                   NOT_CC_INTERP(TemplateInterpreterGenerator) {

public:

InterpreterGenerator(StubQueue* _code);

#ifdef TARGET_ARCH_x86
# include "interpreterGenerator_x86.hpp"
#endif
#ifdef TARGET_ARCH_sparc
# include "interpreterGenerator_sparc.hpp"
#endif
#ifdef TARGET_ARCH_zero
# include "interpreterGenerator_zero.hpp"
#endif
#ifdef TARGET_ARCH_arm
# include "interpreterGenerator_arm.hpp"
#endif
#ifdef TARGET_ARCH_ppc
# include "interpreterGenerator_ppc.hpp"
#endif


};

#endif // SHARE_VM_INTERPRETER_INTERPRETERGENERATOR_HPP
