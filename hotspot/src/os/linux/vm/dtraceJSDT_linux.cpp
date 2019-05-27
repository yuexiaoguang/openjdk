
#include "precompiled.hpp"
#include "classfile/javaClasses.hpp"
#include "code/codeBlob.hpp"
#include "memory/allocation.hpp"
#include "prims/jvm.h"
#include "runtime/dtraceJSDT.hpp"
#include "runtime/jniHandles.hpp"
#include "runtime/os.hpp"
#include "runtime/signature.hpp"
#include "utilities/globalDefinitions.hpp"

int DTraceJSDT::pd_activate(
    void* baseAddress, jstring module,
    jint providers_count, JVM_DTraceProvider* providers) {
  return -1;
}

void DTraceJSDT::pd_dispose(int handle) {
}

jboolean DTraceJSDT::pd_is_supported() {
  return false;
}
