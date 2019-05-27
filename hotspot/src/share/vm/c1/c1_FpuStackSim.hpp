#ifndef SHARE_VM_C1_C1_FPUSTACKSIM_HPP
#define SHARE_VM_C1_C1_FPUSTACKSIM_HPP

#include "c1/c1_FrameMap.hpp"
#include "memory/allocation.hpp"

// Provides location for forward declaration of this class, which is
// only implemented on Intel
class FpuStackSim;

#ifdef TARGET_ARCH_x86
# include "c1_FpuStackSim_x86.hpp"
#endif
#ifdef TARGET_ARCH_sparc
# include "c1_FpuStackSim_sparc.hpp"
#endif
#ifdef TARGET_ARCH_arm
# include "c1_FpuStackSim_arm.hpp"
#endif
#ifdef TARGET_ARCH_ppc
# include "c1_FpuStackSim_ppc.hpp"
#endif


#endif // SHARE_VM_C1_C1_FPUSTACKSIM_HPP
