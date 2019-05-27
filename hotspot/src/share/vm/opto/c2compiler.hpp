#ifndef SHARE_VM_OPTO_C2COMPILER_HPP
#define SHARE_VM_OPTO_C2COMPILER_HPP

#include "compiler/abstractCompiler.hpp"

class C2Compiler : public AbstractCompiler {
 private:
  static bool init_c2_runtime();

public:
  // Name
  const char *name() { return "C2"; }

#ifdef TIERED
  virtual bool is_c2() { return true; };
#endif // TIERED

  void initialize();

  // Compilation entry point for methods
  void compile_method(ciEnv* env,
                      ciMethod* target,
                      int entry_bci);

  // sentinel value used to trigger backtracking in compile_method().
  static const char* retry_no_subsuming_loads();
  static const char* retry_no_escape_analysis();

  // Print compilation timers and statistics
  void print_timers();
};

#endif // SHARE_VM_OPTO_C2COMPILER_HPP
