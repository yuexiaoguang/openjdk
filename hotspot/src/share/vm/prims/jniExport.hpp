#ifndef SHARE_VM_PRIMS_JNI_EXPORT_HPP
#define SHARE_VM_PRIMS_JNI_EXPORT_HPP

#include "prims/jni.h"
#include "prims/jvmtiExport.hpp"

class JniExportedInterface {
 public:
  static bool GetExportedInterface(JavaVM* vm, void** penv, jint version, jint* iface) {
    if (JvmtiExport::is_jvmti_version(version)) {
      *iface = JvmtiExport::get_jvmti_interface(vm, penv, version);
      return true;
    }
    return false;
  }
};

#endif // SHARE_VM_PRIMS_JNI_EXPORT_HPP
