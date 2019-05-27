
#ifndef OS_BSD_VM_THREAD_BSD_INLINE_HPP
#define OS_BSD_VM_THREAD_BSD_INLINE_HPP

#ifndef SHARE_VM_RUNTIME_THREAD_INLINE_HPP_SCOPE
#error "This file should only be included from thread.inline.hpp"
#endif

#include "runtime/atomic.hpp"
#include "runtime/prefetch.hpp"
#include "runtime/thread.hpp"
#include "runtime/threadLocalStorage.hpp"
#ifdef TARGET_OS_ARCH_bsd_x86
# include "atomic_bsd_x86.inline.hpp"
# include "orderAccess_bsd_x86.inline.hpp"
# include "prefetch_bsd_x86.inline.hpp"
#endif
#ifdef TARGET_OS_ARCH_bsd_zero
# include "atomic_bsd_zero.inline.hpp"
# include "orderAccess_bsd_zero.inline.hpp"
# include "prefetch_bsd_zero.inline.hpp"
#endif

// Contains inlined functions for class Thread and ThreadLocalStorage

inline void ThreadLocalStorage::pd_invalidate_all() {} // nothing to do

#endif // OS_BSD_VM_THREAD_BSD_INLINE_HPP
