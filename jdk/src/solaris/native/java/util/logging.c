/*
 * Solaris/Linux specific code to support logging.
 */

#include <unistd.h>
#include "jni_util.h"


JNIEXPORT jboolean JNICALL
Java_java_util_logging_FileHandler_isSetUID(JNIEnv *env, jclass thisclass) {

    /* Return true if we are in a set UID or set GID process. */
    if (getuid() != geteuid() || getgid() != getegid()) {
        return JNI_TRUE;
    }
    return JNI_FALSE;
}
