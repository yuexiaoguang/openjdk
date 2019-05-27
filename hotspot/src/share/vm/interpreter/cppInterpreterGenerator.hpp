#ifndef SHARE_VM_INTERPRETER_CPPINTERPRETERGENERATOR_HPP
#define SHARE_VM_INTERPRETER_CPPINTERPRETERGENERATOR_HPP

// This file contains the platform-independent parts
// of the template interpreter generator.

#ifdef CC_INTERP

class CppInterpreterGenerator: public AbstractInterpreterGenerator {
  protected:
  // shared code sequences
  // Converter for native abi result to tosca result
  address generate_result_handler_for(BasicType type);
  address generate_tosca_to_stack_converter(BasicType type);
  address generate_stack_to_stack_converter(BasicType type);
  address generate_stack_to_native_abi_converter(BasicType type);

  void generate_all();

 public:
  CppInterpreterGenerator(StubQueue* _code);

#ifdef TARGET_ARCH_x86
# include "cppInterpreterGenerator_x86.hpp"
#endif
#ifdef TARGET_ARCH_sparc
# include "cppInterpreterGenerator_sparc.hpp"
#endif
#ifdef TARGET_ARCH_zero
# include "cppInterpreterGenerator_zero.hpp"
#endif
#ifdef TARGET_ARCH_arm
# include "cppInterpreterGenerator_arm.hpp"
#endif
#ifdef TARGET_ARCH_ppc
# include "cppInterpreterGenerator_ppc.hpp"
#endif

};

#endif // CC_INTERP

#endif // SHARE_VM_INTERPRETER_CPPINTERPRETERGENERATOR_HPP
