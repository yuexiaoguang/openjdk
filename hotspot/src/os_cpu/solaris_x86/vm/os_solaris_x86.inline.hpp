
#ifndef OS_CPU_SOLARIS_X86_VM_OS_SOLARIS_X86_INLINE_HPP
#define OS_CPU_SOLARIS_X86_VM_OS_SOLARIS_X86_INLINE_HPP

#include "runtime/os.hpp"

inline jlong os::rdtsc() { return _raw_rdtsc(); }

#endif // OS_CPU_SOLARIS_X86_VM_OS_SOLARIS_X86_INLINE_HPP
