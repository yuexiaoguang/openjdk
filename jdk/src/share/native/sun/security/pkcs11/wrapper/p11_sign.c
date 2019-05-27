#include "pkcs11wrapper.h"

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include "jlong.h"

#include "sun_security_pkcs11_wrapper_PKCS11.h"

#ifdef P11_ENABLE_C_SIGNINIT
/*
 * Class:     sun_security_pkcs11_wrapper_PKCS11
 * Method:    C_SignInit
 * Signature: (JLsun/security/pkcs11/wrapper/CK_MECHANISM;J)V
 * Parametermapping:                    *PKCS11*
 * @param   jlong jSessionHandle        CK_SESSION_HANDLE hSession
 * @param   jobject jMechanism          CK_MECHANISM_PTR pMechanism
 * @return  jlong jKeyHandle            CK_OBJECT_HANDLE hKey
 */
JNIEXPORT void JNICALL Java_sun_security_pkcs11_wrapper_PKCS11_C_1SignInit
    (JNIEnv *env, jobject obj, jlong jSessionHandle, jobject jMechanism, jlong jKeyHandle)
{
    CK_SESSION_HANDLE ckSessionHandle;
    CK_MECHANISM ckMechanism;
    CK_OBJECT_HANDLE ckKeyHandle;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return; }

    ckSessionHandle = jLongToCKULong(jSessionHandle);
    jMechanismToCKMechanism(env, jMechanism, &ckMechanism);
    if ((*env)->ExceptionCheck(env)) { return; }
    ckKeyHandle = jLongToCKULong(jKeyHandle);

    rv = (*ckpFunctions->C_SignInit)(ckSessionHandle, &ckMechanism, ckKeyHandle);

    if (ckMechanism.pParameter != NULL_PTR) {
        free(ckMechanism.pParameter);
    }

    if (ckAssertReturnValueOK(env, rv) != CK_ASSERT_OK) { return; }
}
#endif

#ifdef P11_ENABLE_C_SIGN
/*
 * Class:     sun_security_pkcs11_wrapper_PKCS11
 * Method:    C_Sign
 * Signature: (J[B)[B
 * Parametermapping:                    *PKCS11*
 * @param   jlong jSessionHandle        CK_SESSION_HANDLE hSession
 * @param   jbyteArray jData            CK_BYTE_PTR pData
 *                                      CK_ULONG ulDataLen
 * @return  jbyteArray jSignature       CK_BYTE_PTR pSignature
 *                                      CK_ULONG_PTR pulSignatureLen
 */
JNIEXPORT jbyteArray JNICALL Java_sun_security_pkcs11_wrapper_PKCS11_C_1Sign
    (JNIEnv *env, jobject obj, jlong jSessionHandle, jbyteArray jData)
{
    CK_SESSION_HANDLE ckSessionHandle;
    CK_BYTE_PTR ckpData = NULL_PTR;
    CK_BYTE_PTR ckpSignature;
    CK_ULONG ckDataLength;
    CK_ULONG ckSignatureLength = 0;
    jbyteArray jSignature = NULL;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return NULL; }

    ckSessionHandle = jLongToCKULong(jSessionHandle);
    jByteArrayToCKByteArray(env, jData, &ckpData, &ckDataLength);
    if ((*env)->ExceptionCheck(env)) { return NULL; }

    /* START standard code */

    /* first determine the length of the signature */
    rv = (*ckpFunctions->C_Sign)(ckSessionHandle, ckpData, ckDataLength, NULL_PTR, &ckSignatureLength);
    if (ckAssertReturnValueOK(env, rv) != CK_ASSERT_OK) {
        free(ckpData);
        return NULL;
    }

    ckpSignature = (CK_BYTE_PTR) malloc(ckSignatureLength * sizeof(CK_BYTE));
    if (ckpSignature == NULL) {
        free(ckpData);
        throwOutOfMemoryError(env, 0);
        return NULL;
    }

    /* now get the signature */
    rv = (*ckpFunctions->C_Sign)(ckSessionHandle, ckpData, ckDataLength, ckpSignature, &ckSignatureLength);
 /* END standard code */


    /* START workaround code for operation abort bug in pkcs#11 of Datakey and iButton */
/*
    ckpSignature = (CK_BYTE_PTR) malloc(256 * sizeof(CK_BYTE));
    if (ckpSignature == NULL) {
        free(ckpData);
        throwOutOfMemoryError(env, 0);
        return NULL;
    }
    rv = (*ckpFunctions->C_Sign)(ckSessionHandle, ckpData, ckDataLength, ckpSignature, &ckSignatureLength);

    if (rv == CKR_BUFFER_TOO_SMALL) {
        free(ckpSignature);
        ckpSignature = (CK_BYTE_PTR) malloc(ckSignatureLength * sizeof(CK_BYTE));
        if (ckpSignature == NULL) {
            free(ckpData);
            throwOutOfMemoryError(env, 0);
            return NULL;
        }
        rv = (*ckpFunctions->C_Sign)(ckSessionHandle, ckpData, ckDataLength, ckpSignature, &ckSignatureLength);
    }
 */
    /* END workaround code */
    if (ckAssertReturnValueOK(env, rv) == CK_ASSERT_OK) {
        jSignature = ckByteArrayToJByteArray(env, ckpSignature, ckSignatureLength);
    }
    free(ckpData);
    free(ckpSignature);

    return jSignature ;
}
#endif

#ifdef P11_ENABLE_C_SIGNUPDATE
/*
 * Class:     sun_security_pkcs11_wrapper_PKCS11
 * Method:    C_SignUpdate
 * Signature: (J[BII)V
 * Parametermapping:                    *PKCS11*
 * @param   jlong jSessionHandle        CK_SESSION_HANDLE hSession
 * @param   jbyteArray jPart            CK_BYTE_PTR pPart
 *                                      CK_ULONG ulPartLen
 */
JNIEXPORT void JNICALL Java_sun_security_pkcs11_wrapper_PKCS11_C_1SignUpdate
  (JNIEnv *env, jobject obj, jlong jSessionHandle, jlong directIn, jbyteArray jIn, jint jInOfs, jint jInLen)
{
    CK_SESSION_HANDLE ckSessionHandle;
    CK_RV rv;
    CK_BYTE_PTR bufP;
    CK_BYTE BUF[MAX_STACK_BUFFER_LEN];
    jsize bufLen;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return; }

    ckSessionHandle = jLongToCKULong(jSessionHandle);

    if (directIn != 0) {
        rv = (*ckpFunctions->C_SignUpdate)(ckSessionHandle, (CK_BYTE_PTR) jlong_to_ptr(directIn), jInLen);
        ckAssertReturnValueOK(env, rv);
        return;
    }

    if (jInLen <= MAX_STACK_BUFFER_LEN) {
        bufLen = MAX_STACK_BUFFER_LEN;
        bufP = BUF;
    } else {
        bufLen = min(MAX_HEAP_BUFFER_LEN, jInLen);
        bufP = (CK_BYTE_PTR) malloc((size_t)bufLen);
        if (bufP == NULL) {
            throwOutOfMemoryError(env, 0);
            return;
        }
    }

    while (jInLen > 0) {
        jsize chunkLen = min(bufLen, jInLen);
        (*env)->GetByteArrayRegion(env, jIn, jInOfs, chunkLen, (jbyte *)bufP);
        if ((*env)->ExceptionCheck(env)) {
            if (bufP != BUF) { free(bufP); }
            return;
        }
        rv = (*ckpFunctions->C_SignUpdate)(ckSessionHandle, bufP, chunkLen);
        if (ckAssertReturnValueOK(env, rv) != CK_ASSERT_OK) {
            if (bufP != BUF) {
                free(bufP);
            }
            return;
        }
        jInOfs += chunkLen;
        jInLen -= chunkLen;
    }

    if (bufP != BUF) { free(bufP); }
}
#endif

#ifdef P11_ENABLE_C_SIGNFINAL
/*
 * Class:     sun_security_pkcs11_wrapper_PKCS11
 * Method:    C_SignFinal
 * Signature: (J)[B
 * Parametermapping:                    *PKCS11*
 * @param   jlong jSessionHandle        CK_SESSION_HANDLE hSession
 * @return  jbyteArray jSignature       CK_BYTE_PTR pSignature
 *                                      CK_ULONG_PTR pulSignatureLen
 */
JNIEXPORT jbyteArray JNICALL Java_sun_security_pkcs11_wrapper_PKCS11_C_1SignFinal
    (JNIEnv *env, jobject obj, jlong jSessionHandle, jint jExpectedLength)
{
    CK_SESSION_HANDLE ckSessionHandle;
    jbyteArray jSignature = NULL;
    CK_RV rv;
    CK_BYTE BUF[MAX_STACK_BUFFER_LEN];
    CK_BYTE_PTR bufP = BUF;
    CK_ULONG ckSignatureLength = MAX_STACK_BUFFER_LEN;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return NULL; }

    ckSessionHandle = jLongToCKULong(jSessionHandle);

    if ((jExpectedLength > 0) && ((CK_ULONG)jExpectedLength < ckSignatureLength)) {
        ckSignatureLength = jExpectedLength;
    }

    rv = (*ckpFunctions->C_SignFinal)(ckSessionHandle, bufP, &ckSignatureLength);
    if (rv == CKR_BUFFER_TOO_SMALL) {
        bufP = (CK_BYTE_PTR) malloc(ckSignatureLength);
        if (bufP == NULL) {
            throwOutOfMemoryError(env, 0);
            return NULL;
        }
        rv = (*ckpFunctions->C_SignFinal)(ckSessionHandle, bufP, &ckSignatureLength);
    }
    if (ckAssertReturnValueOK(env, rv) == CK_ASSERT_OK) {
        jSignature = ckByteArrayToJByteArray(env, bufP, ckSignatureLength);
    }

    if (bufP != BUF) { free(bufP); }

    return jSignature;
}
#endif

#ifdef P11_ENABLE_C_SIGNRECOVERINIT
/*
 * Class:     sun_security_pkcs11_wrapper_PKCS11
 * Method:    C_SignRecoverInit
 * Signature: (JLsun/security/pkcs11/wrapper/CK_MECHANISM;J)V
 * Parametermapping:                    *PKCS11*
 * @param   jlong jSessionHandle        CK_SESSION_HANDLE hSession
 * @param   jobject jMechanism          CK_MECHANISM_PTR pMechanism
 * @return  jlong jKeyHandle            CK_OBJECT_HANDLE hKey
 */
JNIEXPORT void JNICALL Java_sun_security_pkcs11_wrapper_PKCS11_C_1SignRecoverInit
    (JNIEnv *env, jobject obj, jlong jSessionHandle, jobject jMechanism, jlong jKeyHandle)
{
    CK_SESSION_HANDLE ckSessionHandle;
    CK_MECHANISM ckMechanism;
    CK_OBJECT_HANDLE ckKeyHandle;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return; }

    ckSessionHandle = jLongToCKULong(jSessionHandle);
    jMechanismToCKMechanism(env, jMechanism, &ckMechanism);
    if ((*env)->ExceptionCheck(env)) { return; }

    ckKeyHandle = jLongToCKULong(jKeyHandle);

    rv = (*ckpFunctions->C_SignRecoverInit)(ckSessionHandle, &ckMechanism, ckKeyHandle);

    if (ckMechanism.pParameter != NULL_PTR) {
        free(ckMechanism.pParameter);
    }

    if(ckAssertReturnValueOK(env, rv) != CK_ASSERT_OK) { return; }
}
#endif

#ifdef P11_ENABLE_C_SIGNRECOVER
/*
 * Class:     sun_security_pkcs11_wrapper_PKCS11
 * Method:    C_SignRecover
 * Signature: (J[BII[BII)I
 * Parametermapping:                    *PKCS11*
 * @param   jlong jSessionHandle        CK_SESSION_HANDLE hSession
 * @param   jbyteArray jData            CK_BYTE_PTR pData
 *                                      CK_ULONG ulDataLen
 * @return  jbyteArray jSignature       CK_BYTE_PTR pSignature
 *                                      CK_ULONG_PTR pulSignatureLen
 */
JNIEXPORT jint JNICALL Java_sun_security_pkcs11_wrapper_PKCS11_C_1SignRecover
  (JNIEnv *env, jobject obj, jlong jSessionHandle, jbyteArray jIn, jint jInOfs, jint jInLen, jbyteArray jOut, jint jOutOfs, jint jOutLen)
{
    CK_SESSION_HANDLE ckSessionHandle;
    CK_RV rv;
    CK_BYTE INBUF[MAX_STACK_BUFFER_LEN];
    CK_BYTE OUTBUF[MAX_STACK_BUFFER_LEN];
    CK_BYTE_PTR inBufP;
    CK_BYTE_PTR outBufP = OUTBUF;
    CK_ULONG ckSignatureLength = MAX_STACK_BUFFER_LEN;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return 0; }

    ckSessionHandle = jLongToCKULong(jSessionHandle);

    if (jInLen <= MAX_STACK_BUFFER_LEN) {
        inBufP = INBUF;
    } else {
        inBufP = (CK_BYTE_PTR) malloc((size_t)jInLen);
        if (inBufP == NULL) {
            throwOutOfMemoryError(env, 0);
            return 0;
        }
    }

    (*env)->GetByteArrayRegion(env, jIn, jInOfs, jInLen, (jbyte *)inBufP);
    if ((*env)->ExceptionCheck(env)) {
        if (inBufP != INBUF) { free(inBufP); }
        return 0;
    }
    rv = (*ckpFunctions->C_SignRecover)(ckSessionHandle, inBufP, jInLen, outBufP, &ckSignatureLength);
    /* re-alloc larger buffer if it fits into our Java buffer */
    if ((rv == CKR_BUFFER_TOO_SMALL) && (ckSignatureLength <= jIntToCKULong(jOutLen))) {
        outBufP = (CK_BYTE_PTR) malloc(ckSignatureLength);
        if (outBufP == NULL) {
            if (inBufP != INBUF) {
                free(inBufP);
            }
            throwOutOfMemoryError(env, 0);
            return 0;
        }
        rv = (*ckpFunctions->C_SignRecover)(ckSessionHandle, inBufP, jInLen, outBufP, &ckSignatureLength);
    }
    if (ckAssertReturnValueOK(env, rv) == CK_ASSERT_OK) {
        (*env)->SetByteArrayRegion(env, jOut, jOutOfs, ckSignatureLength, (jbyte *)outBufP);
    }

    if (inBufP != INBUF) { free(inBufP); }
    if (outBufP != OUTBUF) { free(outBufP); }

    return ckSignatureLength;
}
#endif

#ifdef P11_ENABLE_C_VERIFYINIT
/*
 * Class:     sun_security_pkcs11_wrapper_PKCS11
 * Method:    C_VerifyInit
 * Signature: (JLsun/security/pkcs11/wrapper/CK_MECHANISM;J)V
 * Parametermapping:                    *PKCS11*
 * @param   jlong jSessionHandle        CK_SESSION_HANDLE hSession
 * @param   jobject jMechanism          CK_MECHANISM_PTR pMechanism
 * @return  jlong jKeyHandle            CK_OBJECT_HANDLE hKey
 */
JNIEXPORT void JNICALL Java_sun_security_pkcs11_wrapper_PKCS11_C_1VerifyInit
    (JNIEnv *env, jobject obj, jlong jSessionHandle, jobject jMechanism, jlong jKeyHandle)
{
    CK_SESSION_HANDLE ckSessionHandle;
    CK_MECHANISM ckMechanism;
    CK_OBJECT_HANDLE ckKeyHandle;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return; }

    ckSessionHandle = jLongToCKULong(jSessionHandle);
    jMechanismToCKMechanism(env, jMechanism, &ckMechanism);
    if ((*env)->ExceptionCheck(env)) { return; }

    ckKeyHandle = jLongToCKULong(jKeyHandle);

    rv = (*ckpFunctions->C_VerifyInit)(ckSessionHandle, &ckMechanism, ckKeyHandle);

    if(ckMechanism.pParameter != NULL_PTR) {
        free(ckMechanism.pParameter);
    }

    if (ckAssertReturnValueOK(env, rv) != CK_ASSERT_OK) { return; }
}
#endif

#ifdef P11_ENABLE_C_VERIFY
/*
 * Class:     sun_security_pkcs11_wrapper_PKCS11
 * Method:    C_Verify
 * Signature: (J[B[B)V
 * Parametermapping:                    *PKCS11*
 * @param   jlong jSessionHandle        CK_SESSION_HANDLE hSession
 * @param   jbyteArray jData            CK_BYTE_PTR pData
 *                                      CK_ULONG ulDataLen
 * @param   jbyteArray jSignature       CK_BYTE_PTR pSignature
 *                                      CK_ULONG_PTR pulSignatureLen
 */
JNIEXPORT void JNICALL Java_sun_security_pkcs11_wrapper_PKCS11_C_1Verify
    (JNIEnv *env, jobject obj, jlong jSessionHandle, jbyteArray jData, jbyteArray jSignature)
{
    CK_SESSION_HANDLE ckSessionHandle;
    CK_BYTE_PTR ckpData = NULL_PTR;
    CK_BYTE_PTR ckpSignature = NULL_PTR;
    CK_ULONG ckDataLength;
    CK_ULONG ckSignatureLength;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return; }

    ckSessionHandle = jLongToCKULong(jSessionHandle);
    jByteArrayToCKByteArray(env, jData, &ckpData, &ckDataLength);
    if ((*env)->ExceptionCheck(env)) { return; }

    jByteArrayToCKByteArray(env, jSignature, &ckpSignature, &ckSignatureLength);
    if ((*env)->ExceptionCheck(env)) {
        free(ckpData);
        return;
    }

    /* verify the signature */
    rv = (*ckpFunctions->C_Verify)(ckSessionHandle, ckpData, ckDataLength, ckpSignature, ckSignatureLength);

    free(ckpData);
    free(ckpSignature);

    if (ckAssertReturnValueOK(env, rv) != CK_ASSERT_OK) { return; }
}
#endif

#ifdef P11_ENABLE_C_VERIFYUPDATE
/*
 * Class:     sun_security_pkcs11_wrapper_PKCS11
 * Method:    C_VerifyUpdate
 * Signature: (J[BII)V
 * Parametermapping:                    *PKCS11*
 * @param   jlong jSessionHandle        CK_SESSION_HANDLE hSession
 * @param   jbyteArray jPart            CK_BYTE_PTR pPart
 *                                      CK_ULONG ulPartLen
 */
JNIEXPORT void JNICALL Java_sun_security_pkcs11_wrapper_PKCS11_C_1VerifyUpdate
  (JNIEnv *env, jobject obj, jlong jSessionHandle, jlong directIn, jbyteArray jIn, jint jInOfs, jint jInLen)
{
    CK_SESSION_HANDLE ckSessionHandle;
    CK_RV rv;
    CK_BYTE_PTR bufP;
    CK_BYTE BUF[MAX_STACK_BUFFER_LEN];
    jsize bufLen;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return; }

    ckSessionHandle = jLongToCKULong(jSessionHandle);

    if (directIn != 0) {
        rv = (*ckpFunctions->C_VerifyUpdate)(ckSessionHandle, (CK_BYTE_PTR)jlong_to_ptr(directIn), jInLen);
        ckAssertReturnValueOK(env, rv);
        return;
    }

    if (jInLen <= MAX_STACK_BUFFER_LEN) {
        bufLen = MAX_STACK_BUFFER_LEN;
        bufP = BUF;
    } else {
        bufLen = min(MAX_HEAP_BUFFER_LEN, jInLen);
        bufP = (CK_BYTE_PTR) malloc((size_t)bufLen);
        if (bufP == NULL) {
            throwOutOfMemoryError(env, 0);
            return;
        }
    }

    while (jInLen > 0) {
        jsize chunkLen = min(bufLen, jInLen);
        (*env)->GetByteArrayRegion(env, jIn, jInOfs, chunkLen, (jbyte *)bufP);
        if ((*env)->ExceptionCheck(env)) {
            if (bufP != BUF) { free(bufP); }
            return;
        }

        rv = (*ckpFunctions->C_VerifyUpdate)(ckSessionHandle, bufP, chunkLen);
        if (ckAssertReturnValueOK(env, rv) != CK_ASSERT_OK) {
            if (bufP != BUF) { free(bufP); }
            return;
        }
        jInOfs += chunkLen;
        jInLen -= chunkLen;
    }

    if (bufP != BUF) { free(bufP); }
}
#endif

#ifdef P11_ENABLE_C_VERIFYFINAL
/*
 * Class:     sun_security_pkcs11_wrapper_PKCS11
 * Method:    C_VerifyFinal
 * Signature: (J[B)V
 * Parametermapping:                    *PKCS11*
 * @param   jlong jSessionHandle        CK_SESSION_HANDLE hSession
 * @param   jbyteArray jSignature       CK_BYTE_PTR pSignature
 *                                      CK_ULONG ulSignatureLen
 */
JNIEXPORT void JNICALL Java_sun_security_pkcs11_wrapper_PKCS11_C_1VerifyFinal
    (JNIEnv *env, jobject obj, jlong jSessionHandle, jbyteArray jSignature)
{
    CK_SESSION_HANDLE ckSessionHandle;
    CK_BYTE_PTR ckpSignature = NULL_PTR;
    CK_ULONG ckSignatureLength;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return; }

    ckSessionHandle = jLongToCKULong(jSessionHandle);
    jByteArrayToCKByteArray(env, jSignature, &ckpSignature, &ckSignatureLength);
    if ((*env)->ExceptionCheck(env)) { return; }

    /* verify the signature */
    rv = (*ckpFunctions->C_VerifyFinal)(ckSessionHandle, ckpSignature, ckSignatureLength);

    free(ckpSignature);

    if (ckAssertReturnValueOK(env, rv) != CK_ASSERT_OK) { return; }
}
#endif

#ifdef P11_ENABLE_C_VERIFYRECOVERINIT
/*
 * Class:     sun_security_pkcs11_wrapper_PKCS11
 * Method:    C_VerifyRecoverInit
 * Signature: (JLsun/security/pkcs11/wrapper/CK_MECHANISM;J)V
 * Parametermapping:                    *PKCS11*
 * @param   jlong jSessionHandle        CK_SESSION_HANDLE hSession
 * @param   jobject jMechanism          CK_MECHANISM_PTR pMechanism
 * @return  jlong jKeyHandle            CK_OBJECT_HANDLE hKey
 */
JNIEXPORT void JNICALL Java_sun_security_pkcs11_wrapper_PKCS11_C_1VerifyRecoverInit
    (JNIEnv *env, jobject obj, jlong jSessionHandle, jobject jMechanism, jlong jKeyHandle)
{
    CK_SESSION_HANDLE ckSessionHandle;
    CK_MECHANISM ckMechanism;
    CK_OBJECT_HANDLE ckKeyHandle;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return; }

    ckSessionHandle = jLongToCKULong(jSessionHandle);
    jMechanismToCKMechanism(env, jMechanism, &ckMechanism);
    if ((*env)->ExceptionCheck(env)) { return; }

    ckKeyHandle = jLongToCKULong(jKeyHandle);

    rv = (*ckpFunctions->C_VerifyRecoverInit)(ckSessionHandle, &ckMechanism, ckKeyHandle);

    if (ckMechanism.pParameter != NULL_PTR) {
        free(ckMechanism.pParameter);
    }

    if (ckAssertReturnValueOK(env, rv) != CK_ASSERT_OK) { return; }
}
#endif

#ifdef P11_ENABLE_C_VERIFYRECOVER
/*
 * Class:     sun_security_pkcs11_wrapper_PKCS11
 * Method:    C_VerifyRecover
 * Signature: (J[BII[BII)I
 * Parametermapping:                    *PKCS11*
 * @param   jlong jSessionHandle        CK_SESSION_HANDLE hSession
 * @param   jbyteArray jSignature       CK_BYTE_PTR pSignature
 *                                      CK_ULONG ulSignatureLen
 * @return  jbyteArray jData            CK_BYTE_PTR pData
 *                                      CK_ULONG_PTR pulDataLen
 */
JNIEXPORT jint JNICALL Java_sun_security_pkcs11_wrapper_PKCS11_C_1VerifyRecover
  (JNIEnv *env, jobject obj, jlong jSessionHandle, jbyteArray jIn, jint jInOfs, jint jInLen, jbyteArray jOut, jint jOutOfs, jint jOutLen)
{
    CK_SESSION_HANDLE ckSessionHandle;
    CK_RV rv;
    CK_BYTE INBUF[MAX_STACK_BUFFER_LEN];
    CK_BYTE OUTBUF[MAX_STACK_BUFFER_LEN];
    CK_BYTE_PTR inBufP;
    CK_BYTE_PTR outBufP = OUTBUF;
    CK_ULONG ckDataLength = MAX_STACK_BUFFER_LEN;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return 0; }

    ckSessionHandle = jLongToCKULong(jSessionHandle);

    if (jInLen <= MAX_STACK_BUFFER_LEN) {
        inBufP = INBUF;
    } else {
        inBufP = (CK_BYTE_PTR) malloc((size_t)jInLen);
        if (inBufP == NULL) {
            throwOutOfMemoryError(env, 0);
            return 0;
        }
    }

    (*env)->GetByteArrayRegion(env, jIn, jInOfs, jInLen, (jbyte *)inBufP);
    if ((*env)->ExceptionCheck(env)) {
        if (inBufP != INBUF) { free(inBufP); }
        return 0;
    }

    rv = (*ckpFunctions->C_VerifyRecover)(ckSessionHandle, inBufP, jInLen, outBufP, &ckDataLength);

    /* re-alloc larger buffer if it fits into our Java buffer */
    if ((rv == CKR_BUFFER_TOO_SMALL) && (ckDataLength <= jIntToCKULong(jOutLen))) {
        outBufP = (CK_BYTE_PTR) malloc(ckDataLength);
        if (outBufP == NULL) {
            if (inBufP != INBUF) { free(inBufP); }
            throwOutOfMemoryError(env, 0);
            return 0;
        }
        rv = (*ckpFunctions->C_VerifyRecover)(ckSessionHandle, inBufP, jInLen, outBufP, &ckDataLength);
    }
    if (ckAssertReturnValueOK(env, rv) == CK_ASSERT_OK) {
        (*env)->SetByteArrayRegion(env, jOut, jOutOfs, ckDataLength, (jbyte *)outBufP);
    }

    if (inBufP != INBUF) { free(inBufP); }
    if (outBufP != OUTBUF) { free(outBufP); }

    return ckDataLength;
}
#endif
