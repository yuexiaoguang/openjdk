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

#include <windows.h>

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
    HINSTANCE hModule;
    CK_C_GetFunctionList C_GetFunctionList;
    CK_RV rv;
    ModuleData *moduleData;
    jobject globalPKCS11ImplementationReference;
    LPVOID lpMsgBuf;
    char *exceptionMessage;
    const char *getFunctionListStr;

    const char *libraryNameStr = (*env)->GetStringUTFChars(env, jPkcs11ModulePath, 0);
    TRACE1("DEBUG: connect to PKCS#11 module: %s ... ", libraryNameStr);


  /*
   * Load the PKCS #11 DLL
   */
    hModule = LoadLibrary(libraryNameStr);
    if (hModule == NULL) {
        FormatMessage(
            FORMAT_MESSAGE_ALLOCATE_BUFFER |
            FORMAT_MESSAGE_FROM_SYSTEM |
            FORMAT_MESSAGE_IGNORE_INSERTS,
            NULL,
            GetLastError(),
            0, /* Default language */
            (LPTSTR) &lpMsgBuf,
            0,
            NULL
        );
        exceptionMessage = (char *) malloc(sizeof(char) * (strlen((LPTSTR) lpMsgBuf) + strlen(libraryNameStr) + 1));
        strcpy(exceptionMessage, (LPTSTR) lpMsgBuf);
        strcat(exceptionMessage, libraryNameStr);
        throwIOException(env, (LPTSTR) exceptionMessage);
        /* Free the buffer. */
        free(exceptionMessage);
        LocalFree(lpMsgBuf);
        return;
    }

    /*
     * Get function pointer to C_GetFunctionList
     */
    getFunctionListStr = (*env)->GetStringUTFChars(env, jGetFunctionList, 0);
    C_GetFunctionList = (CK_C_GetFunctionList) GetProcAddress(hModule, getFunctionListStr);
    (*env)->ReleaseStringUTFChars(env, jGetFunctionList, getFunctionListStr);
    if (C_GetFunctionList == NULL) {
        FormatMessage(
            FORMAT_MESSAGE_ALLOCATE_BUFFER |
            FORMAT_MESSAGE_FROM_SYSTEM |
            FORMAT_MESSAGE_IGNORE_INSERTS,
            NULL,
            GetLastError(),
            0, /* Default language */
            (LPTSTR) &lpMsgBuf,
            0,
            NULL
        );
        throwIOException(env, (LPTSTR) lpMsgBuf);
        /* Free the buffer. */
        LocalFree( lpMsgBuf );
        return;
    }

    /*
     * Get function pointers to all PKCS #11 functions
     */
    moduleData = (ModuleData *) malloc(sizeof(ModuleData));
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
        FreeLibrary(moduleData->hModule);
    }

    free(moduleData);
    TRACE0("FINISHED\n");
}
