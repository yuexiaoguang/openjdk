#include "precompiled.hpp"
#include "gc_implementation/shared/objectCountEventSender.hpp"
#include "memory/heapInspection.hpp"
#include "trace/tracing.hpp"
#include "utilities/globalDefinitions.hpp"
#include "utilities/ticks.hpp"

#if INCLUDE_SERVICES

void ObjectCountEventSender::send(const KlassInfoEntry* entry, GCId gc_id, const Ticks& timestamp) {
#if INCLUDE_TRACE
  assert(Tracing::is_event_enabled(EventObjectCountAfterGC::eventId),
         "Only call this method if the event is enabled");

  EventObjectCountAfterGC event(UNTIMED);
  event.set_gcId(gc_id);
  event.set_class(entry->klass());
  event.set_count(entry->count());
  event.set_totalSize(entry->words() * BytesPerWord);
  event.set_endtime(timestamp);
  event.commit();
#endif // INCLUDE_TRACE
}

bool ObjectCountEventSender::should_send_event() {
#if INCLUDE_TRACE
  return Tracing::is_event_enabled(EventObjectCountAfterGC::eventId);
#else
  return false;
#endif // INCLUDE_TRACE
}

#endif // INCLUDE_SERVICES
