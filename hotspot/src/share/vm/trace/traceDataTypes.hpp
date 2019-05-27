#ifndef SHARE_VM_TRACE_TRACEDATATYPES_HPP
#define SHARE_VM_TRACE_TRACEDATATYPES_HPP

#include <stddef.h>

#include "utilities/globalDefinitions.hpp"

enum {
  CONTENT_TYPE_NONE             = 0,
  CONTENT_TYPE_BYTES            = 1,
  CONTENT_TYPE_EPOCHMILLIS      = 2,
  CONTENT_TYPE_MILLIS           = 3,
  CONTENT_TYPE_NANOS            = 4,
  CONTENT_TYPE_TICKS            = 5,
  CONTENT_TYPE_ADDRESS          = 6,

  CONTENT_TYPE_OSTHREAD,
  CONTENT_TYPE_JAVALANGTHREAD,
  CONTENT_TYPE_STACKTRACE,
  CONTENT_TYPE_CLASS,
  CONTENT_TYPE_PERCENTAGE,

  JVM_CONTENT_TYPES_START       = 30,
  JVM_CONTENT_TYPES_END         = 100
};

enum ReservedEvent {
  EVENT_PRODUCERS,
  EVENT_CHECKPOINT,
  EVENT_BUFFERLOST,

  NUM_RESERVED_EVENTS
};

typedef enum ReservedEvent ReservedEvent;

typedef u8 classid;
typedef u8 stacktraceid;
typedef u8 methodid;
typedef u8 fieldid;

class TraceUnicodeString;

#endif // SHARE_VM_TRACE_TRACEDATATYPES_HPP

