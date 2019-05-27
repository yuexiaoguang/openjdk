#include "precompiled.hpp"
#include "utilities/macros.hpp"
#if INCLUDE_ALL_GCS
#include "gc_implementation/shared/allocationStats.hpp"
#include "utilities/ostream.hpp"
#endif // INCLUDE_ALL_GCS

// Technically this should be derived from machine speed, and
// ideally it would be dynamically adjusted.
float AllocationStats::_threshold = ((float)CMS_SweepTimerThresholdMillis)/1000;
