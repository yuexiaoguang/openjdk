#ifndef SHARE_VM_PRIMS_JVMTIUTIL_HPP
#define SHARE_VM_PRIMS_JVMTIUTIL_HPP

#include "jvmtifiles/jvmti.h"
#include "memory/resourceArea.hpp"
#include "prims/jvmtiEventController.hpp"

///////////////////////////////////////////////////////////////
//
// class JvmtiUtil
//
// class for miscellaneous jvmti utility static methods
//

class JvmtiUtil : AllStatic {

  static ResourceArea* _single_threaded_resource_area;

  static const char* _error_names[];
  static const bool  _event_threaded[];

public:

  static ResourceArea* single_threaded_resource_area();

  static const char* error_name(int num)    { return _error_names[num]; }    // To Do: add range checking

  static const bool has_event_capability(jvmtiEvent event_type, const jvmtiCapabilities* capabilities_ptr);

  static const bool  event_threaded(int num) {
    if (num >= JVMTI_MIN_EVENT_TYPE_VAL && num <= JVMTI_MAX_EVENT_TYPE_VAL) {
      return _event_threaded[num];
    }
    if (num >= EXT_MIN_EVENT_TYPE_VAL && num <= EXT_MAX_EVENT_TYPE_VAL) {
      return false;
    }
    ShouldNotReachHere();
    return false;
  }
};


///////////////////////////////////////////////////////////////
//
// class SafeResourceMark
//
// ResourceMarks that work before threads exist
//

class SafeResourceMark : public ResourceMark {

  ResourceArea* safe_resource_area() {
    Thread* thread;

    if (Threads::number_of_threads() == 0) {
      return JvmtiUtil::single_threaded_resource_area();
    }
    thread = ThreadLocalStorage::thread();
    if (thread == NULL) {
      return JvmtiUtil::single_threaded_resource_area();
    }
    return thread->resource_area();
  }

 public:

  SafeResourceMark() : ResourceMark(safe_resource_area()) {}

};

#endif // SHARE_VM_PRIMS_JVMTIUTIL_HPP
