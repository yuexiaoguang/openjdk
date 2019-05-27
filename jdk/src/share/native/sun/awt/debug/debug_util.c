
#include "debug_util.h"

#if defined(DEBUG)

dmutex_t DMutex_Create() {
    dmutex_t    mutex;
    mutex = (dmutex_t)JVM_RawMonitorCreate();
    DASSERT(mutex != NULL);

    return mutex;
}

void DMutex_Destroy(dmutex_t mutex) {
    if (mutex != NULL) {
        JVM_RawMonitorDestroy(mutex);
    }
}

void DMutex_Enter(dmutex_t mutex) {
    if (mutex != NULL) {
        JVM_RawMonitorEnter(mutex);
    }
}

void DMutex_Exit(dmutex_t mutex) {
    if (mutex != NULL) {
        JVM_RawMonitorExit(mutex);
    }
}

#endif
