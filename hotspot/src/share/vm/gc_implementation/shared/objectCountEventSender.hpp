#ifndef SHARE_VM_OBJECT_COUNT_EVENT_SENDER_HPP
#define SHARE_VM_OBJECT_COUNT_EVENT_SENDER_HPP

#include "gc_implementation/shared/gcTrace.hpp"
#include "memory/allocation.hpp"
#include "utilities/macros.hpp"

#if INCLUDE_SERVICES

class KlassInfoEntry;
class Ticks;

class ObjectCountEventSender : public AllStatic {
 public:
  static void send(const KlassInfoEntry* entry, GCId gc_id, const Ticks& timestamp);
  static bool should_send_event();
};

#endif // INCLUDE_SERVICES

#endif // SHARE_VM_OBJECT_COUNT_EVENT_SENDER
