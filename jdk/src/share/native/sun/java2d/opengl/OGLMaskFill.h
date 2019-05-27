#ifndef OGLMaskFill_h_Included
#define OGLMaskFill_h_Included

#include "OGLContext.h"

void OGLMaskFill_MaskFill(OGLContext *oglc,
                          jint x, jint y, jint w, jint h,
                          jint maskoff, jint maskscan, jint masklen,
                          unsigned char *pMask);

#endif /* OGLMaskFill_h_Included */
