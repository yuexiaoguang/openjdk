#ifndef SHARE_VM_GC_IMPLEMENTATION_PARALLELSCAVENGE_PSCOMPACTIONMANAGER_INLINE_HPP
#define SHARE_VM_GC_IMPLEMENTATION_PARALLELSCAVENGE_PSCOMPACTIONMANAGER_INLINE_HPP

#include "gc_implementation/parallelScavenge/psCompactionManager.hpp"
#include "gc_implementation/parallelScavenge/psParallelCompact.hpp"

void ParCompactionManager::push_objarray(oop obj, size_t index)
{
  ObjArrayTask task(obj, index);
  assert(task.is_valid(), "bad ObjArrayTask");
  _objarray_stack.push(task);
}

void ParCompactionManager::push_region(size_t index)
{
#ifdef ASSERT
  const ParallelCompactData& sd = PSParallelCompact::summary_data();
  ParallelCompactData::RegionData* const region_ptr = sd.region(index);
  assert(region_ptr->claimed(), "must be claimed");
  assert(region_ptr->_pushed++ == 0, "should only be pushed once");
#endif
  region_stack()->push(index);
}

#endif // SHARE_VM_GC_IMPLEMENTATION_PARALLELSCAVENGE_PSCOMPACTIONMANAGER_INLINE_HPP
