#ifndef SHARE_VM_PRIMS_JVMTIEXTENSIONS_HPP
#define SHARE_VM_PRIMS_JVMTIEXTENSIONS_HPP

#include "jvmtifiles/jvmti.h"
#include "jvmtifiles/jvmtiEnv.hpp"
#include "memory/allocation.hpp"

// JvmtiExtensions
//
// Maintains the list of extension functions and events in this JVMTI
// implementation. The list of functions and events can be obtained by
// the profiler using the JVMTI GetExtensionFunctions and
// GetExtensionEvents functions.

class JvmtiExtensions : public AllStatic {
 private:
  static GrowableArray<jvmtiExtensionFunctionInfo*>* _ext_functions;
  static GrowableArray<jvmtiExtensionEventInfo*>* _ext_events;

 public:
  // register extensions function
  static void register_extensions();

  // returns the list of extension functions
  static jvmtiError get_functions(JvmtiEnv* env, jint* extension_count_ptr,
                                  jvmtiExtensionFunctionInfo** extensions);

  // returns the list of extension events
  static jvmtiError get_events(JvmtiEnv* env, jint* extension_count_ptr,
                               jvmtiExtensionEventInfo** extensions);

  // sets the callback function for an extension event and enables the event
  static jvmtiError set_event_callback(JvmtiEnv* env, jint extension_event_index,
                                       jvmtiExtensionEvent callback);
};

#endif // SHARE_VM_PRIMS_JVMTIEXTENSIONS_HPP
