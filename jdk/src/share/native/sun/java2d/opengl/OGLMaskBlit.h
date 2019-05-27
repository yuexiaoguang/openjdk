#ifndef OGLMaskBlit_h_Included
#define OGLMaskBlit_h_Included

#include "OGLContext.h"

void OGLMaskBlit_MaskBlit(JNIEnv *env, OGLContext *oglc,
                          jint dstx, jint dsty,
                          jint width, jint height,
                          void *pPixels);

#endif /* OGLMaskBlit_h_Included */
