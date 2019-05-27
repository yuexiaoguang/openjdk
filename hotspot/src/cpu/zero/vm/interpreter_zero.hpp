
#ifndef CPU_ZERO_VM_INTERPRETER_ZERO_HPP
#define CPU_ZERO_VM_INTERPRETER_ZERO_HPP

 public:
  static void invoke_method(Method* method, address entry_point, TRAPS) {
    ((ZeroEntry *) entry_point)->invoke(method, THREAD);
  }
  static void invoke_osr(Method* method,
                         address   entry_point,
                         address   osr_buf,
                         TRAPS) {
    ((ZeroEntry *) entry_point)->invoke_osr(method, osr_buf, THREAD);
  }

 public:
  static int expr_index_at(int i) {
    return stackElementWords * i;
  }

  static int expr_offset_in_bytes(int i) {
    return stackElementSize * i;
  }

  static int local_index_at(int i) {
    assert(i <= 0, "local direction already negated");
    return stackElementWords * i;
  }

#endif // CPU_ZERO_VM_INTERPRETER_ZERO_HPP
