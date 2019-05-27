#ifndef SHARE_VM_INTERPRETER_INTERPRETER_HPP
#define SHARE_VM_INTERPRETER_INTERPRETER_HPP

#include "code/stubs.hpp"
#include "interpreter/cppInterpreter.hpp"
#include "interpreter/templateInterpreter.hpp"
#ifdef ZERO
#ifdef TARGET_ARCH_zero
# include "entry_zero.hpp"
#endif
#endif

// This file contains the platform-independent parts
// of the interpreter and the interpreter generator.

//------------------------------------------------------------------------------------------------------------------------
// An InterpreterCodelet is a piece of interpreter code. All
// interpreter code is generated into little codelets which
// contain extra information for debugging and printing purposes.

class InterpreterCodelet: public Stub {
  friend class VMStructs;
 private:
  int         _size;                             // the size in bytes
  const char* _description;                      // a description of the codelet, for debugging & printing
  Bytecodes::Code _bytecode;                     // associated bytecode if any
  DEBUG_ONLY(CodeStrings _strings;)              // Comments for annotating assembler output.

 public:
  // Initialization/finalization
  void    initialize(int size,
                     CodeStrings& strings)       { _size = size; DEBUG_ONLY(_strings.assign(strings);) }
  void    finalize()                             { ShouldNotCallThis(); }

  // General info/converters
  int     size() const                           { return _size; }
  static  int code_size_to_size(int code_size)   { return round_to(sizeof(InterpreterCodelet), CodeEntryAlignment) + code_size; }

  // Code info
  address code_begin() const                     { return (address)this + round_to(sizeof(InterpreterCodelet), CodeEntryAlignment); }
  address code_end() const                       { return (address)this + size(); }

  // Debugging
  void    verify();
  void    print_on(outputStream* st) const;
  void    print() const { print_on(tty); }

  // Interpreter-specific initialization
  void    initialize(const char* description, Bytecodes::Code bytecode);

  // Interpreter-specific attributes
  int         code_size() const                  { return code_end() - code_begin(); }
  const char* description() const                { return _description; }
  Bytecodes::Code bytecode() const               { return _bytecode; }
};

// Define a prototype interface
DEF_STUB_INTERFACE(InterpreterCodelet);


//------------------------------------------------------------------------------------------------------------------------
// A CodeletMark serves as an automatic creator/initializer for Codelets
// (As a subclass of ResourceMark it automatically GC's the allocated
// code buffer and assemblers).

class CodeletMark: ResourceMark {
 private:
  InterpreterCodelet*         _clet;
  InterpreterMacroAssembler** _masm;
  CodeBuffer                  _cb;

  int codelet_size() {
    // Request the whole code buffer (minus a little for alignment).
    // The commit call below trims it back for each codelet.
    int codelet_size = AbstractInterpreter::code()->available_space() - 2*K;

    // Guarantee there's a little bit of code space left.
    guarantee (codelet_size > 0 && (size_t)codelet_size >  2*K,
               "not enough space for interpreter generation");

    return codelet_size;
  }

 public:
  CodeletMark(
    InterpreterMacroAssembler*& masm,
    const char* description,
    Bytecodes::Code bytecode = Bytecodes::_illegal):
    _clet((InterpreterCodelet*)AbstractInterpreter::code()->request(codelet_size())),
    _cb(_clet->code_begin(), _clet->code_size())

  { // request all space (add some slack for Codelet data)
    assert (_clet != NULL, "we checked not enough space already");

    // initialize Codelet attributes
    _clet->initialize(description, bytecode);
    // create assembler for code generation
    masm  = new InterpreterMacroAssembler(&_cb);
    _masm = &masm;
  }

  ~CodeletMark() {
    // align so printing shows nop's instead of random code at the end (Codelets are aligned)
    (*_masm)->align(wordSize);
    // make sure all code is in code buffer
    (*_masm)->flush();


    // commit Codelet
    AbstractInterpreter::code()->commit((*_masm)->code()->pure_insts_size(), (*_masm)->code()->strings());
    // make sure nobody can use _masm outside a CodeletMark lifespan
    *_masm = NULL;
  }
};

// Wrapper classes to produce Interpreter/InterpreterGenerator from either
// the c++ interpreter or the template interpreter.

class Interpreter: public CC_INTERP_ONLY(CppInterpreter) NOT_CC_INTERP(TemplateInterpreter) {

  public:
  // Debugging/printing
  static InterpreterCodelet* codelet_containing(address pc)     { return (InterpreterCodelet*)_code->stub_containing(pc); }
#ifdef TARGET_ARCH_x86
# include "interpreter_x86.hpp"
#endif
#ifdef TARGET_ARCH_sparc
# include "interpreter_sparc.hpp"
#endif
#ifdef TARGET_ARCH_zero
# include "interpreter_zero.hpp"
#endif
#ifdef TARGET_ARCH_arm
# include "interpreter_arm.hpp"
#endif
#ifdef TARGET_ARCH_ppc
# include "interpreter_ppc.hpp"
#endif

};

#endif // SHARE_VM_INTERPRETER_INTERPRETER_HPP
