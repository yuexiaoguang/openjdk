#include "awt.h"
#include "awt_Component.h"
#include "awt_Toolkit.h"
#include <java_awt_KeyboardFocusManager.h>
#include <jni.h>

static jobject getNativeFocusState(JNIEnv *env, void*(*ftn)()) {
    jobject gFocusState = (jobject)AwtToolkit::GetInstance().SyncCall(ftn);

    if (gFocusState != NULL) {
        jobject lFocusState = env->NewLocalRef(gFocusState);
        env->DeleteGlobalRef(gFocusState);
        return lFocusState;
    }
    return NULL;
}

extern "C" {

/*
 * Class:     java_awt_KeyboardFocusManager
 * Method:    initIDs
 * Signature: ()V
 */
JNIEXPORT void JNICALL
Java_java_awt_KeyboardFocusManager_initIDs
    (JNIEnv *env, jclass cls)
{
}

/*
 * Class:     sun_awt_windows_WKeyboardFocusManagerPeer
 * Method:    setNativeFocusOwner
 * Signature: (Lsun/awt/windows/WComponentPeer)
 */
JNIEXPORT void JNICALL
Java_sun_awt_windows_WKeyboardFocusManagerPeer_setNativeFocusOwner
    (JNIEnv *env, jclass cls, jobject compPeer)
{
    TRY;

    jobject peerGlobalRef = env->NewGlobalRef(compPeer);

    AwtToolkit::GetInstance().SyncCall(AwtComponent::SetNativeFocusOwner,
                                       (void*)peerGlobalRef);
    // peerGlobalRef is deleted in SetNativeFocusOwner

    CATCH_BAD_ALLOC;
}

/*
 * Class:     sun_awt_windows_WKeyboardFocusManagerPeer
 * Method:    getNativeFocusOwner
 * Signature: (Lsun/awt/windows/WComponentPeer)
 */
JNIEXPORT jobject JNICALL
Java_sun_awt_windows_WKeyboardFocusManagerPeer_getNativeFocusOwner
    (JNIEnv *env, jclass cls)
{
    TRY;

    return getNativeFocusState(env, AwtComponent::GetNativeFocusOwner);

    CATCH_BAD_ALLOC_RET(NULL);
}

/*
 * Class:     sun_awt_windows_WKeyboardFocusManagerPeer
 * Method:    getNativeFocusedWindow
 * Signature: ()Ljava/awt/Window;
 */
JNIEXPORT jobject JNICALL
Java_sun_awt_windows_WKeyboardFocusManagerPeer_getNativeFocusedWindow
    (JNIEnv *env, jclass cls)
{
    TRY;

    return getNativeFocusState(env, AwtComponent::GetNativeFocusedWindow);

    CATCH_BAD_ALLOC_RET(NULL);
}
}
