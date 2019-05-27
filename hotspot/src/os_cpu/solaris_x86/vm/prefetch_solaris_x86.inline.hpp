
#ifndef OS_CPU_SOLARIS_X86_VM_PREFETCH_SOLARIS_X86_INLINE_HPP
#define OS_CPU_SOLARIS_X86_VM_PREFETCH_SOLARIS_X86_INLINE_HPP

#include "runtime/prefetch.hpp"

extern "C" {
  void _Prefetch_read (void *loc, intx interval);
  void _Prefetch_write(void *loc, intx interval);
}

inline void Prefetch::read (void *loc, intx interval) {
#ifdef AMD64
  _Prefetch_read(loc, interval);
#endif // AMD64
}

// Use of this method should be gated by VM_Version::has_prefetchw.
inline void Prefetch::write(void *loc, intx interval) {
#ifdef AMD64
  _Prefetch_write(loc, interval);
#endif // AMD64
}

#endif // OS_CPU_SOLARIS_X86_VM_PREFETCH_SOLARIS_X86_INLINE_HPP
