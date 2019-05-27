#ifndef DrawPath_h_Included
#define DrawPath_h_Included

typedef struct {
    SurfaceDataRasInfo* pRasInfo;
    jint pixel;
    NativePrimitive* pPrim;
    CompositeInfo* pCompInfo;
} DrawHandlerData;

#define DHND(HND) ((DrawHandlerData*)((HND)->pData))

#endif
