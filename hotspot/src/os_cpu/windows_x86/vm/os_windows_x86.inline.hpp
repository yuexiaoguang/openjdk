
#ifndef OS_CPU_WINDOWS_X86_VM_OS_WINDOWS_X86_INLINE_HPP
#define OS_CPU_WINDOWS_X86_VM_OS_WINDOWS_X86_INLINE_HPP

#include "runtime/os.hpp"

inline jlong os::rdtsc() {
  // 32 bit: 64 bit result in edx:eax
  // 64 bit: 64 bit value in rax
  uint64_t res;
  res = (uint64_t)__rdtsc();
  return (jlong)res;
}

#endif // OS_CPU_WINDOWS_X86_VM_OS_WINDOWS_X86_INLINE_HPP
