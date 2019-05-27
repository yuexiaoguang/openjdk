#ifndef SHARE_VM_OOPS_OOP_INLINE2_HPP
#define SHARE_VM_OOPS_OOP_INLINE2_HPP

#include "gc_interface/collectedHeap.hpp"
#include "memory/generation.hpp"
#include "memory/universe.hpp"
#include "oops/oop.hpp"

// Implementation of all inlined member functions defined in oop.hpp
// We need a separate file to avoid circular references

inline bool oopDesc::is_scavengable() const {
  return Universe::heap()->is_scavengable(this);
}
#endif // SHARE_VM_OOPS_OOP_INLINE2_HPP
