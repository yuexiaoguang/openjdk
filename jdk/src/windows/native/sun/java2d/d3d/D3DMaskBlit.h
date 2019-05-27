#ifndef D3DMaskBlit_h_Included
#define D3DMaskBlit_h_Included

#include "D3DContext.h"

HRESULT D3DMaskBlit_MaskBlit(JNIEnv *env, D3DContext *d3dc,
                             jint dstx, jint dsty,
                             jint width, jint height,
                             void *pPixels);

#endif /* D3DMaskBlit_h_Included */
