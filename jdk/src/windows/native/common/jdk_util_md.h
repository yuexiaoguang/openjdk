#ifndef JDK_UTIL_MD_H
#define JDK_UTIL_MD_H

#include "jni.h"
#include <float.h>

// checking for nanness
#define ISNANF(f) _isnan(f)
#define ISNAND(d) _isnan(d)

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT HMODULE JDK_LoadSystemLibrary(const char* name);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /* JDK_UTIL_MD_H */
