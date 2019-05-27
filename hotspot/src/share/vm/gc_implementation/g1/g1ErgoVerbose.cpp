#include "precompiled.hpp"
#include "gc_implementation/g1/g1ErgoVerbose.hpp"
#include "utilities/ostream.hpp"

ErgoLevel G1ErgoVerbose::_level;
bool G1ErgoVerbose::_enabled[ErgoHeuristicNum];

void G1ErgoVerbose::initialize() {
  set_level(ErgoLow);
  set_enabled(false);
}

void G1ErgoVerbose::set_level(ErgoLevel level) {
  _level = level;
}

void G1ErgoVerbose::set_enabled(ErgoHeuristic n, bool enabled) {
  assert(0 <= n && n < ErgoHeuristicNum, "pre-condition");
  _enabled[n] = enabled;
}

void G1ErgoVerbose::set_enabled(bool enabled) {
  for (int n = 0; n < ErgoHeuristicNum; n += 1) {
    set_enabled((ErgoHeuristic) n, enabled);
  }
}

const char* G1ErgoVerbose::to_string(int tag) {
  ErgoHeuristic n = extract_heuristic(tag);
  switch (n) {
  case ErgoHeapSizing:        return "Heap Sizing";
  case ErgoCSetConstruction:  return "CSet Construction";
  case ErgoConcCycles:        return "Concurrent Cycles";
  case ErgoMixedGCs:          return "Mixed GCs";
  default:
    ShouldNotReachHere();
    // Keep the Windows compiler happy
    return NULL;
  }
}
