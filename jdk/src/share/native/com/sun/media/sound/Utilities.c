
#include "Utilities.h"


int UTIL_IsBigEndianPlatform() {
#ifdef _LITTLE_ENDIAN
    return 0;
#else
    return 1;
#endif
}

void ThrowJavaMessageException(JNIEnv *e, const char *exClass, const char *msg) {
    jclass newExcCls;

    ERROR1("throw exception: %s\n", msg);
    newExcCls = (*e)->FindClass(e, exClass);
    if (newExcCls == 0) {
        /* Unable to find the new exception class, give up. */
        ERROR0("ThrowJavaMessageException unable to find class!\n");
        return;
    }
    (*e)->ThrowNew(e, newExcCls, msg);
}
