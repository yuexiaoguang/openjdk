#ifndef SHARE_VM_PRIMS_JVMTICODEBLOBEVENTS_HPP
#define SHARE_VM_PRIMS_JVMTICODEBLOBEVENTS_HPP

#include "jvmtifiles/jvmti.h"

// forward declaration
class JvmtiEnv;


// JVMTI code blob event support
// -- used by GenerateEvents to generate CompiledMethodLoad and
//    DynamicCodeGenerated events
// -- also provide utility function build_jvmti_addr_location_map to create
//    a jvmtiAddrLocationMap list for a nmethod.

class JvmtiCodeBlobEvents : public AllStatic {
 public:

  // generate a DYNAMIC_CODE_GENERATED_EVENT event for each non-nmethod
  // code blob in the code cache.
  static jvmtiError generate_dynamic_code_events(JvmtiEnv* env);

  // generate a COMPILED_METHOD_LOAD event for each nmethod
  // code blob in the code cache.
  static jvmtiError generate_compiled_method_load_events(JvmtiEnv* env);

  // create a C-heap allocated address location map for an nmethod
  static void build_jvmti_addr_location_map(nmethod *nm, jvmtiAddrLocationMap** map,
                                            jint *map_length);
};

#endif // SHARE_VM_PRIMS_JVMTICODEBLOBEVENTS_HPP
