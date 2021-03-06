#include "precompiled.hpp"
#include "memory/allocation.inline.hpp"
#include "runtime/mutexLocker.hpp"
#include "runtime/osThread.hpp"
#include "runtime/thread.inline.hpp"
#include "runtime/threadCritical.hpp"
#include "runtime/threadLocalStorage.hpp"
#include "runtime/timer.hpp"
#include "utilities/events.hpp"


EventLog* Events::_logs = NULL;
StringEventLog* Events::_messages = NULL;
StringEventLog* Events::_exceptions = NULL;
StringEventLog* Events::_deopt_messages = NULL;

EventLog::EventLog() {
  // This normally done during bootstrap when we're only single
  // threaded but use a ThreadCritical to ensure inclusion in case
  // some are created slightly late.
  ThreadCritical tc;
  _next = Events::_logs;
  Events::_logs = this;
}

// For each registered event logger, print out the current contents of
// the buffer.  This is normally called when the JVM is crashing.
void Events::print_all(outputStream* out) {
  EventLog* log = _logs;
  while (log != NULL) {
    log->print_log_on(out);
    log = log->next();
  }
}

void Events::print() {
  print_all(tty);
}

void Events::init() {
  if (LogEvents) {
    _messages = new StringEventLog("Events");
    _exceptions = new StringEventLog("Internal exceptions");
    _deopt_messages = new StringEventLog("Deoptimization events");
  }
}

void eventlog_init() {
  Events::init();
}

///////////////////////////////////////////////////////////////////////////
// EventMark

EventMark::EventMark(const char* format, ...) {
  if (LogEvents) {
    va_list ap;
    va_start(ap, format);
    // Save a copy of begin message and log it.
    _buffer.printv(format, ap);
    Events::log(NULL, _buffer);
    va_end(ap);
  }
}

EventMark::~EventMark() {
  if (LogEvents) {
    // Append " done" to the begin message and log it
    _buffer.append(" done");
    Events::log(NULL, _buffer);
  }
}
