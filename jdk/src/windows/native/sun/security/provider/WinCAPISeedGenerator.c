/* Need to define this to get CAPI functions included */
#ifndef _WIN32_WINNT
#define _WIN32_WINNT 0x0400
#endif

#include <windows.h>
#include <wincrypt.h>
#include <jni.h>
#include "sun_security_provider_NativeSeedGenerator.h"

/*
 * Get a random seed from the MS CryptoAPI. Return true if successful, false
 * otherwise.
 *
 * Some early versions of Windows 95 do not support the required functions.
 * Use runtime linking to avoid problems.
 *
 */
JNIEXPORT jboolean JNICALL Java_sun_security_provider_NativeSeedGenerator_nativeGenerateSeed
  (JNIEnv *env, jclass clazz, jbyteArray randArray)
{
    HCRYPTPROV hCryptProv;
    jboolean result = JNI_FALSE;
    jsize numBytes;
    jbyte* randBytes;

    if (CryptAcquireContextA(&hCryptProv, "J2SE", NULL, PROV_RSA_FULL, 0) == FALSE) {
        /* If CSP context hasn't been created, create one. */
        if (CryptAcquireContextA(&hCryptProv, "J2SE", NULL, PROV_RSA_FULL,
                CRYPT_NEWKEYSET) == FALSE) {
            return result;
        }
    }

    numBytes = (*env)->GetArrayLength(env, randArray);
    randBytes = (*env)->GetByteArrayElements(env, randArray, NULL);
    if (CryptGenRandom(hCryptProv, numBytes, randBytes)) {
        result = JNI_TRUE;
    }
    (*env)->ReleaseByteArrayElements(env, randArray, randBytes, 0);

    CryptReleaseContext(hCryptProv, 0);

    return result;
}
