#include "precompiled.hpp"
#include "gc_implementation/g1/g1_globals.hpp"
#include "gc_implementation/g1/g1Log.hpp"
#include "runtime/globals.hpp"

G1Log::LogLevel G1Log::_level = G1Log::LevelNone;

// If G1LogLevel has not been set up we will use the values of PrintGC
// and PrintGCDetails for the logging level.
// - PrintGC maps to "fine".
// - PrintGCDetails maps to "finer".
void G1Log::init() {
  if (G1LogLevel != NULL && G1LogLevel[0] != '\0') {
    if (strncmp("none", G1LogLevel, 4) == 0 && G1LogLevel[4] == '\0') {
      _level = LevelNone;
    } else if (strncmp("fine", G1LogLevel, 4) == 0 && G1LogLevel[4] == '\0') {
      _level = LevelFine;
    } else if (strncmp("finer", G1LogLevel, 5) == 0 && G1LogLevel[5] == '\0') {
      _level = LevelFiner;
    } else if (strncmp("finest", G1LogLevel, 6) == 0 && G1LogLevel[6] == '\0') {
      _level = LevelFinest;
    } else {
      warning("Unknown logging level '%s', should be one of 'fine', 'finer' or 'finest'.", G1LogLevel);
    }
  } else {
    if (PrintGCDetails) {
      _level = LevelFiner;
    } else if (PrintGC) {
      _level = LevelFine;
    }
  }
}
