#ifndef SHARE_VM_MEMORY_UNIVERSE_INLINE_HPP
#define SHARE_VM_MEMORY_UNIVERSE_INLINE_HPP

#include "memory/universe.hpp"

// Check whether an element of a typeArrayOop with the given type must be
// aligned 0 mod 8.  The typeArrayOop itself must be aligned at least this
// strongly.

inline bool Universe::element_type_should_be_aligned(BasicType type) {
  return type == T_DOUBLE || type == T_LONG;
}

// Check whether an object field (static/non-static) of the given type must be aligned 0 mod 8.

inline bool Universe::field_type_should_be_aligned(BasicType type) {
  return type == T_DOUBLE || type == T_LONG;
}

#endif // SHARE_VM_MEMORY_UNIVERSE_INLINE_HPP
