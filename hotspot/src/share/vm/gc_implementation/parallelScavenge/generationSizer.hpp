#ifndef SHARE_VM_GC_IMPLEMENTATION_PARALLELSCAVENGE_GENERATIONSIZER_HPP
#define SHARE_VM_GC_IMPLEMENTATION_PARALLELSCAVENGE_GENERATIONSIZER_HPP

#include "memory/collectorPolicy.hpp"

// There is a nice batch of tested generation sizing code in
// TwoGenerationCollectorPolicy. Lets reuse it!

class GenerationSizer : public TwoGenerationCollectorPolicy {
 private:

  void trace_gen_sizes(const char* const str);

  // The alignment used for boundary between young gen and old gen
  static size_t default_gen_alignment() { return 64 * K * HeapWordSize; }

 protected:

  void initialize_alignments();
  void initialize_flags();
  void initialize_size_info();
};
#endif // SHARE_VM_GC_IMPLEMENTATION_PARALLELSCAVENGE_GENERATIONSIZER_HPP
