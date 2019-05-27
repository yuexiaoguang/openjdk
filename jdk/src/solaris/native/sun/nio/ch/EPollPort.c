#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "jlong.h"
#include "nio_util.h"

#include "sun_nio_ch_EPollPort.h"

#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>

JNIEXPORT void JNICALL
Java_sun_nio_ch_EPollPort_socketpair(JNIEnv* env, jclass clazz, jintArray sv) {
    int sp[2];
    if (socketpair(PF_UNIX, SOCK_STREAM, 0, sp) == -1) {
        JNU_ThrowIOExceptionWithLastError(env, "socketpair failed");
    } else {
        jint res[2];
        res[0] = (jint)sp[0];
        res[1] = (jint)sp[1];
        (*env)->SetIntArrayRegion(env, sv, 0, 2, &res[0]);
    }
}

JNIEXPORT void JNICALL
Java_sun_nio_ch_EPollPort_interrupt(JNIEnv *env, jclass c, jint fd) {
    int res;
    int buf[1];
    buf[0] = 1;
    RESTARTABLE(write(fd, buf, 1), res);
    if (res < 0) {
        JNU_ThrowIOExceptionWithLastError(env, "write failed");
    }
}

JNIEXPORT void JNICALL
Java_sun_nio_ch_EPollPort_drain1(JNIEnv *env, jclass cl, jint fd) {
    int res;
    char buf[1];
    RESTARTABLE(read(fd, buf, 1), res);
    if (res < 0) {
        JNU_ThrowIOExceptionWithLastError(env, "drain1 failed");
    }
}

JNIEXPORT void JNICALL
Java_sun_nio_ch_EPollPort_close0(JNIEnv *env, jclass c, jint fd) {
    int res;
    RESTARTABLE(close(fd), res);
}
