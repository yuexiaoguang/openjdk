#include <jni.h>
#include <jvm.h>
#include "sun_misc_GC.h"


JNIEXPORT jlong JNICALL
Java_sun_misc_GC_maxObjectInspectionAge(JNIEnv *env, jclass cls)
{
    return JVM_MaxObjectInspectionAge();
}
