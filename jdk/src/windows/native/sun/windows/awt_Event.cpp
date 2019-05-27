#include "awt_Event.h"
#include "awt.h"

/************************************************************************
 * AwtEvent fields
 */

jfieldID AwtEvent::targetID;
jfieldID AwtEvent::xID;
jfieldID AwtEvent::yID;

/************************************************************************
 * AwtEvent native methods
 */

extern "C" {

JNIEXPORT void JNICALL
Java_java_awt_Event_initIDs(JNIEnv *env, jclass cls) {
    TRY;

    AwtEvent::targetID = env->GetFieldID(cls, "target", "Ljava/lang/Object;");
    AwtEvent::xID = env->GetFieldID(cls, "x", "I");
    AwtEvent::yID = env->GetFieldID(cls, "y", "I");

    DASSERT(AwtEvent::targetID != NULL);
    DASSERT(AwtEvent::xID != NULL);
    DASSERT(AwtEvent::yID != NULL);

    CATCH_BAD_ALLOC;
}

} /* extern "C" */
