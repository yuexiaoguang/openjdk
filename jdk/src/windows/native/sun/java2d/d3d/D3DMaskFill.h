#ifndef D3DMaskFill_h_Included
#define D3DMaskFill_h_Included

#include "D3DContext.h"

HRESULT D3DMaskFill_MaskFill(D3DContext *d3dc,
                             jint x, jint y, jint w, jint h,
                             jint maskoff, jint maskscan, jint masklen,
                             unsigned char *pMask);

#endif /* D3DMaskFill_h_Included */
