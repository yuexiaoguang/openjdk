/* platform-dependent definitions */

#ifndef _AWT_RECT_H
#define _AWT_RECT_H

#include <X11/Xlib.h>
typedef XRectangle RECT_T;

#define RECT_EQ_X(r1,r2)        ((r1).x==(r2).x && (r1).width==(r2).width)

#define RECT_SET(r,xx,yy,ww,hh)  \
    do {                         \
        (r).x=(xx);              \
        (r).y=(yy);              \
        (r).width=(ww);          \
        (r).height=(hh);         \
    } while (0)

#define RECT_INC_HEIGHT(r)      (r).height++

#if defined(__cplusplus)
extern "C" {
#endif

int BitmapToYXBandedRectangles(int bitsPerPixel, int width, int height,
        unsigned char * buf, RECT_T * outBuf);

#if defined(__cplusplus)
}
#endif

#endif // _AWT_RECT_H
