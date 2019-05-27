#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "jlong.h"
#include "nio_util.h"

#include "sun_nio_ch_KQueue.h"

#include <strings.h>
#include <sys/types.h>
#include <sys/event.h>
#include <sys/time.h>

JNIEXPORT jint JNICALL
Java_sun_nio_ch_KQueue_keventSize(JNIEnv* env, jclass this)
{
    return sizeof(struct kevent);
}

JNIEXPORT jint JNICALL
Java_sun_nio_ch_KQueue_identOffset(JNIEnv* env, jclass this)
{
    return offsetof(struct kevent, ident);
}

JNIEXPORT jint JNICALL
Java_sun_nio_ch_KQueue_filterOffset(JNIEnv* env, jclass this)
{
    return offsetof(struct kevent, filter);
}

JNIEXPORT jint JNICALL
Java_sun_nio_ch_KQueue_flagsOffset(JNIEnv* env, jclass this)
{
    return offsetof(struct kevent, flags);
}

JNIEXPORT jint JNICALL
Java_sun_nio_ch_KQueue_kqueue(JNIEnv *env, jclass c) {
    int kqfd = kqueue();
    if (kqfd < 0) {
        JNU_ThrowIOExceptionWithLastError(env, "kqueue failed");
    }
    return kqfd;
}

JNIEXPORT jint JNICALL
Java_sun_nio_ch_KQueue_keventRegister(JNIEnv *env, jclass c, jint kqfd,
                                      jint fd, jint filter, jint flags)

{
    struct kevent changes[1];
    struct timespec timeout = {0, 0};
    int res;

    EV_SET(&changes[0], fd, filter, flags, 0, 0, 0);
    RESTARTABLE(kevent(kqfd, &changes[0], 1, NULL, 0, &timeout), res);
    return (res == -1) ? errno : 0;
}

JNIEXPORT jint JNICALL
Java_sun_nio_ch_KQueue_keventPoll(JNIEnv *env, jclass c,
                                  jint kqfd, jlong address, jint nevents)
{
    struct kevent *events = jlong_to_ptr(address);
    int res;

    RESTARTABLE(kevent(kqfd, NULL, 0, events, nevents, NULL), res);
    if (res < 0) {
        JNU_ThrowIOExceptionWithLastError(env, "kqueue failed");
    }
    return res;
}
