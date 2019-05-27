#include "awt_Insets.h"
#include "awt.h"

/************************************************************************
 * AwtInsets fields
 */

jfieldID AwtInsets::leftID;
jfieldID AwtInsets::rightID;
jfieldID AwtInsets::topID;
jfieldID AwtInsets::bottomID;

/************************************************************************
 * AwtInsets native methods
 */

extern "C" {

JNIEXPORT void JNICALL
Java_java_awt_Insets_initIDs(JNIEnv *env, jclass cls) {
    TRY;

    AwtInsets::leftID = env->GetFieldID(cls, "left", "I");
    AwtInsets::rightID = env->GetFieldID(cls, "right", "I");
    AwtInsets::topID = env->GetFieldID(cls, "top", "I");
    AwtInsets::bottomID = env->GetFieldID(cls, "bottom", "I");

    DASSERT(AwtInsets::leftID != NULL);
    DASSERT(AwtInsets::rightID != NULL);
    DASSERT(AwtInsets::topID != NULL);
    DASSERT(AwtInsets::bottomID != NULL);

    CATCH_BAD_ALLOC;
}

} /* extern "C" */
