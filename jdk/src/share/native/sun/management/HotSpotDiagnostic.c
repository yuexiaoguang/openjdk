#include <jni.h>
#include "jvm.h"
#include "management.h"
#include "sun_management_HotSpotDiagnostic.h"

JNIEXPORT void JNICALL
Java_sun_management_HotSpotDiagnostic_dumpHeap0
  (JNIEnv *env, jobject dummy, jstring outputfile, jboolean live)
{
    jmm_interface->DumpHeap0(env, outputfile, live);
}
