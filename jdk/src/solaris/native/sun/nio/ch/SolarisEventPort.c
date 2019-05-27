#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "jlong.h"
#include "nio_util.h"

#include <stdlib.h>
#include <dlfcn.h>
#include <sys/types.h>
#include <port.h>

#include "sun_nio_ch_SolarisEventPort.h"

JNIEXPORT jint JNICALL
Java_sun_nio_ch_SolarisEventPort_port_1create
    (JNIEnv* env, jclass clazz)
{
    int port = port_create();
    if (port == -1) {
        JNU_ThrowIOExceptionWithLastError(env, "port_create");
    }
    return (jint)port;
}

JNIEXPORT void JNICALL
Java_sun_nio_ch_SolarisEventPort_port_1close
    (JNIEnv* env, jclass clazz, jint port)
{
    int res;
    RESTARTABLE(close(port), res);
}

JNIEXPORT jboolean JNICALL
Java_sun_nio_ch_SolarisEventPort_port_1associate
    (JNIEnv* env, jclass clazz, jint port, jint source, jlong objectAddress, jint events)
{
    uintptr_t object = (uintptr_t)jlong_to_ptr(objectAddress);
    if (port_associate((int)port, (int)source, object, (int)events, NULL) == 0) {
        return JNI_TRUE;
    } else {
        if (errno != EBADFD)
            JNU_ThrowIOExceptionWithLastError(env, "port_associate");
        return JNI_FALSE;
    }
}

JNIEXPORT jboolean JNICALL
Java_sun_nio_ch_SolarisEventPort_port_1dissociate
    (JNIEnv* env, jclass clazz, jint port, jint source, jlong objectAddress)
{
    uintptr_t object = (uintptr_t)jlong_to_ptr(objectAddress);

    if (port_dissociate((int)port, (int)source, object) == 0) {
        return JNI_TRUE;
    } else {
        if (errno != ENOENT)
            JNU_ThrowIOExceptionWithLastError(env, "port_dissociate");
        return JNI_FALSE;
    }
}

JNIEXPORT void JNICALL
Java_sun_nio_ch_SolarisEventPort_port_1send(JNIEnv* env, jclass clazz,
    jint port, jint events)
{
    if (port_send((int)port, (int)events, NULL) == -1) {
        JNU_ThrowIOExceptionWithLastError(env, "port_send");
    }
}

JNIEXPORT void JNICALL
Java_sun_nio_ch_SolarisEventPort_port_1get(JNIEnv* env, jclass clazz,
    jint port, jlong eventAddress)
{
    int res;
    port_event_t* ev = (port_event_t*)jlong_to_ptr(eventAddress);

    RESTARTABLE(port_get((int)port, ev, NULL), res);
    if (res == -1) {
        JNU_ThrowIOExceptionWithLastError(env, "port_get");
    }
}

JNIEXPORT jint JNICALL
Java_sun_nio_ch_SolarisEventPort_port_1getn(JNIEnv* env, jclass clazz,
    jint port, jlong arrayAddress, jint max, jlong timeout)
{
    int res;
    uint_t n = 1;
    port_event_t* list = (port_event_t*)jlong_to_ptr(arrayAddress);
    timespec_t ts;
    timespec_t* tsp;

    if (timeout >= 0L) {
        ts.tv_sec = timeout / 1000;
        ts.tv_nsec = 1000000 * (timeout % 1000);
        tsp = &ts;
    } else {
        tsp = NULL;
    }

    res = port_getn((int)port, list, (uint_t)max, &n, tsp);
    if (res == -1) {
        if (errno != ETIME && errno != EINTR)
            JNU_ThrowIOExceptionWithLastError(env, "port_getn");
    }

    return (jint)n;
}
