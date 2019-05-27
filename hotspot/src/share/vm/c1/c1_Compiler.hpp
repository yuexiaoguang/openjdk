#ifndef SHARE_VM_C1_C1_COMPILER_HPP
#define SHARE_VM_C1_C1_COMPILER_HPP

#include "compiler/abstractCompiler.hpp"

// There is one instance of the Compiler per CompilerThread.

class Compiler: public AbstractCompiler {
 private:
  static void init_c1_runtime();
  BufferBlob* init_buffer_blob();

 public:
  // Creation
  Compiler();
  ~Compiler();

  // Name of this compiler
  virtual const char* name()                     { return "C1"; }

  virtual bool is_c1()                           { return true; };

  // Missing feature tests
  virtual bool supports_native()                 { return true; }
  virtual bool supports_osr   ()                 { return true; }

  // Initialization
  virtual void initialize();

  // Compilation entry point for methods
  virtual void compile_method(ciEnv* env, ciMethod* target, int entry_bci);

  // Print compilation timers and statistics
  virtual void print_timers();
};

#endif // SHARE_VM_C1_C1_COMPILER_HPP
