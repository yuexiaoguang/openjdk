
#ifndef CPU_X86_VM_INTERPRETER_X86_HPP
#define CPU_X86_VM_INTERPRETER_X86_HPP

 public:
  static Address::ScaleFactor stackElementScale() {
    return NOT_LP64(Address::times_4) LP64_ONLY(Address::times_8);
  }

  // Offset from rsp (which points to the last stack element)
  static int expr_offset_in_bytes(int i) { return stackElementSize * i; }

  // Stack index relative to tos (which points at value)
  static int expr_index_at(int i)        { return stackElementWords * i; }

  // Already negated by c++ interpreter
  static int local_index_at(int i) {
    assert(i <= 0, "local direction already negated");
    return stackElementWords * i;
  }

#endif // CPU_X86_VM_INTERPRETER_X86_HPP
