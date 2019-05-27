#ifndef SHARE_VM_MEMORY_GENMARKSWEEP_HPP
#define SHARE_VM_MEMORY_GENMARKSWEEP_HPP

#include "gc_implementation/shared/markSweep.hpp"

class GenMarkSweep : public MarkSweep {
  friend class VM_MarkSweep;
  friend class G1MarkSweep;
 public:
  static void invoke_at_safepoint(int level, ReferenceProcessor* rp,
                                  bool clear_all_softrefs);

 private:

  // Mark live objects
  static void mark_sweep_phase1(int level, bool clear_all_softrefs);
  // Calculate new addresses
  static void mark_sweep_phase2();
  // Update pointers
  static void mark_sweep_phase3(int level);
  // Move objects to new positions
  static void mark_sweep_phase4();

  // Temporary data structures for traversal and storing/restoring marks
  static void allocate_stacks();
  static void deallocate_stacks();
};

#endif // SHARE_VM_MEMORY_GENMARKSWEEP_HPP
