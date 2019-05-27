
#ifndef CPU_X86_VM_TEMPLATEINTERPRETER_X86_HPP
#define CPU_X86_VM_TEMPLATEINTERPRETER_X86_HPP


  protected:

  // Size of interpreter code.  Increase if too small.  Interpreter will
  // fail with a guarantee ("not enough space for interpreter generation");
  // if too small.
  // Run with +PrintInterpreter to get the VM to print out the size.
  // Max size with JVMTI
#ifdef AMD64
  const static int InterpreterCodeSize = 256 * 1024;
#else
  const static int InterpreterCodeSize = 224 * 1024;
#endif // AMD64

#endif // CPU_X86_VM_TEMPLATEINTERPRETER_X86_HPP
