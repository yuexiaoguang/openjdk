
#ifndef OS_CPU_SOLARIS_X86_VM_GLOBALS_SOLARIS_X86_HPP
#define OS_CPU_SOLARIS_X86_VM_GLOBALS_SOLARIS_X86_HPP

// Sets the default values for platform dependent flags used by the runtime system.
// (see globals.hpp)

define_pd_global(bool, DontYieldALot,            true); // Determined in the design center
#ifdef AMD64
define_pd_global(intx, ThreadStackSize,          1024); // 0 => use system default
define_pd_global(intx, VMThreadStackSize,        1024);
define_pd_global(uintx,JVMInvokeMethodSlack,     8*K);
#else
// ThreadStackSize 320 allows a couple of test cases to run while
// keeping the number of threads that can be created high.
define_pd_global(intx, ThreadStackSize,          320);
define_pd_global(intx, VMThreadStackSize,        512);
define_pd_global(uintx,JVMInvokeMethodSlack,     10*K);
#endif // AMD64

define_pd_global(intx, CompilerThreadStackSize,  0);

// Used on 64 bit platforms for UseCompressedOops base address
define_pd_global(uintx,HeapBaseMinAddress,       2*G);

#endif // OS_CPU_SOLARIS_X86_VM_GLOBALS_SOLARIS_X86_HPP
