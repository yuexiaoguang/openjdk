#include "awt_KeyEvent.h"
#include "awt.h"

/************************************************************************
 * AwtKeyEvent fields
 */

jfieldID AwtKeyEvent::keyCodeID;
jfieldID AwtKeyEvent::keyCharID;
jfieldID AwtKeyEvent::rawCodeID;
jfieldID AwtKeyEvent::primaryLevelUnicodeID;
jfieldID AwtKeyEvent::scancodeID;
jfieldID AwtKeyEvent::extendedKeyCodeID;

/************************************************************************
 * AwtKeyEvent native methods
 */

extern "C" {

JNIEXPORT void JNICALL
Java_java_awt_event_KeyEvent_initIDs(JNIEnv *env, jclass cls) {
    TRY;

    AwtKeyEvent::keyCodeID = env->GetFieldID(cls, "keyCode", "I");
    AwtKeyEvent::keyCharID = env->GetFieldID(cls, "keyChar", "C");
    AwtKeyEvent::rawCodeID = env->GetFieldID(cls, "rawCode", "J");
    AwtKeyEvent::primaryLevelUnicodeID = env->GetFieldID(cls, "primaryLevelUnicode", "J");
    AwtKeyEvent::scancodeID = env->GetFieldID(cls, "scancode", "J");
    AwtKeyEvent::extendedKeyCodeID = env->GetFieldID(cls, "extendedKeyCode", "J");


    DASSERT(AwtKeyEvent::keyCodeID != NULL);
    DASSERT(AwtKeyEvent::keyCharID != NULL);
    DASSERT(AwtKeyEvent::rawCodeID != NULL);
    DASSERT(AwtKeyEvent::primaryLevelUnicodeID != NULL);
    DASSERT(AwtKeyEvent::scancodeID != NULL);
    DASSERT(AwtKeyEvent::extendedKeyCodeID != NULL);

    CATCH_BAD_ALLOC;
}

} /* extern "C" */
