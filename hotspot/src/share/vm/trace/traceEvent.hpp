#ifndef SHARE_VM_TRACE_TRACEEVENT_HPP
#define SHARE_VM_TRACE_TRACEEVENT_HPP

#include "utilities/macros.hpp"

enum EventStartTime {
  UNTIMED,
  TIMED
};

#if INCLUDE_TRACE

#include "trace/traceBackend.hpp"
#include "trace/tracing.hpp"
#include "tracefiles/traceEventIds.hpp"
#include "tracefiles/traceTypes.hpp"
#include "utilities/ticks.hpp"

template<typename T>
class TraceEvent : public StackObj {
 private:
  bool _started;
#ifdef ASSERT
  bool _committed;
  bool _cancelled;
 protected:
  bool _ignore_check;
#endif

 protected:
  jlong _startTime;
  jlong _endTime;

  void set_starttime(const TracingTime& time) {
    _startTime = time;
  }

  void set_endtime(const TracingTime& time) {
    _endTime = time;
  }

 public:
  TraceEvent(EventStartTime timing=TIMED) :
    _startTime(0),
    _endTime(0),
    _started(false)
#ifdef ASSERT
    ,
    _committed(false),
    _cancelled(false),
    _ignore_check(false)
#endif
  {
    if (T::is_enabled()) {
      _started = true;
      if (timing == TIMED && !T::isInstant) {
        static_cast<T *>(this)->set_starttime(Tracing::time());
      }
    }
  }

  static bool is_enabled() {
    return Tracing::is_event_enabled(T::eventId);
  }

  bool should_commit() {
    return _started;
  }

  void ignoreCheck() {
    DEBUG_ONLY(_ignore_check = true);
  }

  void commit() {
    if (!should_commit()) {
        cancel();
        return;
    }
    if (_endTime == 0) {
      static_cast<T*>(this)->set_endtime(Tracing::time());
    }
    if (static_cast<T*>(this)->should_write()) {
      static_cast<T*>(this)->writeEvent();
    }
    set_commited();
  }

  void set_starttime(const Ticks& time) {
    _startTime = time.value();
  }

  void set_endtime(const Ticks& time) {
    _endTime = time.value();
  }

  TraceEventId id() const {
    return T::eventId;
  }

  bool is_instant() const {
    return T::isInstant;
  }

  bool is_requestable() const {
    return T::isRequestable;
  }

  bool has_thread() const {
    return T::hasThread;
  }

  bool has_stacktrace() const {
    return T::hasStackTrace;
  }

  void cancel() {
    assert(!_committed && !_cancelled, "event was already committed/cancelled");
    DEBUG_ONLY(_cancelled = true);
  }

  void set_commited() {
    assert(!_committed, "event has already been committed");
    DEBUG_ONLY(_committed = true);
  }

  ~TraceEvent() {
    if (_started) {
      assert(_ignore_check || _committed || _cancelled, "event was not committed/cancelled");
    }
  }
};

#endif /* INCLUDE_TRACE */

#endif /* SHARE_VM_TRACE_TRACEEVENT_HPP */
