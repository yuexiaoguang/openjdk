/* platform-dependent definitions */

#ifndef _AWT_RECT_H
#define _AWT_RECT_H

#include <windows.h>
typedef RECT RECT_T;

#define RECT_EQ_X(r1, r2)   ((r1).left==(r2).left && (r1).right==(r2).right)

#define RECT_SET(r,xx,yy,ww,hh) \
    do {                        \
        (r).left=(xx);          \
        (r).top=(yy);           \
        (r).right=(xx)+(ww);    \
        (r).bottom=(yy)+(hh);   \
    } while (0)

#define RECT_INC_HEIGHT(r) (r).bottom++

#if defined(__cplusplus)
extern "C" {
#endif

int BitmapToYXBandedRectangles(int bitsPerPixel, int width, int height,
        unsigned char * buf, RECT_T * outBuf);

#if defined(__cplusplus)
}
#endif

#endif // _AWT_RECT_H
