#ifndef JDK_UTIL_MD_H
#define JDK_UTIL_MD_H

// checking for nanness
#ifdef __solaris__
#include <ieeefp.h>
#define ISNANF(f) isnanf(f)
#define ISNAND(d) isnand(d)
#elif defined(MACOSX)
#include <math.h>
#define ISNANF(f) isnan(f)
#define ISNAND(d) isnan(d)
#elif defined(__linux__) || defined(_ALLBSD_SOURCE)
#include <math.h>
#define ISNANF(f) isnanf(f)
#define ISNAND(d) isnan(d)
#else
#error "missing platform-specific definition here"
#endif

#endif /* JDK_UTIL_MD_H */
