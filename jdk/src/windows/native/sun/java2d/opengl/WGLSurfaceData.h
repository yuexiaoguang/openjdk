#ifndef WGLSurfaceData_h_Included
#define WGLSurfaceData_h_Included

#include "OGLSurfaceData.h"
#include "WGLGraphicsConfig.h"
#include "J2D_GL/gl.h"

/**
 * The WGLSDOps structure contains the WGL-specific information for a given
 * OGLSurfaceData.  It is referenced by the native OGLSDOps structure.
 */
typedef struct _WGLSDOps {
    WGLGraphicsConfigInfo *configInfo;
    HWND        window;
    HPBUFFERARB pbuffer;
    HDC pbufferDC;
} WGLSDOps;

#endif /* WGLSurfaceData_h_Included */
