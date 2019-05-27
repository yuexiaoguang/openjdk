#ifndef _AWT_MLIB_H_
#define _AWT_MLIB_H_

#include "awt_ImagingLib.h"


#ifdef __cplusplus
extern "C" {
#endif
typedef void (*mlib_start_timer)(int);
typedef void (*mlib_stop_timer)(int, int);

JNIEXPORT mlib_status awt_getImagingLib(JNIEnv *env, mlibFnS_t *sMlibFns,
                                        mlibSysFnS_t *sMlibSysFns);
JNIEXPORT mlib_start_timer awt_setMlibStartTimer();
JNIEXPORT mlib_stop_timer awt_setMlibStopTimer();

#ifdef __cplusplus
}; /* end of extern "C" */
#endif



#endif /* _AWT_MLIB_H */
