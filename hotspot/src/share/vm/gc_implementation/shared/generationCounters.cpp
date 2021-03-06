#include "precompiled.hpp"
#include "gc_implementation/shared/generationCounters.hpp"
#include "memory/resourceArea.hpp"

void GenerationCounters::initialize(const char* name, int ordinal, int spaces,
                                    size_t min_capacity, size_t max_capacity,
                                    size_t curr_capacity) {
  if (UsePerfData) {
    EXCEPTION_MARK;
    ResourceMark rm;

    const char* cns = PerfDataManager::name_space("generation", ordinal);

    _name_space = NEW_C_HEAP_ARRAY(char, strlen(cns)+1, mtGC);
    strcpy(_name_space, cns);

    const char* cname = PerfDataManager::counter_name(_name_space, "name");
    PerfDataManager::create_string_constant(SUN_GC, cname, name, CHECK);

    cname = PerfDataManager::counter_name(_name_space, "spaces");
    PerfDataManager::create_constant(SUN_GC, cname, PerfData::U_None,
                                     spaces, CHECK);

    cname = PerfDataManager::counter_name(_name_space, "minCapacity");
    PerfDataManager::create_constant(SUN_GC, cname, PerfData::U_Bytes,
                                     min_capacity, CHECK);

    cname = PerfDataManager::counter_name(_name_space, "maxCapacity");
    PerfDataManager::create_constant(SUN_GC, cname, PerfData::U_Bytes,
                                     max_capacity, CHECK);

    cname = PerfDataManager::counter_name(_name_space, "capacity");
    _current_size =
      PerfDataManager::create_variable(SUN_GC, cname, PerfData::U_Bytes,
                                       curr_capacity, CHECK);
  }
}

GenerationCounters::GenerationCounters(const char* name,
                                       int ordinal, int spaces,
                                       VirtualSpace* v)
  : _virtual_space(v) {
  assert(v != NULL, "don't call this constructor if v == NULL");
  initialize(name, ordinal, spaces,
             v->committed_size(), v->reserved_size(), v->committed_size());
}

GenerationCounters::GenerationCounters(const char* name,
                                       int ordinal, int spaces,
                                       size_t min_capacity, size_t max_capacity,
                                       size_t curr_capacity)
  : _virtual_space(NULL) {
  initialize(name, ordinal, spaces, min_capacity, max_capacity, curr_capacity);
}

void GenerationCounters::update_all() {
  assert(_virtual_space != NULL, "otherwise, override this method");
  _current_size->set_value(_virtual_space->committed_size());
}
