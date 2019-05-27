
#ifndef CPU_X86_VM_CPPINTERPRETERGENERATOR_X86_HPP
#define CPU_X86_VM_CPPINTERPRETERGENERATOR_X86_HPP

 protected:

#if 0
  address generate_asm_interpreter_entry(bool synchronized);
  address generate_native_entry(bool synchronized);
  address generate_abstract_entry(void);
  address generate_math_entry(AbstractInterpreter::MethodKind kind);
  address generate_empty_entry(void);
  address generate_accessor_entry(void);
  address generate_Reference_get_entry(void);
  void lock_method(void);
  void generate_stack_overflow_check(void);

  void generate_counter_incr(Label* overflow, Label* profile_method, Label* profile_method_continue);
  void generate_counter_overflow(Label* do_continue);
#endif

  void generate_more_monitors();
  void generate_deopt_handling();
  address generate_interpreter_frame_manager(bool synchronized); // C++ interpreter only
  void generate_compute_interpreter_state(const Register state,
                                          const Register prev_state,
                                          const Register sender_sp,
                                          bool native); // C++ interpreter only

#endif // CPU_X86_VM_CPPINTERPRETERGENERATOR_X86_HPP
