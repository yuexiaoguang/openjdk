
#ifndef OS_CPU_LINUX_ZERO_VM_GLOBALS_LINUX_ZERO_HPP
#define OS_CPU_LINUX_ZERO_VM_GLOBALS_LINUX_ZERO_HPP

//
// Set the default values for platform dependent flags used by the
// runtime system.  See globals.hpp for details of what they do.
//

define_pd_global(bool,  DontYieldALot,           false);
define_pd_global(intx,  ThreadStackSize,         1536);
#ifdef _LP64
define_pd_global(intx,  VMThreadStackSize,       1024);
#else
define_pd_global(intx,  VMThreadStackSize,       512);
#endif // _LP64
define_pd_global(intx,  CompilerThreadStackSize, 0);
define_pd_global(uintx, JVMInvokeMethodSlack,    8192);

// Used on 64 bit platforms for UseCompressedOops base address
define_pd_global(uintx, HeapBaseMinAddress,      2*G);

#endif // OS_CPU_LINUX_ZERO_VM_GLOBALS_LINUX_ZERO_HPP
