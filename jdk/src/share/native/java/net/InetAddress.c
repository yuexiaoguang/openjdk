
#include <string.h>

#include "java_net_InetAddress.h"
#include "net_util.h"

/************************************************************************
 * InetAddress
 */

jclass ia_class;
jclass iac_class;
jfieldID ia_holderID;
jfieldID iac_addressID;
jfieldID iac_familyID;
jfieldID iac_hostNameID;
jfieldID ia_preferIPv6AddressID;

/*
 * Class:     java_net_InetAddress
 * Method:    init
 * Signature: ()V
 */
JNIEXPORT void JNICALL
Java_java_net_InetAddress_init(JNIEnv *env, jclass cls) {
    jclass c = (*env)->FindClass(env,"java/net/InetAddress");
    CHECK_NULL(c);
    ia_class = (*env)->NewGlobalRef(env, c);
    CHECK_NULL(ia_class);
    c = (*env)->FindClass(env,"java/net/InetAddress$InetAddressHolder");
    CHECK_NULL(c);
    iac_class = (*env)->NewGlobalRef(env, c);
    ia_holderID = (*env)->GetFieldID(env, ia_class, "holder", "Ljava/net/InetAddress$InetAddressHolder;");
    CHECK_NULL(ia_holderID);
    ia_preferIPv6AddressID = (*env)->GetStaticFieldID(env, ia_class, "preferIPv6Address", "Z");
    CHECK_NULL(ia_preferIPv6AddressID);

    iac_addressID = (*env)->GetFieldID(env, iac_class, "address", "I");
    CHECK_NULL(iac_addressID);
    iac_familyID = (*env)->GetFieldID(env, iac_class, "family", "I");
    CHECK_NULL(iac_familyID);
    iac_hostNameID = (*env)->GetFieldID(env, iac_class, "hostName", "Ljava/lang/String;");
}
