
#ifndef OS_CPU_WINDOWS_X86_VM_GLOBALS_WINDOWS_X86_HPP
#define OS_CPU_WINDOWS_X86_VM_GLOBALS_WINDOWS_X86_HPP

// Sets the default values for platform dependent flags used by the runtime system.
// (see globals.hpp)

define_pd_global(bool, DontYieldALot,            false);

// Default stack size on Windows is determined by the executable (java.exe
// has a default value of 320K/1MB [32bit/64bit]). Depending on Windows version, changing
// ThreadStackSize to non-zero may have significant impact on memory usage.
// See comments in os_windows.cpp.
define_pd_global(intx, ThreadStackSize,          0); // 0 => use system default
define_pd_global(intx, VMThreadStackSize,        0); // 0 => use system default

#ifdef ASSERT
define_pd_global(intx, CompilerThreadStackSize,  1024);
#else
define_pd_global(intx, CompilerThreadStackSize,  0);
#endif

define_pd_global(uintx, JVMInvokeMethodSlack,    8192);

// Used on 64 bit platforms for UseCompressedOops base address
define_pd_global(uintx, HeapBaseMinAddress,      2*G);

#endif // OS_CPU_WINDOWS_X86_VM_GLOBALS_WINDOWS_X86_HPP
