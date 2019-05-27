#include "awt_Container.h"
#include "awt.h"

/************************************************************************
 * AwtContainer fields
 */

jfieldID AwtContainer::layoutMgrID;

/************************************************************************
 * AwtContainer native methods
 */

extern "C" {

JNIEXPORT void JNICALL
Java_java_awt_Container_initIDs(JNIEnv *env, jclass cls) {
    TRY;

    AwtContainer::layoutMgrID =
        env->GetFieldID(cls, "layoutMgr", "Ljava/awt/LayoutManager;");

    DASSERT(AwtContainer::layoutMgrID != NULL);

    CATCH_BAD_ALLOC;
}

} /* extern "C" */
