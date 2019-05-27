#ifndef SHARE_VM_GC_IMPLEMENTATION_PARNEW_VMSTRUCTS_PARNEW_HPP
#define SHARE_VM_GC_IMPLEMENTATION_PARNEW_VMSTRUCTS_PARNEW_HPP

#define VM_TYPES_PARNEW(declare_type)                                     \
           declare_type(ParNewGeneration,             DefNewGeneration)

#define VM_INT_CONSTANTS_PARNEW(declare_constant)                         \
  declare_constant(Generation::ParNew)

#endif // SHARE_VM_GC_IMPLEMENTATION_PARNEW_VMSTRUCTS_PARNEW_HPP
