#include "precompiled.hpp"
#include "runtime/os.hpp"
#include "utilities/ticks.inline.hpp"

#ifdef ASSERT
 const jlong Ticks::invalid_time_stamp = -2; // 0xFFFF FFFF`FFFF FFFE
#endif

void Ticks::stamp() {
  _stamp_ticks = os::elapsed_counter();
}

const Ticks Ticks::now() {
  Ticks t;
  t.stamp();
  return t;
}

Tickspan::Tickspan(const Ticks& end, const Ticks& start) {
  assert(end.value() != Ticks::invalid_time_stamp, "end is unstamped!");
  assert(start.value() != Ticks::invalid_time_stamp, "start is unstamped!");

  assert(end >= start, "negative time!");

  _span_ticks = end.value() - start.value();
}

template <typename ReturnType>
static ReturnType time_conversion(const Tickspan& span, TicksToTimeHelper::Unit unit) {
  assert(TicksToTimeHelper::SECONDS == unit ||
         TicksToTimeHelper::MILLISECONDS == unit, "invalid unit!");

  ReturnType frequency_per_unit = (ReturnType)os::elapsed_frequency() / (ReturnType)unit;

  return (ReturnType) ((ReturnType)span.value() / frequency_per_unit);
}

double TicksToTimeHelper::seconds(const Tickspan& span) {
  return time_conversion<double>(span, SECONDS);
}

jlong TicksToTimeHelper::milliseconds(const Tickspan& span) {
  return time_conversion<jlong>(span, MILLISECONDS);
}
