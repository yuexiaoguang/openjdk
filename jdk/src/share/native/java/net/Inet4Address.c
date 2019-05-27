
#include <string.h>

#include "java_net_Inet4Address.h"
#include "net_util.h"

/************************************************************************
 * Inet4Address
 */
jclass ia4_class;
jmethodID ia4_ctrID;

/*
 * Class:     java_net_Inet4Address
 * Method:    init
 * Signature: ()V
 */
JNIEXPORT void JNICALL
Java_java_net_Inet4Address_init(JNIEnv *env, jclass cls) {
    jclass c = (*env)->FindClass(env, "java/net/Inet4Address");
    CHECK_NULL(c);
    ia4_class = (*env)->NewGlobalRef(env, c);
    CHECK_NULL(ia4_class);
    ia4_ctrID = (*env)->GetMethodID(env, ia4_class, "<init>", "()V");
}
