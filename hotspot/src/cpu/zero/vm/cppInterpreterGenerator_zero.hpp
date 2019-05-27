
#ifndef CPU_ZERO_VM_CPPINTERPRETERGENERATOR_ZERO_HPP
#define CPU_ZERO_VM_CPPINTERPRETERGENERATOR_ZERO_HPP

 protected:
  MacroAssembler* assembler() const {
    return _masm;
  }

 public:
  static address generate_entry_impl(MacroAssembler* masm, address entry_point) {
    ZeroEntry *entry = (ZeroEntry *) masm->pc();
    masm->advance(sizeof(ZeroEntry));
    entry->set_entry_point(entry_point);
    return (address) entry;
  }

 protected:
  address generate_entry(address entry_point) {
        return generate_entry_impl(assembler(), entry_point);
  }

#endif // CPU_ZERO_VM_CPPINTERPRETERGENERATOR_ZERO_HPP
