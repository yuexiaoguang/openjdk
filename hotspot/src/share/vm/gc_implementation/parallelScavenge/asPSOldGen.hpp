#ifndef SHARE_VM_GC_IMPLEMENTATION_PARALLELSCAVENGE_ASPSOLDGEN_HPP
#define SHARE_VM_GC_IMPLEMENTATION_PARALLELSCAVENGE_ASPSOLDGEN_HPP

#include "gc_implementation/parallelScavenge/objectStartArray.hpp"
#include "gc_implementation/parallelScavenge/psOldGen.hpp"
#include "gc_implementation/parallelScavenge/psVirtualspace.hpp"
#include "gc_implementation/shared/generationCounters.hpp"
#include "gc_implementation/shared/mutableSpace.hpp"
#include "gc_implementation/shared/spaceCounters.hpp"

class ASPSOldGen : public PSOldGen {
  friend class VMStructs;
  size_t _gen_size_limit;  // Largest size the generation's reserved size
                           // can grow.
 public:
  ASPSOldGen(size_t initial_byte_size,
             size_t minimum_byte_size,
             size_t byte_size_limit,
             const char* gen_name, int level);
  ASPSOldGen(PSVirtualSpace* vs,
             size_t initial_byte_size,
             size_t minimum_byte_size,
             size_t byte_size_limit,
             const char* gen_name, int level);
  size_t gen_size_limit()               { return _gen_size_limit; }
  size_t max_gen_size()                 { return _reserved.byte_size(); }
  void set_gen_size_limit(size_t v)     { _gen_size_limit = v; }

  virtual void initialize_work(const char* perf_data_name, int level);

  // After a shrink or expand reset the generation
  void reset_after_change();

  // Return number of bytes that the virtual space in the generation is willing
  // to expand or contract.  The results from these methods should feed into the
  // decisions about adjusting the virtual space.
  size_t available_for_expansion();
  size_t available_for_contraction();

  // Accessors
  void set_reserved(MemRegion v) { _reserved = v; }

  // Debugging support
  virtual const char* short_name() const { return "ASPSOldGen"; }
};

#endif // SHARE_VM_GC_IMPLEMENTATION_PARALLELSCAVENGE_ASPSOLDGEN_HPP
