#ifndef SHARE_VM_GC_IMPLEMENTATION_G1_CONCURRENTMARKTHREAD_INLINE_HPP
#define SHARE_VM_GC_IMPLEMENTATION_G1_CONCURRENTMARKTHREAD_INLINE_HPP

#include "gc_implementation/g1/concurrentMark.hpp"
#include "gc_implementation/g1/concurrentMarkThread.hpp"

  // Total virtual time so far.
inline double ConcurrentMarkThread::vtime_accum() {
  return _vtime_accum + _cm->all_task_accum_vtime();
}

// Marking virtual time so far
inline double ConcurrentMarkThread::vtime_mark_accum() {
  return _vtime_mark_accum + _cm->all_task_accum_vtime();
}

#endif // SHARE_VM_GC_IMPLEMENTATION_G1_CONCURRENTMARKTHREAD_INLINE_HPP
