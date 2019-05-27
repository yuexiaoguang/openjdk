#include "jni_util.h"
#include "Disposer.h"

static jmethodID addRecordMID = NULL;
static jclass dispClass = NULL;

/*
 * Class:     sun_java2d_Disposer
 * Method:    initIDs
 * Signature: ()V
 */
JNIEXPORT void JNICALL
Java_sun_java2d_Disposer_initIDs(JNIEnv *env, jclass disposerClass)
{
    addRecordMID = (*env)->GetStaticMethodID(env, disposerClass, "addRecord",
                                             "(Ljava/lang/Object;JJ)V");
    if (addRecordMID != 0) {
        dispClass = (*env)->NewGlobalRef(env, disposerClass);
    }
}

JNIEXPORT void JNICALL
Disposer_AddRecord(JNIEnv *env, jobject obj, GeneralDisposeFunc disposer, jlong pData) {

    if (dispClass == NULL) {
        /* Needed to initialize the Disposer class as it may be not yet referenced */
        jclass clazz = (*env)->FindClass(env, "sun/java2d/Disposer");
        if ((*env)->ExceptionCheck(env)) {
            // If there's exception pending, we'll just return.
            return;
        }
    }

    (*env)->CallStaticVoidMethod(env, dispClass, addRecordMID,
                                 obj, ptr_to_jlong(disposer), pData);
}

/*
 * Class:     sun_java2d_DefaultDisposerRecord
 * Method:    invokeNativeDispose
 * Signature: (JJ)V
 */
JNIEXPORT void JNICALL
Java_sun_java2d_DefaultDisposerRecord_invokeNativeDispose(JNIEnv *env, jclass dispClass,
                                             jlong disposer, jlong pData)
{
    if (disposer != 0 && pData != 0) {
        GeneralDisposeFunc *disposeMethod = (GeneralDisposeFunc*)(jlong_to_ptr(disposer));
        disposeMethod(env, pData);
    }
}
