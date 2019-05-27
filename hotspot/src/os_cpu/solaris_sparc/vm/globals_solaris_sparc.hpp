
#ifndef OS_CPU_SOLARIS_SPARC_VM_GLOBALS_SOLARIS_SPARC_HPP
#define OS_CPU_SOLARIS_SPARC_VM_GLOBALS_SOLARIS_SPARC_HPP

//
// Sets the default values for platform dependent flags used by the runtime system.
// (see globals.hpp)
//

define_pd_global(uintx, JVMInvokeMethodSlack,    12288);
define_pd_global(intx, CompilerThreadStackSize,  0);

// Used on 64 bit platforms for UseCompressedOops base address
#ifdef _LP64
// use 6G as default base address because by default the OS maps the application
// to 4G on Solaris-Sparc. This leaves at least 2G for the native heap.
define_pd_global(uintx, HeapBaseMinAddress,      CONST64(6)*G);
#else
define_pd_global(uintx, HeapBaseMinAddress,      2*G);
#endif



#endif // OS_CPU_SOLARIS_SPARC_VM_GLOBALS_SOLARIS_SPARC_HPP
