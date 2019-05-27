
#ifndef CPU_X86_VM_INTERPRETERGENERATOR_X86_HPP
#define CPU_X86_VM_INTERPRETERGENERATOR_X86_HPP


// Generation of Interpreter
//
  friend class AbstractInterpreterGenerator;

 private:

  address generate_normal_entry(bool synchronized);
  address generate_native_entry(bool synchronized);
  address generate_abstract_entry(void);
  address generate_math_entry(AbstractInterpreter::MethodKind kind);
  address generate_empty_entry(void);
  address generate_accessor_entry(void);
  address generate_Reference_get_entry();
  address generate_CRC32_update_entry();
  address generate_CRC32_updateBytes_entry(AbstractInterpreter::MethodKind kind);
  void lock_method(void);
  void generate_stack_overflow_check(void);

  void generate_counter_incr(Label* overflow, Label* profile_method, Label* profile_method_continue);
  void generate_counter_overflow(Label* do_continue);

#endif // CPU_X86_VM_INTERPRETERGENERATOR_X86_HPP
