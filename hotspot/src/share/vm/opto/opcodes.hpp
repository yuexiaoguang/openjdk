#ifndef SHARE_VM_OPTO_OPCODES_HPP
#define SHARE_VM_OPTO_OPCODES_HPP

// Build a big enum of class names to give them dense integer indices
#define macro(x) Op_##x,
enum Opcodes {
  Op_Node = 0,
  macro(Set)                    // Instruction selection match rule
  macro(RegN)                   // Machine narrow oop register
  macro(RegI)                   // Machine integer register
  macro(RegP)                   // Machine pointer register
  macro(RegF)                   // Machine float   register
  macro(RegD)                   // Machine double  register
  macro(RegL)                   // Machine long    register
  macro(VecS)                   // Machine vectors register
  macro(VecD)                   // Machine vectord register
  macro(VecX)                   // Machine vectorx register
  macro(VecY)                   // Machine vectory register
  macro(RegFlags)               // Machine flags   register
  _last_machine_leaf,           // Split between regular opcodes and machine
#include "classes.hpp"
  _last_opcode
};
#undef macro

// Table of names, indexed by Opcode
extern const char *NodeClassNames[];

#endif // SHARE_VM_OPTO_OPCODES_HPP
