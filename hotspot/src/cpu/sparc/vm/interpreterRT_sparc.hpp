#ifndef CPU_SPARC_VM_INTERPRETERRT_SPARC_HPP
#define CPU_SPARC_VM_INTERPRETERRT_SPARC_HPP

#include "memory/allocation.hpp"

static int binary_search(int key, LookupswitchPair* array, int n);

static address iload (JavaThread* thread);
static address aload (JavaThread* thread);
static address istore(JavaThread* thread);
static address astore(JavaThread* thread);
static address iinc  (JavaThread* thread);



// native method calls

class SignatureHandlerGenerator: public NativeSignatureIterator {
 private:
  MacroAssembler* _masm;

  void pass_word(int size_of_arg, int offset_in_arg);
  void pass_int()    { pass_word(1, 0); }
  void pass_long();
  void pass_double();
  void pass_float();
  void pass_object();

 public:
  // Creation
  SignatureHandlerGenerator(methodHandle method, CodeBuffer* buffer) : NativeSignatureIterator(method) {
    _masm = new MacroAssembler(buffer);
  }

  // Code generation
  void generate( uint64_t fingerprint );
};

#endif // CPU_SPARC_VM_INTERPRETERRT_SPARC_HPP
