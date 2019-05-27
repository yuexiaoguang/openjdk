#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "jlong.h"

#include <stdlib.h>
#include <string.h>

#include <CoreFoundation/CoreFoundation.h>

JNIEXPORT jcharArray JNICALL
Java_sun_nio_fs_MacOSXNativeDispatcher_normalizepath(JNIEnv* env, jclass this,
                                                     jcharArray path,
                                                     jint form)
{
    jcharArray result = NULL;
    char chars_buf[(PATH_MAX + 1) * 2];     // utf16 + zero padding
    CFMutableStringRef csref = CFStringCreateMutable(NULL, 0);
    if (csref == NULL) {
        JNU_ThrowOutOfMemoryError(env, "native heap");
    } else {
        char *chars = (char*)(*env)->GetPrimitiveArrayCritical(env, path, 0);
        jsize len = (*env)->GetArrayLength(env, path);
        CFStringAppendCharacters(csref, (const UniChar*)chars, len);
        (*env)->ReleasePrimitiveArrayCritical(env, path, chars, 0);
        CFStringNormalize(csref, form);
        len = CFStringGetLength(csref);
        if (len < PATH_MAX) {
            if (CFStringGetCString(csref, chars_buf, sizeof(chars_buf), kCFStringEncodingUTF16)) {
                result = (*env)->NewCharArray(env, len);
                (*env)->SetCharArrayRegion(env, result, 0, len, (jchar*)&chars_buf);
            }
        } else {
            int ulen = (len + 1) * 2;
            chars = malloc(ulen);
            if (chars == NULL) {
                CFRelease(csref);
                JNU_ThrowOutOfMemoryError(env, "native heap");
                return result;
            } else {
                if (CFStringGetCString(csref, chars, ulen, kCFStringEncodingUTF16)) {
                    result = (*env)->NewCharArray(env, len);
                    (*env)->SetCharArrayRegion(env, result, 0, len, (jchar*)chars);
                }
                free(chars);
            }
        }
        CFRelease(csref);
    }
    return result;
}
