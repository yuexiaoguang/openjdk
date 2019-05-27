#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include <dlfcn.h>

#include <jni_util.h>

#include "j2secmod.h"

void *findFunction(JNIEnv *env, jlong jHandle, const char *functionName) {
    void *hModule = (void*)jlong_to_ptr(jHandle);
    void *fAddress = dlsym(hModule, functionName);
    if (fAddress == NULL) {
        char errorMessage[256];
        snprintf(errorMessage, sizeof(errorMessage), "Symbol not found: %s", functionName);
        throwNullPointerException(env, errorMessage);
        return NULL;
    }
    return fAddress;
}

JNIEXPORT jlong JNICALL Java_sun_security_pkcs11_Secmod_nssGetLibraryHandle
  (JNIEnv *env, jclass thisClass, jstring jLibName)
{
    const char *libName = (*env)->GetStringUTFChars(env, jLibName, NULL);
    // look up existing handle only, do not load
    void *hModule = dlopen(libName, RTLD_NOLOAD);
    dprintf2("-handle for %s: %u\n", libName, hModule);
    (*env)->ReleaseStringUTFChars(env, jLibName, libName);
    return ptr_to_jlong(hModule);
}

JNIEXPORT jlong JNICALL Java_sun_security_pkcs11_Secmod_nssLoadLibrary
  (JNIEnv *env, jclass thisClass, jstring jLibName)
{
    void *hModule;
    const char *libName = (*env)->GetStringUTFChars(env, jLibName, NULL);

    dprintf1("-lib %s\n", libName);
    hModule = dlopen(libName, RTLD_LAZY);
    (*env)->ReleaseStringUTFChars(env, jLibName, libName);
    dprintf2("-handle: %u (0X%X)\n", hModule, hModule);

    if (hModule == NULL) {
        throwIOException(env, dlerror());
        return 0;
    }

    return ptr_to_jlong(hModule);
}
