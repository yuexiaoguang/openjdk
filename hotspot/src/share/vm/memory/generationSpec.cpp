#include "precompiled.hpp"
#include "memory/binaryTreeDictionary.hpp"
#include "memory/defNewGeneration.hpp"
#include "memory/filemap.hpp"
#include "memory/genRemSet.hpp"
#include "memory/generationSpec.hpp"
#include "memory/tenuredGeneration.hpp"
#include "runtime/java.hpp"
#include "utilities/macros.hpp"
#if INCLUDE_ALL_GCS
#include "gc_implementation/parNew/asParNewGeneration.hpp"
#include "gc_implementation/concurrentMarkSweep/concurrentMarkSweepGeneration.hpp"
#include "gc_implementation/parNew/parNewGeneration.hpp"
#endif // INCLUDE_ALL_GCS

Generation* GenerationSpec::init(ReservedSpace rs, int level,
                                 GenRemSet* remset) {
  switch (name()) {
    case Generation::DefNew:
      return new DefNewGeneration(rs, init_size(), level);

    case Generation::MarkSweepCompact:
      return new TenuredGeneration(rs, init_size(), level, remset);

#if INCLUDE_ALL_GCS
    case Generation::ParNew:
      return new ParNewGeneration(rs, init_size(), level);

    case Generation::ASParNew:
      return new ASParNewGeneration(rs,
                                    init_size(),
                                    init_size() /* min size */,
                                    level);

    case Generation::ConcurrentMarkSweep: {
      assert(UseConcMarkSweepGC, "UseConcMarkSweepGC should be set");
      CardTableRS* ctrs = remset->as_CardTableRS();
      if (ctrs == NULL) {
        vm_exit_during_initialization("Rem set incompatibility.");
      }
      // Otherwise
      // The constructor creates the CMSCollector if needed,
      // else registers with an existing CMSCollector

      ConcurrentMarkSweepGeneration* g = NULL;
      g = new ConcurrentMarkSweepGeneration(rs,
                 init_size(), level, ctrs, UseCMSAdaptiveFreeLists,
                 (FreeBlockDictionary<FreeChunk>::DictionaryChoice)CMSDictionaryChoice);

      g->initialize_performance_counters();

      return g;
    }

    case Generation::ASConcurrentMarkSweep: {
      assert(UseConcMarkSweepGC, "UseConcMarkSweepGC should be set");
      CardTableRS* ctrs = remset->as_CardTableRS();
      if (ctrs == NULL) {
        vm_exit_during_initialization("Rem set incompatibility.");
      }
      // Otherwise
      // The constructor creates the CMSCollector if needed,
      // else registers with an existing CMSCollector

      ASConcurrentMarkSweepGeneration* g = NULL;
      g = new ASConcurrentMarkSweepGeneration(rs,
                 init_size(), level, ctrs, UseCMSAdaptiveFreeLists,
                 (FreeBlockDictionary<FreeChunk>::DictionaryChoice)CMSDictionaryChoice);

      g->initialize_performance_counters();

      return g;
    }
#endif // INCLUDE_ALL_GCS

    default:
      guarantee(false, "unrecognized GenerationName");
      return NULL;
  }
}
