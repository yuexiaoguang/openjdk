
#ifndef CPU_X86_VM_STUBROUTINES_X86_HPP
#define CPU_X86_VM_STUBROUTINES_X86_HPP

// This file holds the platform specific parts of the StubRoutines
// definition. See stubRoutines.hpp for a description on how to
// extend it.

 private:
  static address _verify_mxcsr_entry;
  // shuffle mask for fixing up 128-bit words consisting of big-endian 32-bit integers
  static address _key_shuffle_mask_addr;
  // masks and table for CRC32
  static uint64_t _crc_by128_masks[];
  static juint    _crc_table[];

 public:
  static address verify_mxcsr_entry()    { return _verify_mxcsr_entry; }
  static address key_shuffle_mask_addr() { return _key_shuffle_mask_addr; }
  static address crc_by128_masks_addr()  { return (address)_crc_by128_masks; }

#endif // CPU_X86_VM_STUBROUTINES_X86_32_HPP
