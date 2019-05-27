
#if !defined(_DASSERT_H)
#define _DASSERT_H

#if defined(__cplusplus)
extern "C" {
#endif

#include "debug_util.h"

/* Use THIS_FILE when it is available. */
#ifndef THIS_FILE
    #define THIS_FILE __FILE__
#endif

#if defined(DEBUG)

#define DASSERT(_expr) \
        if ( !(_expr) ) { \
            DAssert_Impl( #_expr, THIS_FILE, __LINE__); \
        } else { \
        }

#define DASSERTMSG(_expr, _msg) \
        if ( !(_expr) ) { \
            DAssert_Impl( (_msg), THIS_FILE, __LINE__); \
        } else { \
        }

/* prototype for assert function */
typedef void (*DASSERT_CALLBACK)(const char * msg, const char * file, int line);

extern void DAssert_Impl(const char * msg, const char * file, int line);
extern void DAssert_SetCallback( DASSERT_CALLBACK pfn );

#else /* DEBUG not defined */

#define DASSERT(_expr)
#define DASSERTMSG(_expr, _msg)

#endif /* if defined(DEBUG) */

#if defined(__cplusplus)
} /* extern "C" */
#endif

#endif /* _DASSERT_H */
