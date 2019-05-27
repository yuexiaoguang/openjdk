#define UNICODE
#include <jni.h>
#include <windows.h>
#include <stdlib.h>

/*
 * Class:     sun_security_krb5_Config
 * Method:    getWindowsDirectory
 * Signature: (Z)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_sun_security_krb5_Config_getWindowsDirectory(
        JNIEnv* env, jclass configClass, jboolean isSystem) {
    TCHAR lpPath[MAX_PATH+1];
    UINT len;
    if (isSystem) {
        len = GetSystemWindowsDirectory(lpPath, MAX_PATH);
    } else {
        len = GetWindowsDirectory(lpPath, MAX_PATH);
    }
    if (len) {
        return (*env)->NewString(env, lpPath, len);
    } else {
        return NULL;
    }
}
