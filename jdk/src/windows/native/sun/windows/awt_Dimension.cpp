#include "awt_Dimension.h"
#include "awt.h"

/************************************************************************
 * AwtDimension fields
 */

jfieldID AwtDimension::widthID;
jfieldID AwtDimension::heightID;

/************************************************************************
 * AwtDimension native methods
 */

extern "C" {

JNIEXPORT void JNICALL
Java_java_awt_Dimension_initIDs(JNIEnv *env, jclass cls) {
    TRY;

    AwtDimension::widthID = env->GetFieldID(cls, "width", "I");
    AwtDimension::heightID = env->GetFieldID(cls, "height", "I");

    DASSERT(AwtDimension::widthID != NULL);
    DASSERT(AwtDimension::heightID != NULL);

    CATCH_BAD_ALLOC;
}

} /* extern "C" */
