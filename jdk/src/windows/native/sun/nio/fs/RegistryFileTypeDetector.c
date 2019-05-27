#include <windows.h>

#include "jni.h"
#include "jni_util.h"
#include "jlong.h"

#include "sun_nio_fs_RegistryFileTypeDetector.h"


JNIEXPORT jstring JNICALL
Java_sun_nio_fs_RegistryFileTypeDetector_queryStringValue(JNIEnv* env, jclass this,
    jlong keyAddress, jlong nameAddress)
{
    LPCWSTR lpSubKey= (LPCWSTR)jlong_to_ptr(keyAddress);
    LPWSTR lpValueName = (LPWSTR)jlong_to_ptr(nameAddress);
    LONG res;
    HKEY hKey;
    jstring result = NULL;

    res = RegOpenKeyExW(HKEY_CLASSES_ROOT, lpSubKey, 0, KEY_READ, &hKey);
    if (res == ERROR_SUCCESS) {
        DWORD type;
        BYTE data[255];
        DWORD size = sizeof(data);

        res = RegQueryValueExW(hKey, lpValueName, NULL, &type, (LPBYTE)&data, &size);
        if (res == ERROR_SUCCESS) {
            if (type == REG_SZ) {
                jsize len = (jsize)wcslen((WCHAR*)data);
                result = (*env)->NewString(env, (const jchar*)&data, len);
            }
        }

        RegCloseKey(hKey);
    }
    return result;
}
