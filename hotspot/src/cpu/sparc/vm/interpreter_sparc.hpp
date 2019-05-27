#ifndef CPU_SPARC_VM_INTERPRETER_SPARC_HPP
#define CPU_SPARC_VM_INTERPRETER_SPARC_HPP

 public:

  static int expr_offset_in_bytes(int i) { return stackElementSize * i + wordSize; }

  // Stack index relative to tos (which points at value)
  static int expr_index_at(int i)        { return stackElementWords * i; }

  // Already negated by c++ interpreter
  static int local_index_at(int i) {
    assert(i <= 0, "local direction already negated");
    return stackElementWords * i;
  }

#endif // CPU_SPARC_VM_INTERPRETER_SPARC_HPP
