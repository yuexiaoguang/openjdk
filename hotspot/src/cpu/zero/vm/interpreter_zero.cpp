
#include "precompiled.hpp"
#include "asm/assembler.hpp"
#include "interpreter/bytecodeHistogram.hpp"
#include "interpreter/interpreter.hpp"
#include "interpreter/interpreterGenerator.hpp"
#include "interpreter/interpreterRuntime.hpp"
#include "interpreter/templateTable.hpp"
#include "oops/arrayOop.hpp"
#include "oops/methodData.hpp"
#include "oops/method.hpp"
#include "oops/oop.inline.hpp"
#include "prims/jvmtiExport.hpp"
#include "prims/jvmtiThreadState.hpp"
#include "prims/methodHandles.hpp"
#include "runtime/arguments.hpp"
#include "runtime/deoptimization.hpp"
#include "runtime/frame.inline.hpp"
#include "runtime/sharedRuntime.hpp"
#include "runtime/stubRoutines.hpp"
#include "runtime/synchronizer.hpp"
#include "runtime/timer.hpp"
#include "runtime/vframeArray.hpp"
#include "utilities/debug.hpp"
#ifdef COMPILER1
#include "c1/c1_Runtime1.hpp"
#endif
#ifdef CC_INTERP
#include "interpreter/cppInterpreter.hpp"
#endif

address AbstractInterpreterGenerator::generate_slow_signature_handler() {
  _masm->advance(1);
  return (address) InterpreterRuntime::slow_signature_handler;
}

address InterpreterGenerator::generate_math_entry(
    AbstractInterpreter::MethodKind kind) {
  if (!InlineIntrinsics)
    return NULL;

  Unimplemented();
  return NULL;
}

address InterpreterGenerator::generate_abstract_entry() {
  return generate_entry((address) ShouldNotCallThisEntry());
}

bool AbstractInterpreter::can_be_compiled(methodHandle m) {
  return true;
}

void Deoptimization::unwind_callee_save_values(frame* f,
                                               vframeArray* vframe_array) {
}
