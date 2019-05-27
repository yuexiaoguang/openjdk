#ifndef SHARE_VM_UTILITIES_TOP_HPP
#define SHARE_VM_UTILITIES_TOP_HPP

#include "oops/oopsHierarchy.hpp"
#include "runtime/globals.hpp"
#include "utilities/debug.hpp"
#include "utilities/exceptions.hpp"
#include "utilities/globalDefinitions.hpp"
#include "utilities/macros.hpp"
#include "utilities/ostream.hpp"
#include "utilities/sizes.hpp"
#if INCLUDE_ALL_GCS
#include "gc_implementation/g1/g1_globals.hpp"
#endif // INCLUDE_ALL_GCS
#ifdef COMPILER1
#include "c1/c1_globals.hpp"
#endif
#ifdef COMPILER2
#include "opto/c2_globals.hpp"
#endif

// THIS FILE IS INTESIONALLY LEFT EMPTY
// IT IS USED TO MINIMIZE THE NUMBER OF DEPENDENCIES IN includeDB

#endif // SHARE_VM_UTILITIES_TOP_HPP
