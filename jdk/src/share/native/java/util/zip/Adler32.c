/*
 * Native method support for java.util.zip.Adler32
 */

#include "jni.h"
#include "jni_util.h"
#include "jlong.h"
#include <zlib.h>

#include "java_util_zip_Adler32.h"

JNIEXPORT jint JNICALL
Java_java_util_zip_Adler32_update(JNIEnv *env, jclass cls, jint adler, jint b)
{
    Bytef buf[1];

    buf[0] = (Bytef)b;
    return adler32(adler, buf, 1);
}

JNIEXPORT jint JNICALL
Java_java_util_zip_Adler32_updateBytes(JNIEnv *env, jclass cls, jint adler,
                                       jarray b, jint off, jint len)
{
    Bytef *buf = (*env)->GetPrimitiveArrayCritical(env, b, 0);
    if (buf) {
        adler = adler32(adler, buf + off, len);
        (*env)->ReleasePrimitiveArrayCritical(env, b, buf, 0);
    }
    return adler;
}


JNIEXPORT jint JNICALL
Java_java_util_zip_Adler32_updateByteBuffer(JNIEnv *env, jclass cls, jint adler,
                                       jlong address, jint off, jint len)
{
    Bytef *buf = (Bytef *)jlong_to_ptr(address);
    if (buf) {
        adler = adler32(adler, buf + off, len);
    }
    return adler;
}


