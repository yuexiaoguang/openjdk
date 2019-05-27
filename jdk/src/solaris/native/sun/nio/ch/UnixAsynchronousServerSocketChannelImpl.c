#include "sun_nio_ch_UnixAsynchronousServerSocketChannelImpl.h"

extern void Java_sun_nio_ch_ServerSocketChannelImpl_initIDs(JNIEnv* env,
    jclass c);

extern jint Java_sun_nio_ch_ServerSocketChannelImpl_accept0(JNIEnv* env,
    jobject this, jobject ssfdo, jobject newfdo, jobjectArray isaa);

JNIEXPORT void JNICALL
Java_sun_nio_ch_UnixAsynchronousServerSocketChannelImpl_initIDs(JNIEnv* env,
    jclass c)
{
    Java_sun_nio_ch_ServerSocketChannelImpl_initIDs(env, c);
}

JNIEXPORT jint JNICALL
Java_sun_nio_ch_UnixAsynchronousServerSocketChannelImpl_accept0(JNIEnv* env,
    jobject this, jobject ssfdo, jobject newfdo, jobjectArray isaa)
{
    return Java_sun_nio_ch_ServerSocketChannelImpl_accept0(env, this,
        ssfdo, newfdo, isaa);
}
