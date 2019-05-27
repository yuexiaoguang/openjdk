
#ifndef OS_CPU_LINUX_SPARC_VM_PREFETCH_LINUX_SPARC_INLINE_HPP
#define OS_CPU_LINUX_SPARC_VM_PREFETCH_LINUX_SPARC_INLINE_HPP

#include "runtime/prefetch.hpp"

#if defined(COMPILER2) || defined(_LP64)

inline void Prefetch::read(void *loc, intx interval) {
  __asm__ volatile("prefetch [%0+%1], 0" : : "r" (loc), "r" (interval) : "memory" );
}

inline void Prefetch::write(void *loc, intx interval) {
  __asm__ volatile("prefetch [%0+%1], 2" : : "r" (loc), "r" (interval) : "memory" );
}

#else

inline void Prefetch::read (void *loc, intx interval) {}
inline void Prefetch::write(void *loc, intx interval) {}

#endif

#endif // OS_CPU_LINUX_SPARC_VM_PREFETCH_LINUX_SPARC_INLINE_HPP
