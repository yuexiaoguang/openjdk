#ifdef HEADLESS
    #error This file should not be included in headless library
#endif

#include "awt_p.h"
#include "color.h"
#include <X11/IntrinsicP.h>
#include <X11/Xatom.h>
#include <X11/Xmd.h>
#include <X11/Xutil.h>
#include <X11/Xproto.h>
#include <jni.h>
#include <jni_util.h>
#include <sys/time.h>


#include "java_awt_event_MouseWheelEvent.h"

/*
 * Called by "ToolkitErrorHandler" function in "XlibWrapper.c" file.
 */
XErrorHandler current_native_xerror_handler = NULL;

extern jint getModifiers(uint32_t state, jint button, jint keyCode);
extern jint getButton(uint32_t button);

static Atom OLDecorDelAtom = 0;
static Atom MWMHints = 0;
static Atom DTWMHints = 0;
static Atom decor_list[9];

#ifndef MAX
#define MAX(a,b) ((a) > (b) ? (a) : (b))
#endif

#ifndef MIN
#define MIN(a,b) ((a) < (b) ? (a) : (b))
#endif

void
awtJNI_ThreadYield(JNIEnv *env) {

    static jclass threadClass = NULL;
    static jmethodID yieldMethodID = NULL;

    /* Initialize our java identifiers once. Checking before locking
     * is a huge performance win.
     */
    if (threadClass == NULL) {
        // should enter a monitor here...
        Boolean err = FALSE;
        if (threadClass == NULL) {
            jclass tc = (*env)->FindClass(env, "java/lang/Thread");
            threadClass = (*env)->NewGlobalRef(env, tc);
            (*env)->DeleteLocalRef(env, tc);
            if (threadClass != NULL) {
                yieldMethodID = (*env)->GetStaticMethodID(env,
                                              threadClass,
                                              "yield",
                                              "()V"
                                                );
            }
        }
        if (yieldMethodID == NULL) {
            threadClass = NULL;
            err = TRUE;
        }
        if (err) {
            return;
        }
    } /* threadClass == NULL*/

    (*env)->CallStaticVoidMethod(env, threadClass, yieldMethodID);
    DASSERT(!((*env)->ExceptionOccurred(env)));
} /* awtJNI_ThreadYield() */
