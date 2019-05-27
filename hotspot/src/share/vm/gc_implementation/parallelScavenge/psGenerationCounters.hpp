#ifndef SHARE_VM_GC_IMPLEMENTATION_PARALLELSCAVENGE_PSGENERATIONCOUNTERS_HPP
#define SHARE_VM_GC_IMPLEMENTATION_PARALLELSCAVENGE_PSGENERATIONCOUNTERS_HPP

#include "gc_implementation/parallelScavenge/psVirtualspace.hpp"
#include "gc_implementation/shared/generationCounters.hpp"
#include "runtime/perfData.hpp"

// A PSGenerationCounter is a holder class for performance counters
// that track a generation

class PSGenerationCounters: public GenerationCounters {
  friend class VMStructs;

 private:
  PSVirtualSpace*      _ps_virtual_space;

 public:
  PSGenerationCounters(const char* name, int ordinal, int spaces,
                     PSVirtualSpace* v);

  void update_all() {
    assert(_virtual_space == NULL, "Only one should be in use");
    _current_size->set_value(_ps_virtual_space->committed_size());
  }
};

#endif // SHARE_VM_GC_IMPLEMENTATION_PARALLELSCAVENGE_PSGENERATIONCOUNTERS_HPP
