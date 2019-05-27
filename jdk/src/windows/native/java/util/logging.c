/*
 * Win32 specific code to support logging.
 */
#include "jni_util.h"


JNIEXPORT jboolean JNICALL
Java_java_util_logging_FileHandler_isSetUID(JNIEnv *env, jclass thisclass) {

    /* There is no set UID on Windows. */
    return JNI_FALSE;
}
