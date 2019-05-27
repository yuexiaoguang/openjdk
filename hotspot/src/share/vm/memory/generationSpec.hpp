#ifndef SHARE_VM_MEMORY_GENERATIONSPEC_HPP
#define SHARE_VM_MEMORY_GENERATIONSPEC_HPP

#include "memory/generation.hpp"

// The specification of a generation.  This class also encapsulates
// some generation-specific behavior.  This is done here rather than as a
// virtual function of Generation because these methods are needed in
// initialization of the Generations.
class GenerationSpec : public CHeapObj<mtGC> {
  friend class VMStructs;
private:
  Generation::Name _name;
  size_t           _init_size;
  size_t           _max_size;

public:
  GenerationSpec(Generation::Name name, size_t init_size, size_t max_size) {
    _name = name;
    _init_size = init_size;
    _max_size = max_size;
  }

  Generation* init(ReservedSpace rs, int level, GenRemSet* remset);

  // Accessors
  Generation::Name name()        const { return _name; }
  size_t init_size()             const { return _init_size; }
  void set_init_size(size_t size)      { _init_size = size; }
  size_t max_size()              const { return _max_size; }
  void set_max_size(size_t size)       { _max_size = size; }

  // Alignment
  void align(size_t alignment) {
    set_init_size(align_size_up(init_size(), alignment));
    set_max_size(align_size_up(max_size(), alignment));
  }

  // Return the number of regions contained in the generation which
  // might need to be independently covered by a remembered set.
  virtual int n_covered_regions() const { return 1; }
};

typedef GenerationSpec* GenerationSpecPtr;

#endif // SHARE_VM_MEMORY_GENERATIONSPEC_HPP
