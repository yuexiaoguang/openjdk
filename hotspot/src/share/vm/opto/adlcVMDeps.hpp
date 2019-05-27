#ifndef SHARE_VM_OPTO_ADLCVMDEPS_HPP
#define SHARE_VM_OPTO_ADLCVMDEPS_HPP

// adlcVMDeps.hpp is used by both adlc and vm builds.
// Only include allocation.hpp when we're not building adlc.
#ifndef SHARE_VM_ADLC_ARENA_HPP
#include "memory/allocation.hpp"
#endif

// Declare commonly known constant and data structures between the
// ADLC and the VM
//

class AdlcVMDeps : public AllStatic {
 public:
  // Mirror of TypeFunc types
  enum { Control, I_O, Memory, FramePtr, ReturnAdr, Parms };

  enum Cisc_Status { Not_cisc_spillable = -1 };

  // Mirror of OptoReg::Name names
  enum Name {
    Physical = 0                // Start of physical regs
  };

  // relocInfo
  static const char* oop_reloc_type()  { return "relocInfo::oop_type"; }
  static const char* none_reloc_type() { return "relocInfo::none"; }
};

#endif // SHARE_VM_OPTO_ADLCVMDEPS_HPP
