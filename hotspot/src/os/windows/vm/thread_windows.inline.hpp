
#ifndef OS_WINDOWS_VM_THREAD_WINDOWS_INLINE_HPP
#define OS_WINDOWS_VM_THREAD_WINDOWS_INLINE_HPP

#ifndef SHARE_VM_RUNTIME_THREAD_INLINE_HPP_SCOPE
#error "This file should only be included from thread.inline.hpp"
#endif

#include "runtime/atomic.hpp"
#include "runtime/prefetch.hpp"
#include "runtime/thread.hpp"
#include "runtime/threadLocalStorage.hpp"
#ifdef TARGET_OS_ARCH_windows_x86
# include "atomic_windows_x86.inline.hpp"
# include "orderAccess_windows_x86.inline.hpp"
# include "prefetch_windows_x86.inline.hpp"
#endif

// Contains inlined functions for class Thread and ThreadLocalStorage

inline void ThreadLocalStorage::pd_invalidate_all()            { return; }

#endif // OS_WINDOWS_VM_THREAD_WINDOWS_INLINE_HPP
