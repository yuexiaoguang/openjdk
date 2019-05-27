#ifndef SHARE_VM_SERVICES_PSMEMORYPOOL_HPP
#define SHARE_VM_SERVICES_PSMEMORYPOOL_HPP

#include "utilities/macros.hpp"
#if INCLUDE_ALL_GCS
#include "gc_implementation/parallelScavenge/psOldGen.hpp"
#include "gc_implementation/parallelScavenge/psYoungGen.hpp"
#include "gc_implementation/shared/mutableSpace.hpp"
#include "memory/defNewGeneration.hpp"
#include "memory/heap.hpp"
#include "memory/space.hpp"
#include "services/memoryPool.hpp"
#include "services/memoryUsage.hpp"
#endif // INCLUDE_ALL_GCS

class PSGenerationPool : public CollectedMemoryPool {
private:
  PSOldGen* _gen;

public:
  PSGenerationPool(PSOldGen* pool, const char* name, PoolType type, bool support_usage_threshold);

  MemoryUsage get_memory_usage();
  size_t used_in_bytes()              { return _gen->used_in_bytes(); }
  size_t max_size() const             { return _gen->reserved().byte_size(); }
};

class EdenMutableSpacePool : public CollectedMemoryPool {
private:
  PSYoungGen*   _gen;
  MutableSpace* _space;

public:
  EdenMutableSpacePool(PSYoungGen* gen,
                       MutableSpace* space,
                       const char* name,
                       PoolType type,
                       bool support_usage_threshold);

  MutableSpace* space()                     { return _space; }
  MemoryUsage get_memory_usage();
  size_t used_in_bytes()                    { return space()->used_in_bytes(); }
  size_t max_size() const {
    // Eden's max_size = max_size of Young Gen - the current committed size of survivor spaces
    return _gen->max_size() - _gen->from_space()->capacity_in_bytes() - _gen->to_space()->capacity_in_bytes();
  }
};

class SurvivorMutableSpacePool : public CollectedMemoryPool {
private:
  PSYoungGen*   _gen;

public:
  SurvivorMutableSpacePool(PSYoungGen* gen,
                           const char* name,
                           PoolType type,
                           bool support_usage_threshold);

  MemoryUsage get_memory_usage();

  size_t used_in_bytes() {
    return _gen->from_space()->used_in_bytes();
  }
  size_t committed_in_bytes() {
    return _gen->from_space()->capacity_in_bytes();
  }
  size_t max_size() const {
    // Return current committed size of the from-space
    return _gen->from_space()->capacity_in_bytes();
  }
};

#endif // SHARE_VM_SERVICES_PSMEMORYPOOL_HPP
