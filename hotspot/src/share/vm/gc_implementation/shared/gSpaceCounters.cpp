#include "precompiled.hpp"
#include "utilities/macros.hpp"
#if INCLUDE_ALL_GCS
#include "gc_implementation/shared/gSpaceCounters.hpp"
#include "memory/generation.hpp"
#include "memory/resourceArea.hpp"
#endif // INCLUDE_ALL_GCS

GSpaceCounters::GSpaceCounters(const char* name, int ordinal, size_t max_size,
                               Generation* g, GenerationCounters* gc,
                               bool sampled) :
   _gen(g) {

  if (UsePerfData) {
    EXCEPTION_MARK;
    ResourceMark rm;

    const char* cns = PerfDataManager::name_space(gc->name_space(), "space",
                                                  ordinal);

    _name_space = NEW_C_HEAP_ARRAY(char, strlen(cns)+1, mtGC);
    strcpy(_name_space, cns);

    const char* cname = PerfDataManager::counter_name(_name_space, "name");
    PerfDataManager::create_string_constant(SUN_GC, cname, name, CHECK);

    cname = PerfDataManager::counter_name(_name_space, "maxCapacity");
    PerfDataManager::create_constant(SUN_GC, cname, PerfData::U_Bytes,
                                     (jlong)max_size, CHECK);

    cname = PerfDataManager::counter_name(_name_space, "capacity");
    _capacity = PerfDataManager::create_variable(SUN_GC, cname,
                                                 PerfData::U_Bytes,
                                                 _gen->capacity(), CHECK);

    cname = PerfDataManager::counter_name(_name_space, "used");
    if (sampled) {
      _used = PerfDataManager::create_variable(SUN_GC, cname, PerfData::U_Bytes,
                                               new GenerationUsedHelper(_gen),
                                               CHECK);
    }
    else {
      _used = PerfDataManager::create_variable(SUN_GC, cname, PerfData::U_Bytes,
                                               (jlong)0, CHECK);
    }

    cname = PerfDataManager::counter_name(_name_space, "initCapacity");
    PerfDataManager::create_constant(SUN_GC, cname, PerfData::U_Bytes,
                                     _gen->capacity(), CHECK);
  }
}
