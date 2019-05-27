
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

/*
 * JSDT java dtrace probes have never been implemented in macosx.  It is unknown if the solaris implementation
 * is close or if significant implementation work is necessary.  The future of the solaris implementation also
 * appears to be unclear since compiling code with JSDT probes produces the following warning:
 * "warning: ProviderFactory is internal proprietary API and may be removed in a future release"
 */

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
