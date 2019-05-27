#ifndef SHARE_VM_GC_IMPLEMENTATION_PARALLELSCAVENGE_ADJOININGGENERATIONS_HPP
#define SHARE_VM_GC_IMPLEMENTATION_PARALLELSCAVENGE_ADJOININGGENERATIONS_HPP

#include "gc_implementation/parallelScavenge/adjoiningVirtualSpaces.hpp"
#include "gc_implementation/parallelScavenge/asPSOldGen.hpp"
#include "gc_implementation/parallelScavenge/asPSYoungGen.hpp"
#include "gc_implementation/parallelScavenge/generationSizer.hpp"


// Contains two generations that both use an AdjoiningVirtualSpaces.
// The two generations are adjacent in the reserved space for the
// heap.  Each generation has a virtual space and shrinking and
// expanding of the generations can still be down with that
// virtual space as was previously done.  If expanding of reserved
// size of a generation is required, the adjacent generation
// must be shrunk.  Adjusting the boundary between the generations
// is called for in this class.

class AdjoiningGenerations : public CHeapObj<mtGC> {
  friend class VMStructs;
 private:
  // The young generation and old generation, respectively
  PSYoungGen* _young_gen;
  PSOldGen* _old_gen;

  // The spaces used by the two generations.
  AdjoiningVirtualSpaces _virtual_spaces;

  // Move boundary up to expand old gen.  Checks are made to
  // determine if the move can be done with specified limits.
  void request_old_gen_expansion(size_t desired_change_in_bytes);
  // Move boundary down to expand young gen.
  bool request_young_gen_expansion(size_t desired_change_in_bytes);

 public:
  AdjoiningGenerations(ReservedSpace rs, GenerationSizer* policy, size_t alignment);

  // Accessors
  PSYoungGen* young_gen() { return _young_gen; }
  PSOldGen* old_gen() { return _old_gen; }

  AdjoiningVirtualSpaces* virtual_spaces() { return &_virtual_spaces; }

  // Additional space is needed in the old generation.  Check
  // the available space and attempt to move the boundary if more space
  // is needed.  The growth is not guaranteed to occur.
  void adjust_boundary_for_old_gen_needs(size_t desired_change_in_bytes);
  // Similary for a growth of the young generation.
  void adjust_boundary_for_young_gen_needs(size_t eden_size, size_t survivor_size);

  // Return the total byte size of the reserved space
  // for the adjoining generations.
  size_t reserved_byte_size();
};

#endif // SHARE_VM_GC_IMPLEMENTATION_PARALLELSCAVENGE_ADJOININGGENERATIONS_HPP
