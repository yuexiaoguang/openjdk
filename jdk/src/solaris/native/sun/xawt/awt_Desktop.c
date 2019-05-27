#include "gtk2_interface.h"
#include "gnome_interface.h"

static gboolean gtk_has_been_loaded = FALSE;
static gboolean gnome_has_been_loaded = FALSE;

/*
 * Class:     sun_awt_X11_XDesktopPeer
 * Method:    init
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_sun_awt_X11_XDesktopPeer_init
  (JNIEnv *env, jclass cls)
{

    if (gtk_has_been_loaded || gnome_has_been_loaded) {
        return JNI_TRUE;
    }

    if (gtk2_load(env) && gtk2_show_uri_load(env)) {
        gtk_has_been_loaded = TRUE;
        return JNI_TRUE;
    } else if (gnome_load()) {
        gnome_has_been_loaded = TRUE;
        return JNI_TRUE;
    }

    return JNI_FALSE;
}

/*
 * Class:     sun_awt_X11_XDesktopPeer
 * Method:    gnome_url_show
 * Signature: (Ljava/lang/[B;)Z
 */
JNIEXPORT jboolean JNICALL Java_sun_awt_X11_XDesktopPeer_gnome_1url_1show
  (JNIEnv *env, jobject obj, jbyteArray url_j)
{
    gboolean success = FALSE;
    const gchar* url_c;

    url_c = (char*)(*env)->GetByteArrayElements(env, url_j, NULL);

    if (gtk_has_been_loaded) {
        fp_gdk_threads_enter();
        success = fp_gtk_show_uri(NULL, url_c, GDK_CURRENT_TIME, NULL);
        fp_gdk_threads_leave();
    } else if (gnome_has_been_loaded) {
        success = (*gnome_url_show)(url_c, NULL);
    }

    (*env)->ReleaseByteArrayElements(env, url_j, (signed char*)url_c, 0);

    return success ? JNI_TRUE : JNI_FALSE;
}
