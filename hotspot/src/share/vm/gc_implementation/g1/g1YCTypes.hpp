#ifndef SHARE_VM_GC_IMPLEMENTATION_G1_G1YCTYPES_HPP
#define SHARE_VM_GC_IMPLEMENTATION_G1_G1YCTYPES_HPP

#include "utilities/debug.hpp"

enum G1YCType {
  Normal,
  InitialMark,
  DuringMark,
  Mixed,
  G1YCTypeEndSentinel
};

class G1YCTypeHelper {
 public:
  static const char* to_string(G1YCType type) {
    switch(type) {
      case Normal: return "Normal";
      case InitialMark: return "Initial Mark";
      case DuringMark: return "During Mark";
      case Mixed: return "Mixed";
      default: ShouldNotReachHere(); return NULL;
    }
  }
};

#endif // SHARE_VM_GC_IMPLEMENTATION_G1_G1YCTYPES_HPP
