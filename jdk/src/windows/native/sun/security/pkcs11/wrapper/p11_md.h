/*
 * platoform.h
 * 10.12.2001
 *
 * declaration of all platform dependent functions used by pkcs11wrapper.c
 */
/* defines for WIN32 platform *************************************************/

#include <windows.h>

/* statement according to PKCS11 docu */
#pragma pack(push, cryptoki, 1)

/* definitions according to PKCS#11 docu for Win32 environment */
#define CK_PTR *
#define CK_DEFINE_FUNCTION(returnType, name) returnType __declspec(dllexport) name
#define CK_DECLARE_FUNCTION(returnType, name) returnType __declspec(dllimport) name
#define CK_DECLARE_FUNCTION_POINTER(returnType, name) returnType __declspec(dllimport) (* name)
#define CK_CALLBACK_FUNCTION(returnType, name) returnType (* name)
#ifndef NULL_PTR
#define NULL_PTR 0
#endif /* NULL_PTR */

/* to avoid clash with Win32 #define */
#ifdef CreateMutex
#undef CreateMutex
#endif /* CreateMutex */

#include "pkcs11.h"

/* statement according to PKCS11 docu */
#pragma pack(pop, cryptoki)

#include "jni.h"

/* A data structure to hold required information about a PKCS#11 module. */
struct ModuleData {

    HINSTANCE hModule;

    /* The pointer to the PKCS#11 functions of this module. */
    CK_FUNCTION_LIST_PTR ckFunctionListPtr;

    /* Reference to the object to use for mutex handling. NULL, if not used. */
    jobject applicationMutexHandler;

};
typedef struct ModuleData ModuleData;
