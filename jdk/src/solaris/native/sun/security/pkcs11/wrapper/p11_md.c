/*
 * pkcs11wrapper.c
 * 18.05.2001
 *
 * This module contains the native functions of the Java to PKCS#11 interface
 * which are platform dependent. This includes loading a dynamic link libary,
 * retrieving the function list and unloading the dynamic link library.
 */

#include "pkcs11wrapper.h"

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

#include <dlfcn.h>

#include <jni.h>

#include "sun_security_pkcs11_wrapper_PKCS11.h"

/*
 * Class:     sun_security_pkcs11_wrapper_PKCS11
 * Method:    connect
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_sun_security_pkcs11_wrapper_PKCS11_connect
    (JNIEnv *env, jobject obj, jstring jPkcs11ModulePath, jstring jGetFunctionList)
{
    void *hModule;
    char *error;
    CK_C_GetFunctionList C_GetFunctionList=NULL;
    CK_RV rv;
    ModuleData *moduleData;
    jobject globalPKCS11ImplementationReference;
    char *systemErrorMessage;
    char *exceptionMessage;
    const char *getFunctionListStr;

    const char *libraryNameStr = (*env)->GetStringUTFChars(env, jPkcs11ModulePath, 0);
    TRACE1("DEBUG: connect to PKCS#11 module: %s ... ", libraryNameStr);


    /*
     * Load the PKCS #11 DLL
     */
    dlerror(); /* clear any old error message not fetched */
#ifdef DEBUG
    hModule = dlopen(libraryNameStr, RTLD_NOW);
#else
    hModule = dlopen(libraryNameStr, RTLD_LAZY);
#endif /* DEBUG */

    if (hModule == NULL) {
        systemErrorMessage = dlerror();
        exceptionMessage = (char *) malloc(sizeof(char) * (strlen(systemErrorMessage) + strlen(libraryNameStr) + 1));
        if (exceptionMessage == NULL) {
            throwOutOfMemoryError(env, 0);
            return;
        }
        strcpy(exceptionMessage, systemErrorMessage);
        strcat(exceptionMessage, libraryNameStr);
        throwIOException(env, exceptionMessage);
        (*env)->ReleaseStringUTFChars(env, jPkcs11ModulePath, libraryNameStr);
        free(exceptionMessage);
        return;
    }

    /*
     * Get function pointer to C_GetFunctionList
     */
    dlerror(); /* clear any old error message not fetched */
    // with the old JAR file jGetFunctionList is null, temporarily check for that
    if (jGetFunctionList != NULL) {
        getFunctionListStr = (*env)->GetStringUTFChars(env, jGetFunctionList, 0);
        C_GetFunctionList = (CK_C_GetFunctionList) dlsym(hModule, getFunctionListStr);
        (*env)->ReleaseStringUTFChars(env, jGetFunctionList, getFunctionListStr);
    }
    if (C_GetFunctionList == NULL) {
        throwIOException(env, "ERROR: C_GetFunctionList == NULL");
        return;
    } else if ( (systemErrorMessage = dlerror()) != NULL ){
        throwIOException(env, systemErrorMessage);
        return;
    }

    /*
     * Get function pointers to all PKCS #11 functions
     */
    moduleData = (ModuleData *) malloc(sizeof(ModuleData));
    if (moduleData == NULL) {
        dlclose(hModule);
        throwOutOfMemoryError(env, 0);
        return;
    }
    moduleData->hModule = hModule;
    moduleData->applicationMutexHandler = NULL;
    rv = (C_GetFunctionList)(&(moduleData->ckFunctionListPtr));
    globalPKCS11ImplementationReference = (*env)->NewGlobalRef(env, obj);
    putModuleEntry(env, globalPKCS11ImplementationReference, moduleData);

    (*env)->ReleaseStringUTFChars(env, jPkcs11ModulePath, libraryNameStr);
    TRACE0("FINISHED\n");

    if(ckAssertReturnValueOK(env, rv) != CK_ASSERT_OK) { return; }
}

/*
 * Class:     sun_security_pkcs11_wrapper_PKCS11
 * Method:    disconnect
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_sun_security_pkcs11_wrapper_PKCS11_disconnect
    (JNIEnv *env, jobject obj)
{
    ModuleData *moduleData;
    TRACE0("DEBUG: disconnecting module...");
    moduleData = removeModuleEntry(env, obj);

    if (moduleData != NULL) {
        dlclose(moduleData->hModule);
    }

    free(moduleData);
    TRACE0("FINISHED\n");

}
