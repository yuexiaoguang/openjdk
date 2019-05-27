#ifndef SHARE_VM_GC_IMPLEMENTATION_CONCURRENTMARKSWEEP_CMSCOLLECTORPOLICY_HPP
#define SHARE_VM_GC_IMPLEMENTATION_CONCURRENTMARKSWEEP_CMSCOLLECTORPOLICY_HPP

#include "memory/collectorPolicy.hpp"

class ConcurrentMarkSweepPolicy : public TwoGenerationCollectorPolicy {
 protected:
  void initialize_alignments();
  void initialize_generations();

 public:
  ConcurrentMarkSweepPolicy() {}

  ConcurrentMarkSweepPolicy* as_concurrent_mark_sweep_policy() { return this; }

  void initialize_gc_policy_counters();

  virtual void initialize_size_policy(size_t init_eden_size,
                                      size_t init_promo_size,
                                      size_t init_survivor_size);

  // Returns true if the incremental mode is enabled.
  virtual bool has_soft_ended_eden();
};

class ASConcurrentMarkSweepPolicy : public ConcurrentMarkSweepPolicy {
 public:

  // Initialize the jstat counters.  This method requires a
  // size policy.  The size policy is expected to be created
  // after the generations are fully initialized so the
  // initialization of the counters need to be done post
  // the initialization of the generations.
  void initialize_gc_policy_counters();

  virtual CollectorPolicy::Name kind() {
    return CollectorPolicy::ASConcurrentMarkSweepPolicyKind;
  }
};

#endif // SHARE_VM_GC_IMPLEMENTATION_CONCURRENTMARKSWEEP_CMSCOLLECTORPOLICY_HPP
