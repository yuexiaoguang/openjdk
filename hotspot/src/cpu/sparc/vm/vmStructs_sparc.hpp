#ifndef CPU_SPARC_VM_VMSTRUCTS_SPARC_HPP
#define CPU_SPARC_VM_VMSTRUCTS_SPARC_HPP

// These are the CPU-specific fields, types and integer
// constants required by the Serviceability Agent. This file is
// referenced by vmStructs.cpp.

#define VM_STRUCTS_CPU(nonstatic_field, static_field, unchecked_nonstatic_field, volatile_nonstatic_field, nonproduct_nonstatic_field, c2_nonstatic_field, unchecked_c1_static_field, unchecked_c2_static_field)            \
 \
  /******************************/                                                                                                   \
  /* JavaCallWrapper            */                                                                                                   \
  /******************************/                                                                                                   \
  /******************************/                                                                                                   \
  /* JavaFrameAnchor            */                                                                                                   \
  /******************************/                                                                                                   \
  volatile_nonstatic_field(JavaFrameAnchor,     _flags,                                          int)

#define VM_TYPES_CPU(declare_type, declare_toplevel_type, declare_oop_type, declare_integer_type, declare_unsigned_integer_type, declare_c1_toplevel_type, declare_c2_type, declare_c2_toplevel_type)


#define VM_INT_CONSTANTS_CPU(declare_constant, declare_preprocessor_constant, declare_c1_constant, declare_c2_constant, declare_c2_preprocessor_constant)                                                              \
  /******************************/                                        \
  /* Register numbers (C2 only) */                                        \
  /******************************/                                        \
                                                                          \
  declare_c2_constant(R_L0_num)                                           \
  declare_c2_constant(R_L1_num)                                           \
  declare_c2_constant(R_L2_num)                                           \
  declare_c2_constant(R_L3_num)                                           \
  declare_c2_constant(R_L4_num)                                           \
  declare_c2_constant(R_L5_num)                                           \
  declare_c2_constant(R_L6_num)                                           \
  declare_c2_constant(R_L7_num)                                           \
  declare_c2_constant(R_I0_num)                                           \
  declare_c2_constant(R_I1_num)                                           \
  declare_c2_constant(R_I2_num)                                           \
  declare_c2_constant(R_I3_num)                                           \
  declare_c2_constant(R_I4_num)                                           \
  declare_c2_constant(R_I5_num)                                           \
  declare_c2_constant(R_FP_num)                                           \
  declare_c2_constant(R_I7_num)                                           \
  declare_c2_constant(R_O0_num)                                           \
  declare_c2_constant(R_O1_num)                                           \
  declare_c2_constant(R_O2_num)                                           \
  declare_c2_constant(R_O3_num)                                           \
  declare_c2_constant(R_O4_num)                                           \
  declare_c2_constant(R_O5_num)                                           \
  declare_c2_constant(R_SP_num)                                           \
  declare_c2_constant(R_O7_num)                                           \
  declare_c2_constant(R_G0_num)                                           \
  declare_c2_constant(R_G1_num)                                           \
  declare_c2_constant(R_G2_num)                                           \
  declare_c2_constant(R_G3_num)                                           \
  declare_c2_constant(R_G4_num)                                           \
  declare_c2_constant(R_G5_num)                                           \
  declare_c2_constant(R_G6_num)                                           \
  declare_c2_constant(R_G7_num)

#define VM_LONG_CONSTANTS_CPU(declare_constant, declare_preprocessor_constant, declare_c1_constant, declare_c2_constant, declare_c2_preprocessor_constant)

#endif // CPU_SPARC_VM_VMSTRUCTS_SPARC_HPP
