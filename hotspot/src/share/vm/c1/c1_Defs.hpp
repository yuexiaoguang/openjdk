#ifndef SHARE_VM_C1_C1_DEFS_HPP
#define SHARE_VM_C1_C1_DEFS_HPP

#include "utilities/globalDefinitions.hpp"
#ifdef TARGET_ARCH_x86
# include "register_x86.hpp"
#endif
#ifdef TARGET_ARCH_sparc
# include "register_sparc.hpp"
#endif
#ifdef TARGET_ARCH_zero
# include "register_zero.hpp"
#endif
#ifdef TARGET_ARCH_arm
# include "register_arm.hpp"
#endif
#ifdef TARGET_ARCH_ppc
# include "register_ppc.hpp"
#endif

// set frame size and return address offset to these values in blobs
// (if the compiled frame uses ebp as link pointer on IA; otherwise,
// the frame size must be fixed)
enum {
  no_frame_size            = -1
};


#ifdef TARGET_ARCH_x86
# include "c1_Defs_x86.hpp"
#endif
#ifdef TARGET_ARCH_sparc
# include "c1_Defs_sparc.hpp"
#endif
#ifdef TARGET_ARCH_arm
# include "c1_Defs_arm.hpp"
#endif
#ifdef TARGET_ARCH_ppc
# include "c1_Defs_ppc.hpp"
#endif


// native word offsets from memory address
enum {
  lo_word_offset_in_bytes = pd_lo_word_offset_in_bytes,
  hi_word_offset_in_bytes = pd_hi_word_offset_in_bytes
};


// the processor may require explicit rounding operations to implement the strictFP mode
enum {
  strict_fp_requires_explicit_rounding = pd_strict_fp_requires_explicit_rounding
};


// for debug info: a float value in a register may be saved in double precision by runtime stubs
enum {
  float_saved_as_double = pd_float_saved_as_double
};

#endif // SHARE_VM_C1_C1_DEFS_HPP
