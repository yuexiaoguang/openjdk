#include <jawt.h>

#include "awt_DrawingSurface.h"

/*
 * Get the AWT native structure.  This function returns JNI_FALSE if
 * an error occurs.
 */
JNIEXPORT jboolean JNICALL JAWT_GetAWT(JNIEnv* env, JAWT* awt)
{
#if defined(JAVASE_EMBEDDED) && defined(HEADLESS)
    /* there are no AWT libs available at all */
    return JNI_FALSE;
#else
    if (awt == NULL) {
        return JNI_FALSE;
    }

    if (awt->version != JAWT_VERSION_1_3
        && awt->version != JAWT_VERSION_1_4
        && awt->version != JAWT_VERSION_1_7) {
        return JNI_FALSE;
    }

    awt->GetDrawingSurface = awt_GetDrawingSurface;
    awt->FreeDrawingSurface = awt_FreeDrawingSurface;
    if (awt->version >= JAWT_VERSION_1_4) {
        awt->Lock = awt_Lock;
        awt->Unlock = awt_Unlock;
        awt->GetComponent = awt_GetComponent;
    }

    return JNI_TRUE;
#endif
}
