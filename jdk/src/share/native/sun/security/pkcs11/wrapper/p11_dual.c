#include "pkcs11wrapper.h"

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

#include "sun_security_pkcs11_wrapper_PKCS11.h"

#ifdef P11_ENABLE_C_DIGESTENCRYPTUPDATE
/*
 * Class:     sun_security_pkcs11_wrapper_PKCS11
 * Method:    C_DigestEncryptUpdate
 * Signature: (J[B)[B
 * Parametermapping:                    *PKCS11*
 * @param   jlong jSessionHandle        CK_SESSION_HANDLE hSession
 * @param   jbyteArray jPart            CK_BYTE_PTR pPart
 *                                      CK_ULONG ulPartLen
 * @return  jbyteArray jEncryptedPart   CK_BYTE_PTR pEncryptedPart
 *                                      CK_ULONG_PTR pulEncryptedPartLen
 */
JNIEXPORT jbyteArray JNICALL Java_sun_security_pkcs11_wrapper_PKCS11_C_1DigestEncryptUpdate
    (JNIEnv *env, jobject obj, jlong jSessionHandle, jbyteArray jPart)
{
    CK_SESSION_HANDLE ckSessionHandle;
    CK_BYTE_PTR ckpPart = NULL_PTR, ckpEncryptedPart;
    CK_ULONG ckPartLength, ckEncryptedPartLength = 0;
    jbyteArray jEncryptedPart = NULL;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return NULL; }

    ckSessionHandle = jLongToCKULong(jSessionHandle);
    jByteArrayToCKByteArray(env, jPart, &ckpPart, &ckPartLength);
    if ((*env)->ExceptionCheck(env)) { return NULL; }

    rv = (*ckpFunctions->C_DigestEncryptUpdate)(ckSessionHandle, ckpPart, ckPartLength, NULL_PTR, &ckEncryptedPartLength);
    if (ckAssertReturnValueOK(env, rv) != CK_ASSERT_OK) {
        free(ckpPart);
        return NULL;
    }

    ckpEncryptedPart = (CK_BYTE_PTR) malloc(ckEncryptedPartLength * sizeof(CK_BYTE));
    if (ckpEncryptedPart == NULL) {
        free(ckpPart);
        throwOutOfMemoryError(env, 0);
        return NULL;
    }

    rv = (*ckpFunctions->C_DigestEncryptUpdate)(ckSessionHandle, ckpPart, ckPartLength, ckpEncryptedPart, &ckEncryptedPartLength);
    if (ckAssertReturnValueOK(env, rv) == CK_ASSERT_OK) {
        jEncryptedPart = ckByteArrayToJByteArray(env, ckpEncryptedPart, ckEncryptedPartLength);
    }
    free(ckpPart);
    free(ckpEncryptedPart);

    return jEncryptedPart ;
}
#endif

#ifdef P11_ENABLE_C_DECRYPTDIGESTUPDATE
/*
 * Class:     sun_security_pkcs11_wrapper_PKCS11
 * Method:    C_DecryptDigestUpdate
 * Signature: (J[B)[B
 * Parametermapping:                    *PKCS11*
 * @param   jlong jSessionHandle        CK_SESSION_HANDLE hSession
 * @param   jbyteArray jEncryptedPart   CK_BYTE_PTR pEncryptedPart
 *                                      CK_ULONG ulEncryptedPartLen
 * @return  jbyteArray jPart            CK_BYTE_PTR pPart
 *                                      CK_ULONG_PTR pulPartLen
 */
JNIEXPORT jbyteArray JNICALL Java_sun_security_pkcs11_wrapper_PKCS11_C_1DecryptDigestUpdate
    (JNIEnv *env, jobject obj, jlong jSessionHandle, jbyteArray jEncryptedPart)
{
    CK_SESSION_HANDLE ckSessionHandle;
    CK_BYTE_PTR ckpPart, ckpEncryptedPart = NULL_PTR;
    CK_ULONG ckPartLength = 0, ckEncryptedPartLength;
    jbyteArray jPart = NULL;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return NULL; }

    ckSessionHandle = jLongToCKULong(jSessionHandle);
    jByteArrayToCKByteArray(env, jEncryptedPart, &ckpEncryptedPart, &ckEncryptedPartLength);
    if ((*env)->ExceptionCheck(env)) { return NULL; }

    rv = (*ckpFunctions->C_DecryptDigestUpdate)(ckSessionHandle, ckpEncryptedPart, ckEncryptedPartLength, NULL_PTR, &ckPartLength);
    if (ckAssertReturnValueOK(env, rv) != CK_ASSERT_OK) {
        free(ckpEncryptedPart);
        return NULL;
    }

    ckpPart = (CK_BYTE_PTR) malloc(ckPartLength * sizeof(CK_BYTE));
    if (ckpPart == NULL) {
        free(ckpEncryptedPart);
        throwOutOfMemoryError(env, 0);
        return NULL;
    }

    rv = (*ckpFunctions->C_DecryptDigestUpdate)(ckSessionHandle, ckpEncryptedPart, ckEncryptedPartLength, ckpPart, &ckPartLength);
    if (ckAssertReturnValueOK(env, rv) == CK_ASSERT_OK) {
        jPart = ckByteArrayToJByteArray(env, ckpPart, ckPartLength);
    }
    free(ckpEncryptedPart);
    free(ckpPart);

    return jPart ;
}
#endif

#ifdef P11_ENABLE_C_SIGNENCRYPTUPDATE
/*
 * Class:     sun_security_pkcs11_wrapper_PKCS11
 * Method:    C_SignEncryptUpdate
 * Signature: (J[B)[B
 * Parametermapping:                    *PKCS11*
 * @param   jlong jSessionHandle        CK_SESSION_HANDLE hSession
 * @param   jbyteArray jPart            CK_BYTE_PTR pPart
 *                                      CK_ULONG ulPartLen
 * @return  jbyteArray jEncryptedPart   CK_BYTE_PTR pEncryptedPart
 *                                      CK_ULONG_PTR pulEncryptedPartLen
 */
JNIEXPORT jbyteArray JNICALL Java_sun_security_pkcs11_wrapper_PKCS11_C_1SignEncryptUpdate
    (JNIEnv *env, jobject obj, jlong jSessionHandle, jbyteArray jPart)
{
    CK_SESSION_HANDLE ckSessionHandle;
    CK_BYTE_PTR ckpPart = NULL_PTR, ckpEncryptedPart;
    CK_ULONG ckPartLength, ckEncryptedPartLength = 0;
    jbyteArray jEncryptedPart = NULL;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return NULL; }

    ckSessionHandle = jLongToCKULong(jSessionHandle);
    jByteArrayToCKByteArray(env, jPart, &ckpPart, &ckPartLength);
    if ((*env)->ExceptionCheck(env)) { return NULL; }

    rv = (*ckpFunctions->C_SignEncryptUpdate)(ckSessionHandle, ckpPart, ckPartLength, NULL_PTR, &ckEncryptedPartLength);
    if (ckAssertReturnValueOK(env, rv) != CK_ASSERT_OK) {
        free(ckpPart);
        return NULL;
    }

    ckpEncryptedPart = (CK_BYTE_PTR) malloc(ckEncryptedPartLength * sizeof(CK_BYTE));
    if (ckpEncryptedPart == NULL) {
        free(ckpPart);
        throwOutOfMemoryError(env, 0);
        return NULL;
    }

    rv = (*ckpFunctions->C_SignEncryptUpdate)(ckSessionHandle, ckpPart, ckPartLength, ckpEncryptedPart, &ckEncryptedPartLength);
    if (ckAssertReturnValueOK(env, rv) == CK_ASSERT_OK) {
        jEncryptedPart = ckByteArrayToJByteArray(env, ckpEncryptedPart, ckEncryptedPartLength);
    }
    free(ckpPart);
    free(ckpEncryptedPart);

    return jEncryptedPart ;
}
#endif

#ifdef P11_ENABLE_C_DECRYPTVERIFYUPDATE
/*
 * Class:     sun_security_pkcs11_wrapper_PKCS11
 * Method:    C_DecryptVerifyUpdate
 * Signature: (J[B)[B
 * Parametermapping:                    *PKCS11*
 * @param   jlong jSessionHandle        CK_SESSION_HANDLE hSession
 * @param   jbyteArray jEncryptedPart   CK_BYTE_PTR pEncryptedPart
 *                                      CK_ULONG ulEncryptedPartLen
 * @return  jbyteArray jPart            CK_BYTE_PTR pPart
 *                                      CK_ULONG_PTR pulPartLen
 */
JNIEXPORT jbyteArray JNICALL Java_sun_security_pkcs11_wrapper_PKCS11_C_1DecryptVerifyUpdate
    (JNIEnv *env, jobject obj, jlong jSessionHandle, jbyteArray jEncryptedPart)
{
    CK_SESSION_HANDLE ckSessionHandle;
    CK_BYTE_PTR ckpPart, ckpEncryptedPart = NULL_PTR;
    CK_ULONG ckPartLength = 0, ckEncryptedPartLength;
    jbyteArray jPart = NULL;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return NULL; }

    ckSessionHandle = jLongToCKULong(jSessionHandle);
    jByteArrayToCKByteArray(env, jEncryptedPart, &ckpEncryptedPart, &ckEncryptedPartLength);
    if ((*env)->ExceptionCheck(env)) { return NULL; }

    rv = (*ckpFunctions->C_DecryptVerifyUpdate)(ckSessionHandle, ckpEncryptedPart, ckEncryptedPartLength, NULL_PTR, &ckPartLength);
    if (ckAssertReturnValueOK(env, rv) != CK_ASSERT_OK) {
        free(ckpEncryptedPart);
        return NULL;
    }

    ckpPart = (CK_BYTE_PTR) malloc(ckPartLength * sizeof(CK_BYTE));
    if (ckpPart == NULL) {
        free(ckpEncryptedPart);
        throwOutOfMemoryError(env, 0);
        return NULL;
    }

    rv = (*ckpFunctions->C_DecryptVerifyUpdate)(ckSessionHandle, ckpEncryptedPart, ckEncryptedPartLength, ckpPart, &ckPartLength);

    if (ckAssertReturnValueOK(env, rv) == CK_ASSERT_OK) {
        jPart = ckByteArrayToJByteArray(env, ckpPart, ckPartLength);
    }
    free(ckpEncryptedPart);
    free(ckpPart);

    return jPart ;
}
#endif

#ifdef P11_ENABLE_C_GETFUNCTIONSTATUS
/*
 * Class:     sun_security_pkcs11_wrapper_PKCS11
 * Method:    C_GetFunctionStatus
 * Signature: (J)V
 * Parametermapping:                    *PKCS11*
 * @param   jlong jSessionHandle        CK_SESSION_HANDLE hSession
 */
JNIEXPORT void JNICALL Java_sun_security_pkcs11_wrapper_PKCS11_C_1GetFunctionStatus
    (JNIEnv *env, jobject obj, jlong jSessionHandle)
{
    CK_SESSION_HANDLE ckSessionHandle;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return; }

    ckSessionHandle = jLongToCKULong(jSessionHandle);

    /* C_GetFunctionStatus should always return CKR_FUNCTION_NOT_PARALLEL */
    rv = (*ckpFunctions->C_GetFunctionStatus)(ckSessionHandle);
    if (ckAssertReturnValueOK(env, rv) != CK_ASSERT_OK) { return; }
}
#endif

#ifdef P11_ENABLE_C_CANCELFUNCTION
/*
 * Class:     sun_security_pkcs11_wrapper_PKCS11
 * Method:    C_CancelFunction
 * Signature: (J)V
 * Parametermapping:                    *PKCS11*
 * @param   jlong jSessionHandle        CK_SESSION_HANDLE hSession
 */
JNIEXPORT void JNICALL Java_sun_security_pkcs11_wrapper_PKCS11_C_1CancelFunction
    (JNIEnv *env, jobject obj, jlong jSessionHandle)
{
    CK_SESSION_HANDLE ckSessionHandle;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return; }

    ckSessionHandle = jLongToCKULong(jSessionHandle);

    /* C_GetFunctionStatus should always return CKR_FUNCTION_NOT_PARALLEL */
    rv = (*ckpFunctions->C_CancelFunction)(ckSessionHandle);
    if (ckAssertReturnValueOK(env, rv) != CK_ASSERT_OK) { return; }
}
#endif
