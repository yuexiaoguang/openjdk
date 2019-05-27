#ifndef SHARE_VM_UTILITIES_TICKS_INLINE_HPP
#define SHARE_VM_UTILITIES_TICKS_INLINE_HPP

#include "utilities/ticks.hpp"

inline Tickspan operator+(Tickspan lhs, const Tickspan& rhs) {
  lhs += rhs;
  return lhs;
}

inline bool operator==(const Tickspan& lhs, const Tickspan& rhs) {
  return lhs.value() == rhs.value();
}

inline bool operator!=(const Tickspan& lhs, const Tickspan& rhs) {
  return !operator==(lhs,rhs);
}

inline bool operator<(const Tickspan& lhs, const Tickspan& rhs) {
  return lhs.value() < rhs.value();
}

inline bool operator>(const Tickspan& lhs, const Tickspan& rhs) {
  return operator<(rhs,lhs);
}

inline bool operator<=(const Tickspan& lhs, const Tickspan& rhs) {
  return !operator>(lhs,rhs);
}

inline bool operator>=(const Tickspan& lhs, const Tickspan& rhs) {
  return !operator<(lhs,rhs);
}

inline Ticks operator+(Ticks lhs, const Tickspan& span) {
  lhs += span;
  return lhs;
}

inline Ticks operator-(Ticks lhs, const Tickspan& span) {
  lhs -= span;
  return lhs;
}

inline Tickspan operator-(const Ticks& end, const Ticks& start) {
  return Tickspan(end, start);
}

inline bool operator==(const Ticks& lhs, const Ticks& rhs) {
  return lhs.value() == rhs.value();
}

inline bool operator!=(const Ticks& lhs, const Ticks& rhs) {
  return !operator==(lhs,rhs);
}

inline bool operator<(const Ticks& lhs, const Ticks& rhs) {
  return lhs.value() < rhs.value();
}

inline bool operator>(const Ticks& lhs, const Ticks& rhs) {
  return operator<(rhs,lhs);
}

inline bool operator<=(const Ticks& lhs, const Ticks& rhs) {
  return !operator>(lhs,rhs);
}

inline bool operator>=(const Ticks& lhs, const Ticks& rhs) {
  return !operator<(lhs,rhs);
}

#endif // SHARE_VM_UTILITIES_TICKS_INLINE_HPP
