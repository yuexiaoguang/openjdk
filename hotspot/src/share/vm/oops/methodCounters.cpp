#include "precompiled.hpp"
#include "oops/methodCounters.hpp"
#include "runtime/thread.inline.hpp"

MethodCounters* MethodCounters::allocate(ClassLoaderData* loader_data, TRAPS) {
  return new(loader_data, size(), false, MetaspaceObj::MethodCountersType, THREAD) MethodCounters();
}

void MethodCounters::clear_counters() {
  invocation_counter()->reset();
  backedge_counter()->reset();
  set_interpreter_throwout_count(0);
  set_interpreter_invocation_count(0);
}
