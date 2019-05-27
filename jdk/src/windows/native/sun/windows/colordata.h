#ifndef _COLORDATA_H_
#define _COLORDATA_H_

#include "img_globals.h"

typedef struct _ColorData {
    char* img_oda_red;
    char* img_oda_green;
    char* img_oda_blue;
    unsigned char* img_clr_tbl;
    int *pGrayInverseLutData;
} ColorData;

#define CANFREE(pData) (pData)

#endif
