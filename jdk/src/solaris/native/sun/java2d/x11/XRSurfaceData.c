#include "GraphicsPrimitiveMgr.h"
#include "Region.h"
#include "Trace.h"
#include "X11SurfaceData.h"

/*#include <xcb/xcb.h>*/
#include <X11/extensions/Xrender.h>

#ifndef RepeatNone  /* added in 0.10 */
#define RepeatNone    0
#define RepeatNormal  1
#define RepeatPad     2
#define RepeatReflect 3
#endif


#include <sys/uio.h>
#include <dlfcn.h>
#include <setjmp.h>

#ifndef HEADLESS
jfieldID pictID;
jfieldID xidID;
jfieldID blitMaskPMID;
jfieldID blitMaskPictID;
#endif /* !HEADLESS */

JNIEXPORT void JNICALL
   Java_sun_java2d_xr_XRSurfaceData_initXRPicture(JNIEnv *env, jobject xsd,
                                                  jlong pXSData,
                                                  jint pictFormat)
{
#ifndef HEADLESS

  X11SDOps *xsdo;
  XRenderPictFormat *fmt;

  J2dTraceLn(J2D_TRACE_INFO, "in XRSurfaceData_initXRender");

  xsdo = (X11SDOps *) jlong_to_ptr(pXSData);
  if (xsdo == NULL) {
      return;
  }

  if (xsdo->xrPic == None) {
      XRenderPictureAttributes pict_attr;
      pict_attr.repeat = RepeatNone;
      fmt = XRenderFindStandardFormat(awt_display, pictFormat);
      xsdo->xrPic =
         XRenderCreatePicture(awt_display, xsdo->drawable, fmt,
                              CPRepeat, &pict_attr);
  }

  (*env)->SetIntField (env, xsd, pictID, xsdo->xrPic);
  (*env)->SetIntField (env, xsd, xidID, xsdo->drawable);
#endif /* !HEADLESS */
}

JNIEXPORT void JNICALL
Java_sun_java2d_xr_XRSurfaceData_initIDs(JNIEnv *env, jclass xsd)
{
#ifndef HEADLESS
  J2dTraceLn(J2D_TRACE_INFO, "in XRSurfaceData_initIDs");

  pictID = (*env)->GetFieldID(env, xsd, "picture", "I");
  if (pictID == NULL) {
      return;
  }
  xidID = (*env)->GetFieldID(env, xsd, "xid", "I");
  if (xidID == NULL) {
      return;
  }

  XShared_initIDs(env, JNI_FALSE);
#endif /* !HEADLESS */
}


JNIEXPORT void JNICALL
Java_sun_java2d_xr_XRSurfaceData_XRInitSurface(JNIEnv *env, jclass xsd,
                                               jint depth,
                                               jint width, jint height,
                                               jlong drawable, jint pictFormat)
{
#ifndef HEADLESS
    X11SDOps *xsdo;

    J2dTraceLn(J2D_TRACE_INFO, "in XRSurfaceData_initSurface");

    xsdo = X11SurfaceData_GetOps(env, xsd);
    if (xsdo == NULL) {
        return;
    }

    XShared_initSurface(env, xsdo, depth, width, height, drawable);
#endif /* !HEADLESS */
}



JNIEXPORT void JNICALL
Java_sun_java2d_xr_XRSurfaceData_freeXSDOPicture(JNIEnv *env, jobject xsd,
                                                  jlong pXSData)
{
#ifndef HEADLESS
    X11SDOps *xsdo;

    J2dTraceLn(J2D_TRACE_INFO, "in XRSurfaceData_freeXSDOPicture");

    xsdo = X11SurfaceData_GetOps(env, xsd);
    if (xsdo == NULL) {
        return;
    }

    if(xsdo->xrPic != None) {
       XRenderFreePicture(awt_display, xsdo->xrPic);
       xsdo->xrPic = None;
    }
#endif /* !HEADLESS */
}
