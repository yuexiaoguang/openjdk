#include "sun_nio_ch_sctp_SctpServerChannelImpl.h"

extern void Java_sun_nio_ch_ServerSocketChannelImpl_initIDs(JNIEnv* env,
    jclass c);

extern jint Java_sun_nio_ch_ServerSocketChannelImpl_accept0(JNIEnv* env,
    jobject this, jobject ssfdo, jobject newfdo, jobjectArray isaa);

/*
 * Class:     sun_nio_ch_sctp_SctpServerChannelImpl
 * Method:    initIDs
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_sun_nio_ch_sctp_SctpServerChannelImpl_initIDs
  (JNIEnv* env, jclass c) {
    Java_sun_nio_ch_ServerSocketChannelImpl_initIDs(env, c);
}

/*
 * Class:     sun_nio_ch_sctp_SctpServerChannelImpl
 * Method:    accept0
 * Signature: (Ljava/io/FileDescriptor;Ljava/io/FileDescriptor;[Ljava/net/InetSocketAddress;)I
 */
JNIEXPORT jint JNICALL Java_sun_nio_ch_sctp_SctpServerChannelImpl_accept0
  (JNIEnv* env, jobject this, jobject ssfdo, jobject newfdo, jobjectArray isaa) {
    return Java_sun_nio_ch_ServerSocketChannelImpl_accept0(env, this,
                                                           ssfdo, newfdo, isaa);
}
