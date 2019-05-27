#ifndef SHARE_VM_GC_IMPLEMENTATION_G1_G1MARKSWEEP_HPP
#define SHARE_VM_GC_IMPLEMENTATION_G1_G1MARKSWEEP_HPP

#include "gc_implementation/g1/g1CollectedHeap.inline.hpp"
#include "gc_implementation/g1/heapRegion.hpp"
#include "memory/genMarkSweep.hpp"
#include "memory/generation.hpp"
#include "memory/universe.hpp"
#include "oops/markOop.hpp"
#include "oops/oop.hpp"
#include "runtime/timer.hpp"
#include "utilities/growableArray.hpp"

class ReferenceProcessor;

// G1MarkSweep takes care of global mark-compact garbage collection for a
// G1CollectedHeap using a four-phase pointer forwarding algorithm.  All
// generations are assumed to support marking; those that can also support
// compaction.
//
// Class unloading will only occur when a full gc is invoked.


class G1MarkSweep : AllStatic {
  friend class VM_G1MarkSweep;
  friend class Scavenge;

 public:

  static void invoke_at_safepoint(ReferenceProcessor* rp,
                                  bool clear_all_softrefs);

  static STWGCTimer* gc_timer() { return GenMarkSweep::_gc_timer; }
  static SerialOldTracer* gc_tracer() { return GenMarkSweep::_gc_tracer; }

 private:

  // Mark live objects
  static void mark_sweep_phase1(bool& marked_for_deopt,
                                bool clear_all_softrefs);
  // Calculate new addresses
  static void mark_sweep_phase2();
  // Update pointers
  static void mark_sweep_phase3();
  // Move objects to new positions
  static void mark_sweep_phase4();

  static void allocate_stacks();
};

#endif // SHARE_VM_GC_IMPLEMENTATION_G1_G1MARKSWEEP_HPP
