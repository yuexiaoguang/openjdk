
#ifndef CPU_ZERO_VM_GLOBALS_ZERO_HPP
#define CPU_ZERO_VM_GLOBALS_ZERO_HPP

#include "utilities/globalDefinitions.hpp"
#include "utilities/macros.hpp"

// Set the default values for platform dependent flags used by the
// runtime system.  See globals.hpp for details of what they do.

define_pd_global(bool,  ConvertSleepToYield,  true);
define_pd_global(bool,  ShareVtableStubs,     true);
define_pd_global(bool,  CountInterpCalls,     true);
define_pd_global(bool,  NeedsDeoptSuspend,    false);

define_pd_global(bool,  ImplicitNullChecks,   true);
define_pd_global(bool,  UncommonNullCast,     true);

define_pd_global(intx,  CodeEntryAlignment,   32);
define_pd_global(intx,  OptoLoopAlignment,    16);
define_pd_global(intx,  InlineFrequencyCount, 100);
define_pd_global(intx,  PreInflateSpin,       10);

define_pd_global(intx,  StackYellowPages,     2);
define_pd_global(intx,  StackRedPages,        1);
define_pd_global(intx,  StackShadowPages,     5 LP64_ONLY(+1) DEBUG_ONLY(+3));

define_pd_global(bool,  RewriteBytecodes,     true);
define_pd_global(bool,  RewriteFrequentPairs, true);

define_pd_global(bool,  UseMembar,            true);

// GC Ergo Flags
define_pd_global(uintx, CMSYoungGenPerWorker, 16*M);  // default max size of CMS young gen, per GC worker thread

define_pd_global(uintx, TypeProfileLevel, 0);

#define ARCH_FLAGS(develop, product, diagnostic, experimental, notproduct)

#endif // CPU_ZERO_VM_GLOBALS_ZERO_HPP
