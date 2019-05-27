
#ifndef OS_SOLARIS_VM_THREAD_SOLARIS_INLINE_HPP
#define OS_SOLARIS_VM_THREAD_SOLARIS_INLINE_HPP

#ifndef SHARE_VM_RUNTIME_THREAD_INLINE_HPP_SCOPE
#error "This file should only be included from thread.inline.hpp"
#endif

#include "runtime/atomic.hpp"
#include "runtime/prefetch.hpp"
#include "runtime/thread.hpp"
#include "runtime/threadLocalStorage.hpp"
#ifdef TARGET_OS_ARCH_solaris_x86
# include "atomic_solaris_x86.inline.hpp"
# include "orderAccess_solaris_x86.inline.hpp"
# include "prefetch_solaris_x86.inline.hpp"
#endif
#ifdef TARGET_OS_ARCH_solaris_sparc
# include "atomic_solaris_sparc.inline.hpp"
# include "orderAccess_solaris_sparc.inline.hpp"
# include "prefetch_solaris_sparc.inline.hpp"
#endif

// Thread::current is "hot" it's called > 128K times in the 1st 500 msecs of
// startup.
// ThreadLocalStorage::thread is warm -- it's called > 16K times in the same
// period.   Thread::current() now calls ThreadLocalStorage::thread() directly.
// For SPARC, to avoid excessive register window spill-fill faults,
// we aggressively inline these routines.

inline Thread* ThreadLocalStorage::thread()  {
  // don't use specialized code if +UseMallocOnly -- may confuse Purify et al.
  debug_only(if (UseMallocOnly) return get_thread_slow(););

  uintptr_t raw = pd_raw_thread_id();
  int ix = pd_cache_index(raw);
  Thread* candidate = ThreadLocalStorage::_get_thread_cache[ix];
  if (candidate->self_raw_id() == raw) {
    // hit
    return candidate;
  } else {
    return ThreadLocalStorage::get_thread_via_cache_slowly(raw, ix);
  }
}

#endif // OS_SOLARIS_VM_THREAD_SOLARIS_INLINE_HPP
