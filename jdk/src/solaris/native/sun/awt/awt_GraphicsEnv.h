#ifndef _AWT_GRAPHICSENV_H_
#define _AWT_GRAPHICSENV_H_

#include <jni_util.h>

#ifndef HEADLESS
#define MITSHM
#endif /* !HEADLESS */

#define UNSET_MITSHM (-2)
#define NOEXT_MITSHM (-1)
#define CANT_USE_MITSHM (0)
#define CAN_USE_MITSHM (1)

#ifdef MITSHM

#include <sys/ipc.h>
#include <sys/shm.h>
#include <X11/extensions/XShm.h>
#ifndef X_ShmAttach
#include <X11/Xmd.h>
#include <X11/extensions/shmproto.h>
#endif

#define MITSHM_PERM_COMMON (0666)
#define MITSHM_PERM_OWNER  (0600)

extern int XShmQueryExtension();

void TryInitMITShm(JNIEnv *env, jint *shmExt, jint *shmPixmaps);
void resetXShmAttachFailed();
jboolean isXShmAttachFailed();

#endif /* MITSHM */

/* fieldIDs for X11GraphicsConfig fields that may be accessed from C */
struct X11GraphicsConfigIDs {
    jfieldID aData;
    jfieldID bitsPerPixel;
    jfieldID screen;
};

/* fieldIDs for X11GraphicsDevice fields that may be accessed from C */
struct X11GraphicsDeviceIDs {
    jfieldID screen;
};

#endif /* _AWT_GRAPHICSENV_H_ */
