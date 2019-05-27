#include "awt_Panel.h"
#include "awt_Toolkit.h"
#include "awt_Component.h"
#include "awt.h"

/************************************************************************
 * AwtPanel fields
 */

jfieldID AwtPanel::insets_ID;

/************************************************************************
 * AwtPanel native methods
 */

extern "C" {

JNIEXPORT void JNICALL
Java_sun_awt_windows_WPanelPeer_initIDs(JNIEnv *env, jclass cls) {

    TRY;

    AwtPanel::insets_ID = env->GetFieldID(cls, "insets_", "Ljava/awt/Insets;");

    DASSERT(AwtPanel::insets_ID != NULL);

    CATCH_BAD_ALLOC;
}

} /* extern "C" */
