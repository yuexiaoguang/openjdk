#ifndef SHARE_VM_TRACE_NOTRACEBACKEND_HPP
#define SHARE_VM_TRACE_NOTRACEBACKEND_HPP

#include "prims/jni.h"
#include "trace/traceTime.hpp"

class NoTraceBackend {
public:
  static TracingTime time() {
    return 0;
  }
};

class TraceThreadData {
public:
    TraceThreadData() {}
};

typedef NoTraceBackend Tracing;

#endif
