#include "precompiled.hpp"
#include "gc_implementation/shared/collectorCounters.hpp"
#include "memory/resourceArea.hpp"

CollectorCounters::CollectorCounters(const char* name, int ordinal) {

  if (UsePerfData) {
    EXCEPTION_MARK;
    ResourceMark rm;

    const char* cns = PerfDataManager::name_space("collector", ordinal);

    _name_space = NEW_C_HEAP_ARRAY(char, strlen(cns)+1, mtGC);
    strcpy(_name_space, cns);

    char* cname = PerfDataManager::counter_name(_name_space, "name");
    PerfDataManager::create_string_constant(SUN_GC, cname, name, CHECK);

    cname = PerfDataManager::counter_name(_name_space, "invocations");
    _invocations = PerfDataManager::create_counter(SUN_GC, cname,
                                                   PerfData::U_Events, CHECK);

    cname = PerfDataManager::counter_name(_name_space, "time");
    _time = PerfDataManager::create_counter(SUN_GC, cname, PerfData::U_Ticks,
                                            CHECK);

    cname = PerfDataManager::counter_name(_name_space, "lastEntryTime");
    _last_entry_time = PerfDataManager::create_variable(SUN_GC, cname,
                                                        PerfData::U_Ticks,
                                                        CHECK);

    cname = PerfDataManager::counter_name(_name_space, "lastExitTime");
    _last_exit_time = PerfDataManager::create_variable(SUN_GC, cname,
                                                       PerfData::U_Ticks,
                                                       CHECK);
  }
}
