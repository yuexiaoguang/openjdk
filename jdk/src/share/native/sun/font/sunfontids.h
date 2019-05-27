#ifndef SunFontIDIncludesDefined
#define SunFontIDIncludesDefined

#include "jni.h"

#ifdef  __cplusplus
extern "C" {
#endif

typedef struct FontManagerNativeIDs {

    /* sun/font/Font2D methods */
    jmethodID getMapperMID;
    jmethodID getTableBytesMID;
    jmethodID canDisplayMID;
    jmethodID f2dCharToGlyphMID;

    /* sun/font/CharToGlyphMapper methods */
    jmethodID charToGlyphMID;

    /* sun/font/PhysicalStrike methods */
    jmethodID getGlyphMetricsMID;
    jmethodID getGlyphPointMID;
    jmethodID adjustPointMID;
    jfieldID  pScalerContextFID;

    /* java/awt/geom/Rectangle2D.Float */
    jclass rect2DFloatClass;
    jmethodID rect2DFloatCtr;
    jmethodID rect2DFloatCtr4;
    jfieldID rectF2DX, rectF2DY, rectF2DWidth, rectF2DHeight;

    /* java/awt/geom/Point2D.Float */
    jclass pt2DFloatClass;
    jmethodID pt2DFloatCtr;
    jfieldID xFID, yFID;

    /* java/awt/geom/GeneralPath */
    jclass gpClass;
    jmethodID gpCtr;
    jmethodID gpCtrEmpty;

    /* sun/font/StrikeMetrics */
    jclass strikeMetricsClass;
    jmethodID strikeMetricsCtr;

    /* sun/font/TrueTypeFont */
    jmethodID ttReadBlockMID;
    jmethodID ttReadBytesMID;

    /* sun/font/Type1Font */
    jmethodID readFileMID;

    /* sun/font/GlyphList */
    jfieldID glyphListX, glyphListY, glyphListLen,
      glyphImages, glyphListUsePos, glyphListPos, lcdRGBOrder, lcdSubPixPos;
} FontManagerNativeIDs;

/* Note: we share variable in the context of fontmanager lib
   but we need access method to use it from separate rasterizer lib */
extern FontManagerNativeIDs sunFontIDs;
JNIEXPORT FontManagerNativeIDs getSunFontIDs(JNIEnv* env);

#ifdef  __cplusplus
}
#endif

#endif
