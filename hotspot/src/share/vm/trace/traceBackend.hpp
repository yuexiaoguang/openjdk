#ifndef SHARE_VM_TRACE_TRACEBACKEND_HPP
#define SHARE_VM_TRACE_TRACEBACKEND_HPP

#include "utilities/macros.hpp"

#if INCLUDE_TRACE

#include "runtime/globals.hpp"
#include "runtime/os.hpp"
#include "trace/traceTime.hpp"
#include "tracefiles/traceEventIds.hpp"

class TraceBackend {
public:
  static bool enabled(void) {
    return EnableTracing;
  }

  static bool is_event_enabled(TraceEventId id) {
    return enabled();
  }

  static TracingTime time() {
    return os::elapsed_counter();
  }

  static void on_unloading_classes(void) {
  }
};

class TraceThreadData {
public:
    TraceThreadData() {}
};

typedef TraceBackend Tracing;

#else /* INCLUDE_TRACE */

#include "trace/noTraceBackend.hpp"

#endif /* INCLUDE_TRACE */
#endif /* SHARE_VM_TRACE_TRACEBACKEND_HPP */
