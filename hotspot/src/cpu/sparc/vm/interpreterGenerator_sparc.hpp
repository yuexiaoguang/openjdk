#ifndef CPU_SPARC_VM_INTERPRETERGENERATOR_SPARC_HPP
#define CPU_SPARC_VM_INTERPRETERGENERATOR_SPARC_HPP

 friend class AbstractInterpreterGenerator;

 private:

  address generate_normal_entry(bool synchronized);
  address generate_native_entry(bool synchronized);
  address generate_abstract_entry(void);
  address generate_math_entry(AbstractInterpreter::MethodKind kind);
  address generate_empty_entry(void);
  address generate_accessor_entry(void);
  address generate_Reference_get_entry(void);
  void lock_method(void);
  void save_native_result(void);
  void restore_native_result(void);

  void generate_counter_incr(Label* overflow, Label* profile_method, Label* profile_method_continue);
  void generate_counter_overflow(Label& Lcontinue);

#endif // CPU_SPARC_VM_INTERPRETERGENERATOR_SPARC_HPP
