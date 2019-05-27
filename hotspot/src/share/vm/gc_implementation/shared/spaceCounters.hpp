#ifndef SHARE_VM_GC_IMPLEMENTATION_SHARED_SPACECOUNTERS_HPP
#define SHARE_VM_GC_IMPLEMENTATION_SHARED_SPACECOUNTERS_HPP

#include "utilities/macros.hpp"
#if INCLUDE_ALL_GCS
#include "gc_implementation/shared/generationCounters.hpp"
#include "gc_implementation/shared/immutableSpace.hpp"
#include "gc_implementation/shared/mutableSpace.hpp"
#include "runtime/perfData.hpp"
#endif // INCLUDE_ALL_GCS

// A SpaceCounter is a holder class for performance counters
// that track a space;

class SpaceCounters: public CHeapObj<mtGC> {
  friend class VMStructs;

 private:
  PerfVariable*      _capacity;
  PerfVariable*      _used;

  // Constant PerfData types don't need to retain a reference.
  // However, it's a good idea to document them here.
  // PerfConstant*     _size;

  MutableSpace*     _object_space;
  char*             _name_space;

 public:

  SpaceCounters(const char* name, int ordinal, size_t max_size,
                MutableSpace* m, GenerationCounters* gc);

  ~SpaceCounters() {
    if (_name_space != NULL) FREE_C_HEAP_ARRAY(char, _name_space, mtGC);
  }

  inline void update_capacity() {
    _capacity->set_value(_object_space->capacity_in_bytes());
  }

  inline void update_used() {
    _used->set_value(_object_space->used_in_bytes());
  }

  inline void update_all() {
    update_used();
    update_capacity();
  }

  const char* name_space() const        { return _name_space; }
};

class MutableSpaceUsedHelper: public PerfLongSampleHelper {
  private:
    MutableSpace* _m;

  public:
    MutableSpaceUsedHelper(MutableSpace* m) : _m(m) { }

    inline jlong take_sample() {
      return _m->used_in_bytes();
    }
};

#endif // SHARE_VM_GC_IMPLEMENTATION_SHARED_SPACECOUNTERS_HPP
