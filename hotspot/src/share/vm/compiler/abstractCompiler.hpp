#ifndef SHARE_VM_COMPILER_ABSTRACTCOMPILER_HPP
#define SHARE_VM_COMPILER_ABSTRACTCOMPILER_HPP

#include "ci/compilerInterface.hpp"

class AbstractCompiler : public CHeapObj<mtCompiler> {
 private:
  volatile int _num_compiler_threads;

 protected:
  volatile int _compiler_state;
  // Used for tracking global state of compiler runtime initialization
  enum { uninitialized, initializing, initialized, failed, shut_down };

  // This method returns true for the first compiler thread that reaches that methods.
  // This thread will initialize the compiler runtime.
  bool should_perform_init();

 public:
  AbstractCompiler() : _compiler_state(uninitialized), _num_compiler_threads(0) {}

  // This function determines the compiler thread that will perform the
  // shutdown of the corresponding compiler runtime.
  bool should_perform_shutdown();

  // Name of this compiler
  virtual const char* name() = 0;

  // Missing feature tests
  virtual bool supports_native()                 { return true; }
  virtual bool supports_osr   ()                 { return true; }
  virtual bool can_compile_method(methodHandle method)  { return true; }
#if defined(TIERED) || ( !defined(COMPILER1) && !defined(COMPILER2) && !defined(SHARK))
  virtual bool is_c1   ()                        { return false; }
  virtual bool is_c2   ()                        { return false; }
  virtual bool is_shark()                        { return false; }
#else
#ifdef COMPILER1
  bool is_c1   ()                                { return true; }
  bool is_c2   ()                                { return false; }
  bool is_shark()                                { return false; }
#endif // COMPILER1
#ifdef COMPILER2
  bool is_c1   ()                                { return false; }
  bool is_c2   ()                                { return true; }
  bool is_shark()                                { return false; }
#endif // COMPILER2
#ifdef SHARK
  bool is_c1   ()                                { return false; }
  bool is_c2   ()                                { return false; }
  bool is_shark()                                { return true; }
#endif // SHARK
#endif // TIERED

  // Customization
  virtual void initialize () = 0;

  void set_num_compiler_threads(int num) { _num_compiler_threads = num;  }
  int num_compiler_threads()             { return _num_compiler_threads; }

  // Get/set state of compiler objects
  bool is_initialized()           { return _compiler_state == initialized; }
  bool is_failed     ()           { return _compiler_state == failed;}
  void set_state     (int state);
  void set_shut_down ()           { set_state(shut_down); }
  // Compilation entry point for methods
  virtual void compile_method(ciEnv* env, ciMethod* target, int entry_bci) {
    ShouldNotReachHere();
  }


  // Print compilation timers and statistics
  virtual void print_timers() {
    ShouldNotReachHere();
  }
};

#endif // SHARE_VM_COMPILER_ABSTRACTCOMPILER_HPP
