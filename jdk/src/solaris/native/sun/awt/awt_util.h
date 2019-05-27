#ifndef _AWT_UTIL_H_
#define _AWT_UTIL_H_

#ifndef HEADLESS
#include "gdefs.h"

#define WITH_XERROR_HANDLER(f) do {             \
    XSync(awt_display, False);                  \
    current_native_xerror_handler = (f);        \
} while (0)

#define RESTORE_XERROR_HANDLER do {             \
    XSync(awt_display, False);                  \
    current_native_xerror_handler = NULL;       \
} while (0)

#define EXEC_WITH_XERROR_HANDLER(f, code) do {  \
    WITH_XERROR_HANDLER(f);                     \
    do {                                        \
        code;                                   \
    } while (0);                                \
    RESTORE_XERROR_HANDLER;                     \
} while (0)

/*
 * Called by "ToolkitErrorHandler" function in "XlibWrapper.c" file.
 */
extern XErrorHandler current_native_xerror_handler;

#endif /* !HEADLESS */

#ifndef INTERSECTS
#define INTERSECTS(r1_x1,r1_x2,r1_y1,r1_y2,r2_x1,r2_x2,r2_y1,r2_y2) \
!((r2_x2 <= r1_x1) ||\
  (r2_y2 <= r1_y1) ||\
  (r2_x1 >= r1_x2) ||\
  (r2_y1 >= r1_y2))
#endif

#ifndef MIN
#define MIN(a,b) ((a) < (b) ? (a) : (b))
#endif
#ifndef MAX
#define MAX(a,b) ((a) > (b) ? (a) : (b))
#endif

struct DPos {
    int32_t x;
    int32_t y;
    int32_t mapped;
    void *data;
    void *peer;
    int32_t echoC;
};

extern void awtJNI_ThreadYield(JNIEnv *env);

/*
 * Functions for accessing fields by name and signature
 */

JNIEXPORT jobject JNICALL
JNU_GetObjectField(JNIEnv *env, jobject self, const char *name,
                   const char *sig);

JNIEXPORT jboolean JNICALL
JNU_SetObjectField(JNIEnv *env, jobject self, const char *name,
                   const char *sig, jobject val);

JNIEXPORT jlong JNICALL
JNU_GetLongField(JNIEnv *env, jobject self, const char *name);

JNIEXPORT jint JNICALL
JNU_GetIntField(JNIEnv *env, jobject self, const char *name);

JNIEXPORT jboolean JNICALL
JNU_SetIntField(JNIEnv *env, jobject self, const char *name, jint val);

JNIEXPORT jboolean JNICALL
JNU_SetLongField(JNIEnv *env, jobject self, const char *name, jlong val);

JNIEXPORT jboolean JNICALL
JNU_GetBooleanField(JNIEnv *env, jobject self, const char *name);

JNIEXPORT jboolean JNICALL
JNU_SetBooleanField(JNIEnv *env, jobject self, const char *name, jboolean val);

JNIEXPORT jint JNICALL
JNU_GetCharField(JNIEnv *env, jobject self, const char *name);

#endif           /* _AWT_UTIL_H_ */
