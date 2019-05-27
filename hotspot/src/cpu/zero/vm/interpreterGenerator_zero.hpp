
#ifndef CPU_ZERO_VM_INTERPRETERGENERATOR_ZERO_HPP
#define CPU_ZERO_VM_INTERPRETERGENERATOR_ZERO_HPP

  // Generation of Interpreter
  //
  friend class AbstractInterpreterGenerator;

 private:
  address generate_normal_entry(bool synchronized);
  address generate_native_entry(bool synchronized);
  address generate_abstract_entry();
  address generate_math_entry(AbstractInterpreter::MethodKind kind);
  address generate_empty_entry();
  address generate_accessor_entry();
  address generate_Reference_get_entry();

#endif // CPU_ZERO_VM_INTERPRETERGENERATOR_ZERO_HPP
