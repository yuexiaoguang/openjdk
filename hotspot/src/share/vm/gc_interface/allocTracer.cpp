#include "precompiled.hpp"
#include "gc_interface/allocTracer.hpp"
#include "trace/tracing.hpp"
#include "runtime/handles.hpp"
#include "utilities/globalDefinitions.hpp"

void AllocTracer::send_allocation_outside_tlab_event(KlassHandle klass, size_t alloc_size) {
  EventAllocObjectOutsideTLAB event;
  if (event.should_commit()) {
    event.set_class(klass());
    event.set_allocationSize(alloc_size);
    event.commit();
  }
}

void AllocTracer::send_allocation_in_new_tlab_event(KlassHandle klass, size_t tlab_size, size_t alloc_size) {
  EventAllocObjectInNewTLAB event;
  if (event.should_commit()) {
    event.set_class(klass());
    event.set_allocationSize(alloc_size);
    event.set_tlabSize(tlab_size);
    event.commit();
  }
}
