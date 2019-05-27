#include <jni.h>
#include "management.h"
#include "sun_management_MemoryManagerImpl.h"

JNIEXPORT jobject JNICALL Java_sun_management_MemoryManagerImpl_getMemoryPools0
  (JNIEnv *env, jobject mgr) {
    jobject pools = jmm_interface->GetMemoryPools(env, mgr);
    if (pools == NULL) {
        JNU_ThrowInternalError(env, "Memory Manager not found");
    }
    return pools;
}
