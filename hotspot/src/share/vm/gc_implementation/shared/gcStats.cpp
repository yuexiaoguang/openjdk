#include "precompiled.hpp"
#include "gc_implementation/shared/gcStats.hpp"
#include "gc_implementation/shared/gcUtil.hpp"
#include "memory/allocation.inline.hpp"

GCStats::GCStats() {
    _avg_promoted       = new AdaptivePaddedNoZeroDevAverage(
                                                  AdaptiveSizePolicyWeight,
                                                  PromotedPadding);
}

CMSGCStats::CMSGCStats() {
    _avg_promoted       = new AdaptivePaddedNoZeroDevAverage(
                                                  CMSExpAvgFactor,
                                                  PromotedPadding);
}
