#include "java_net_InetAddressImplFactory.h"
#include "net_util.h"

/*
 * InetAddressImplFactory
 */


/*
 * Class:     java_net_InetAddressImplFactory
 * Method:    isIPv6Supported
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL
Java_java_net_InetAddressImplFactory_isIPv6Supported(JNIEnv *env, jobject this)
{
    if (ipv6_available()) {
        return JNI_TRUE;
    } else {
        return JNI_FALSE;
    }
}
