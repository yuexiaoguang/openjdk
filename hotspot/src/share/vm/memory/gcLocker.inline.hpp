#ifndef SHARE_VM_MEMORY_GCLOCKER_INLINE_HPP
#define SHARE_VM_MEMORY_GCLOCKER_INLINE_HPP

#include "memory/gcLocker.hpp"

inline void GC_locker::lock() {
  // cast away volatile
  Atomic::inc(&_lock_count);
  CHECK_UNHANDLED_OOPS_ONLY(
    if (CheckUnhandledOops) { Thread::current()->_gc_locked_out_count++; })
  assert(Universe::heap() == NULL ||
         !Universe::heap()->is_gc_active(), "locking failed");
}

inline void GC_locker::unlock() {
  // cast away volatile
  Atomic::dec(&_lock_count);
  CHECK_UNHANDLED_OOPS_ONLY(
    if (CheckUnhandledOops) { Thread::current()->_gc_locked_out_count--; })
}

inline void GC_locker::lock_critical(JavaThread* thread) {
  if (!thread->in_critical()) {
    if (needs_gc()) {
      // jni_lock call calls enter_critical under the lock so that the
      // global lock count and per thread count are in agreement.
      jni_lock(thread);
      return;
    }
    increment_debug_jni_lock_count();
  }
  thread->enter_critical();
}

inline void GC_locker::unlock_critical(JavaThread* thread) {
  if (thread->in_last_critical()) {
    if (needs_gc()) {
      // jni_unlock call calls exit_critical under the lock so that
      // the global lock count and per thread count are in agreement.
      jni_unlock(thread);
      return;
    }
    decrement_debug_jni_lock_count();
  }
  thread->exit_critical();
}

#endif // SHARE_VM_MEMORY_GCLOCKER_INLINE_HPP
