#include "precompiled.hpp"
#include "gc_implementation/shared/gcTimer.hpp"
#include "gc_implementation/shared/gcTraceTime.hpp"
#include "runtime/globals.hpp"
#include "runtime/os.hpp"
#include "runtime/safepoint.hpp"
#include "runtime/thread.inline.hpp"
#include "runtime/timer.hpp"
#include "utilities/ostream.hpp"
#include "utilities/ticks.inline.hpp"


GCTraceTime::GCTraceTime(const char* title, bool doit, bool print_cr, GCTimer* timer) :
    _title(title), _doit(doit), _print_cr(print_cr), _timer(timer), _start_counter() {
  if (_doit || _timer != NULL) {
    _start_counter.stamp();
  }

  if (_timer != NULL) {
    assert(SafepointSynchronize::is_at_safepoint(), "Tracing currently only supported at safepoints");
    assert(Thread::current()->is_VM_thread(), "Tracing currently only supported from the VM thread");

    _timer->register_gc_phase_start(title, _start_counter);
  }

  if (_doit) {
    if (PrintGCTimeStamps) {
      gclog_or_tty->stamp();
      gclog_or_tty->print(": ");
    }
    gclog_or_tty->print("[%s", title);
    gclog_or_tty->flush();
  }
}

GCTraceTime::~GCTraceTime() {
  Ticks stop_counter;

  if (_doit || _timer != NULL) {
    stop_counter.stamp();
  }

  if (_timer != NULL) {
    _timer->register_gc_phase_end(stop_counter);
  }

  if (_doit) {
    const Tickspan duration = stop_counter - _start_counter;
    double duration_in_seconds = TicksToTimeHelper::seconds(duration);
    if (_print_cr) {
      gclog_or_tty->print_cr(", %3.7f secs]", duration_in_seconds);
    } else {
      gclog_or_tty->print(", %3.7f secs]", duration_in_seconds);
    }
    gclog_or_tty->flush();
  }
}
