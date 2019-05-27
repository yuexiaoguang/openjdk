#include <jni.h>
#include "management.h"
#include "sun_management_GarbageCollectorImpl.h"

JNIEXPORT jlong JNICALL Java_sun_management_GarbageCollectorImpl_getCollectionCount
  (JNIEnv *env, jobject mgr) {
    return jmm_interface->GetLongAttribute(env, mgr, JMM_GC_COUNT);
}

JNIEXPORT jlong JNICALL Java_sun_management_GarbageCollectorImpl_getCollectionTime
  (JNIEnv *env, jobject mgr) {
    return jmm_interface->GetLongAttribute(env, mgr, JMM_GC_TIME_MS);
}


JNIEXPORT void JNICALL Java_sun_management_GarbageCollectorImpl_setNotificationEnabled
(JNIEnv *env, jobject dummy, jobject gc,jboolean enabled) {

    if (gc == NULL) {
        JNU_ThrowNullPointerException(env, "Invalid GarbageCollectorMBean");
        return;
    }
    if((jmm_version > JMM_VERSION_1_2)
       || (jmm_version == JMM_VERSION_1_2 && ((jmm_version&0xFF)>=1))) {
      jmm_interface->SetGCNotificationEnabled(env, gc, enabled);
    }
}
