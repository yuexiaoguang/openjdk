#include <jni.h>
#include "management.h"
#include "sun_management_ClassLoadingImpl.h"

JNIEXPORT void JNICALL Java_sun_management_ClassLoadingImpl_setVerboseClass
  (JNIEnv *env, jclass cls, jboolean flag) {
    jmm_interface->SetBoolAttribute(env, JMM_VERBOSE_CLASS, flag);
}
