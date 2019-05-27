#ifndef SHARE_VM_UTILITIES_TICKS_HPP
#define SHARE_VM_UTILITIES_TICKS_HPP

#include "memory/allocation.hpp"
#include "utilities/globalDefinitions.hpp"

class Ticks;

class Tickspan VALUE_OBJ_CLASS_SPEC {
  friend class Ticks;
  friend Tickspan operator-(const Ticks& end, const Ticks& start);

 private:
  jlong _span_ticks;

  Tickspan(const Ticks& end, const Ticks& start);

 public:
  Tickspan() : _span_ticks(0) {}

  Tickspan& operator+=(const Tickspan& rhs) {
    _span_ticks += rhs._span_ticks;
    return *this;
  }

  jlong value() const {
    return _span_ticks;
  }

};

class Ticks VALUE_OBJ_CLASS_SPEC {
 private:
  jlong _stamp_ticks;

 public:
  Ticks() : _stamp_ticks(0) {
    assert((_stamp_ticks = invalid_time_stamp) == invalid_time_stamp,
      "initial unstamped time value assignment");
  }

  Ticks& operator+=(const Tickspan& span) {
    _stamp_ticks += span.value();
    return *this;
  }

  Ticks& operator-=(const Tickspan& span) {
    _stamp_ticks -= span.value();
    return *this;
  }

  void stamp();

  jlong value() const {
    return _stamp_ticks;
  }

  static const Ticks now();

#ifdef ASSERT
  static const jlong invalid_time_stamp;
#endif

#ifndef PRODUCT
  // only for internal use by GC VM tests
  friend class TimePartitionPhasesIteratorTest;
  friend class GCTimerTest;

 private:
  // implicit type conversion
  Ticks(int ticks) : _stamp_ticks(ticks) {}

#endif // !PRODUCT

};

class TicksToTimeHelper : public AllStatic {
 public:
  enum Unit {
    SECONDS = 1,
    MILLISECONDS = 1000
  };
  static double seconds(const Tickspan& span);
  static jlong milliseconds(const Tickspan& span);
};

#endif // SHARE_VM_UTILITIES_TICKS_HPP
