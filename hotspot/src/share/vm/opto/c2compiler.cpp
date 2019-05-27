#include "precompiled.hpp"
#include "opto/c2compiler.hpp"
#include "opto/runtime.hpp"
#ifdef TARGET_ARCH_MODEL_x86_32
# include "adfiles/ad_x86_32.hpp"
#endif
#ifdef TARGET_ARCH_MODEL_x86_64
# include "adfiles/ad_x86_64.hpp"
#endif
#ifdef TARGET_ARCH_MODEL_sparc
# include "adfiles/ad_sparc.hpp"
#endif
#ifdef TARGET_ARCH_MODEL_zero
# include "adfiles/ad_zero.hpp"
#endif
#ifdef TARGET_ARCH_MODEL_arm
# include "adfiles/ad_arm.hpp"
#endif
#ifdef TARGET_ARCH_MODEL_ppc
# include "adfiles/ad_ppc.hpp"
#endif

// register information defined by ADLC
extern const char register_save_policy[];
extern const int  register_save_type[];

const char* C2Compiler::retry_no_subsuming_loads() {
  return "retry without subsuming loads";
}
const char* C2Compiler::retry_no_escape_analysis() {
  return "retry without escape analysis";
}
bool C2Compiler::init_c2_runtime() {

  // Check assumptions used while running ADLC
  Compile::adlc_verification();
  assert(REG_COUNT <= ConcreteRegisterImpl::number_of_registers, "incompatible register counts");

  for (int i = 0; i < ConcreteRegisterImpl::number_of_registers ; i++ ) {
      OptoReg::vm2opto[i] = OptoReg::Bad;
  }

  for( OptoReg::Name i=OptoReg::Name(0); i<OptoReg::Name(REG_COUNT); i = OptoReg::add(i,1) ) {
    VMReg r = OptoReg::as_VMReg(i);
    if (r->is_valid()) {
      OptoReg::vm2opto[r->value()] = i;
    }
  }

  // Check that runtime and architecture description agree on callee-saved-floats
  bool callee_saved_floats = false;
  for( OptoReg::Name i=OptoReg::Name(0); i<OptoReg::Name(_last_Mach_Reg); i = OptoReg::add(i,1) ) {
    // Is there a callee-saved float or double?
    if( register_save_policy[i] == 'E' /* callee-saved */ &&
       (register_save_type[i] == Op_RegF || register_save_type[i] == Op_RegD) ) {
      callee_saved_floats = true;
    }
  }

  DEBUG_ONLY( Node::init_NodeProperty(); )

  Compile::pd_compiler2_init();

  CompilerThread* thread = CompilerThread::current();

  HandleMark handle_mark(thread);
  return OptoRuntime::generate(thread->env());
}


void C2Compiler::initialize() {
  // The first compiler thread that gets here will initialize the
  // small amount of global state (and runtime stubs) that C2 needs.

  // There is a race possible once at startup and then we're fine

  // Note that this is being called from a compiler thread not the
  // main startup thread.
  if (should_perform_init()) {
    bool successful = C2Compiler::init_c2_runtime();
    int new_state = (successful) ? initialized : failed;
    set_state(new_state);
  }
}

void C2Compiler::compile_method(ciEnv* env, ciMethod* target, int entry_bci) {
  assert(is_initialized(), "Compiler thread must be initialized");

  bool subsume_loads = SubsumeLoads;
  bool do_escape_analysis = DoEscapeAnalysis && !env->jvmti_can_access_local_variables();
  bool eliminate_boxing = EliminateAutoBox;
  while (!env->failing()) {
    // Attempt to compile while subsuming loads into machine instructions.
    Compile C(env, this, target, entry_bci, subsume_loads, do_escape_analysis, eliminate_boxing);


    // Check result and retry if appropriate.
    if (C.failure_reason() != NULL) {
      if (C.failure_reason_is(retry_no_subsuming_loads())) {
        assert(subsume_loads, "must make progress");
        subsume_loads = false;
        continue;  // retry
      }
      if (C.failure_reason_is(retry_no_escape_analysis())) {
        assert(do_escape_analysis, "must make progress");
        do_escape_analysis = false;
        continue;  // retry
      }
      if (C.has_boxed_value()) {
        // Recompile without boxing elimination regardless failure reason.
        assert(eliminate_boxing, "must make progress");
        eliminate_boxing = false;
        continue;  // retry
      }
      // Pass any other failure reason up to the ciEnv.
      // Note that serious, irreversible failures are already logged
      // on the ciEnv via env->record_method_not_compilable().
      env->record_failure(C.failure_reason());
    }
    if (StressRecompilation) {
      if (subsume_loads) {
        subsume_loads = false;
        continue;  // retry
      }
      if (do_escape_analysis) {
        do_escape_analysis = false;
        continue;  // retry
      }
    }

    // No retry; just break the loop.
    break;
  }
}


void C2Compiler::print_timers() {
  // do nothing
}