#include "SurfaceData.h"
#include "colordata.h"


typedef struct _BufImgSDOps {
    SurfaceDataOps      sdOps;
    jobject             array;
    jint                offset;
    jint                bitoffset;
    jint                pixStr;
    jint                scanStr;
    jobject             icm;
    jobject             lutarray;
    jint                lutsize;
    SurfaceDataBounds   rasbounds;
} BufImgSDOps;

typedef struct _BufImgRIPrivate {
    jint                lockFlags;
    void                *base;
    void                *lutbase;
    ColorData           *cData;
} BufImgRIPrivate;
