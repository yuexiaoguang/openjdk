#ifndef CPU_SPARC_VM_VMREG_SPARC_INLINE_HPP
#define CPU_SPARC_VM_VMREG_SPARC_INLINE_HPP

inline VMReg RegisterImpl::as_VMReg() {
  if( this==noreg ) return VMRegImpl::Bad();
  return VMRegImpl::as_VMReg(encoding() << 1 );
}

inline VMReg FloatRegisterImpl::as_VMReg() { return VMRegImpl::as_VMReg( ConcreteRegisterImpl::max_gpr + encoding() ); }


inline bool VMRegImpl::is_Register() { return value() >= 0 && value() < ConcreteRegisterImpl::max_gpr; }
inline bool VMRegImpl::is_FloatRegister() { return value() >= ConcreteRegisterImpl::max_gpr &&
                                                   value() < ConcreteRegisterImpl::max_fpr; }
inline Register VMRegImpl::as_Register() {

  assert( is_Register() && is_even(value()), "even-aligned GPR name" );
  // Yuk
  return ::as_Register(value()>>1);
}

inline FloatRegister VMRegImpl::as_FloatRegister() {
  assert( is_FloatRegister(), "must be" );
  // Yuk
  return ::as_FloatRegister( value() - ConcreteRegisterImpl::max_gpr );
}

inline   bool VMRegImpl::is_concrete() {
  assert(is_reg(), "must be");
  int v = value();
  if ( v  <  ConcreteRegisterImpl::max_gpr ) {
    return is_even(v);
  }
  // F0..F31
  if ( v <= ConcreteRegisterImpl::max_gpr + 31) return true;
  if ( v <  ConcreteRegisterImpl::max_fpr) {
    return is_even(v);
  }
  assert(false, "what register?");
  return false;
}

#endif // CPU_SPARC_VM_VMREG_SPARC_INLINE_HPP
