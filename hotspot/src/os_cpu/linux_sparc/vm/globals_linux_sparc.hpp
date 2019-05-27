
#ifndef OS_CPU_LINUX_SPARC_VM_GLOBALS_LINUX_SPARC_HPP
#define OS_CPU_LINUX_SPARC_VM_GLOBALS_LINUX_SPARC_HPP

//
// Sets the default values for platform dependent flags used by the
// runtime system.  (see globals.hpp)
//

define_pd_global(uintx, JVMInvokeMethodSlack,    12288);
define_pd_global(intx, CompilerThreadStackSize,  0);

// Used on 64 bit platforms for UseCompressedOops base address
define_pd_global(uintx, HeapBaseMinAddress,      CONST64(4)*G);

#endif // OS_CPU_LINUX_SPARC_VM_GLOBALS_LINUX_SPARC_HPP
