#ifndef CPU_SPARC_VM_CPPINTERPRETERGENERATOR_SPARC_HPP
#define CPU_SPARC_VM_CPPINTERPRETERGENERATOR_SPARC_HPP

  static address frame_manager_return;
  static address frame_manager_sync_return;


  void generate_more_monitors();
  void generate_deopt_handling();
  void adjust_callers_stack(Register args);
  void generate_compute_interpreter_state(const Register state,
                                          const Register prev_state,
                                          bool native);

#endif // CPU_SPARC_VM_CPPINTERPRETERGENERATOR_SPARC_HPP
