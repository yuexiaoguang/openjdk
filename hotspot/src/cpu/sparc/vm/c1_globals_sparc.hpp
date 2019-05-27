#ifndef CPU_SPARC_VM_C1_GLOBALS_SPARC_HPP
#define CPU_SPARC_VM_C1_GLOBALS_SPARC_HPP

#include "utilities/globalDefinitions.hpp"
#include "utilities/macros.hpp"

// Sets the default values for platform dependent flags used by the client compiler.
// (see c1_globals.hpp)

#ifndef TIERED
define_pd_global(bool, BackgroundCompilation,        true );
define_pd_global(bool, CICompileOSR,                 true );
define_pd_global(bool, InlineIntrinsics,             true );
define_pd_global(bool, PreferInterpreterNativeStubs, false);
define_pd_global(bool, ProfileTraps,                 false);
define_pd_global(bool, UseOnStackReplacement,        true );
define_pd_global(bool, TieredCompilation,            false);
define_pd_global(intx, CompileThreshold,             1000 ); // Design center runs on 1.3.1
define_pd_global(intx, BackEdgeThreshold,            100000);

define_pd_global(intx, OnStackReplacePercentage,     1400 );
define_pd_global(bool, UseTLAB,                      true );
define_pd_global(bool, ProfileInterpreter,           false);
define_pd_global(intx, FreqInlineSize,               325  );
define_pd_global(bool, ResizeTLAB,                   true );
define_pd_global(intx, ReservedCodeCacheSize,        32*M );
define_pd_global(intx, CodeCacheExpansionSize,       32*K );
define_pd_global(uintx, CodeCacheMinBlockLength,     1);
define_pd_global(uintx, CodeCacheMinimumUseSpace,    400*K);
define_pd_global(uintx, MetaspaceSize,               12*M );
define_pd_global(bool, NeverActAsServerClassMachine, true );
define_pd_global(intx, NewSizeThreadIncrease,        16*K );
define_pd_global(uint64_t,MaxRAM,                    1ULL*G);
define_pd_global(intx, InitialCodeCacheSize,         160*K);
#endif // !TIERED

define_pd_global(bool, UseTypeProfile,               false);
define_pd_global(bool, RoundFPResults,               false);

define_pd_global(bool, LIRFillDelaySlots,            true );
define_pd_global(bool, OptimizeSinglePrecision,      false);
define_pd_global(bool, CSEArrayLength,               true );
define_pd_global(bool, TwoOperandLIRForm,            false);

define_pd_global(intx, SafepointPollOffset,          0    );

#endif // CPU_SPARC_VM_C1_GLOBALS_SPARC_HPP
