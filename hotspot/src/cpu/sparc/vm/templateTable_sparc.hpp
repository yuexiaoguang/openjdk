#ifndef CPU_SPARC_VM_TEMPLATETABLE_SPARC_HPP
#define CPU_SPARC_VM_TEMPLATETABLE_SPARC_HPP

  static void prepare_invoke(int byte_no,
                             Register method,         // linked method (or i-klass)
                             Register ra,             // return address
                             Register index = noreg,  // itable index, MethodType, etc.
                             Register recv  = noreg,  // if caller wants to see it
                             Register flags = noreg   // if caller wants to test it
                             );
  // helper function
  static void invokevfinal_helper(Register Rcache, Register Rret);
  static void invokeinterface_object_method(Register RKlass, Register Rcall,
                                            Register Rret,
                                            Register Rflags);
  static void generate_vtable_call(Register Rrecv, Register Rindex, Register Rret);
  static void volatile_barrier(Assembler::Membar_mask_bits order_constraint);

#endif // CPU_SPARC_VM_TEMPLATETABLE_SPARC_HPP
