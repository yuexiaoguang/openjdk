#ifndef _AWT_MLIB_H_
#define _AWT_MLIB_H_

#include "awt_ImagingLib.h"

typedef void (*mlib_start_timer)(int);
typedef void (*mlib_stop_timer)(int, int);

mlib_status awt_getImagingLib(JNIEnv *, mlibFnS_t *, mlibSysFnS_t *);
mlib_start_timer awt_setMlibStartTimer();
mlib_stop_timer awt_setMlibStopTimer();

#endif /* _AWT_MLIB_H */
