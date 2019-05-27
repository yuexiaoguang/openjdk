#ifndef __FONTINSTANCEADAPTER_H
#define __FONTINSTANCEADAPTER_H

#include "LETypes.h"
#include "LEFontInstance.h"
#include "jni.h"
#include "sunfontids.h"
#include "fontscalerdefs.h"
#include <jni_util.h>

class FontInstanceAdapter : public LEFontInstance {
private:
    JNIEnv *env;
    jobject font2D;
    jobject fontStrike;

    float xppem;
    float yppem;

    float xScaleUnitsToPoints;
    float yScaleUnitsToPoints;

    float xScalePixelsToUnits;
    float yScalePixelsToUnits;

    le_int32 upem;
    float xPointSize, yPointSize;
    float txMat[4];

    float euclidianDistance(float a, float b);

    /* Table format is the same as defined in the truetype spec.
       Pointer can be NULL (e.g. for Type1 fonts). */
    TTLayoutTableCache* layoutTables;

public:
    FontInstanceAdapter(JNIEnv *env,
                        jobject theFont2D, jobject theFontStrike,
                        float *matrix, le_int32 xRes, le_int32 yRes,
                        le_int32 theUPEM, TTLayoutTableCache *ltables);

    virtual ~FontInstanceAdapter() { };

    virtual const LEFontInstance *getSubFont(const LEUnicode chars[],
                            le_int32 *offset, le_int32 limit,
                            le_int32 script, LEErrorCode &success) const {
      return this;
    }

    // tables are cached with the native font scaler data
    // only supports gsub, gpos, gdef, mort tables at present
    virtual const void *getFontTable(LETag tableTag) const;
    virtual const void *getFontTable(LETag tableTag, size_t &len) const;

    virtual void *getKernPairs() const {
        return layoutTables->kernPairs;
    }
    virtual void setKernPairs(void *pairs) const {
        layoutTables->kernPairs = pairs;
    }

    virtual le_bool canDisplay(LEUnicode32 ch) const
    {
        return  (le_bool)env->CallBooleanMethod(font2D,
                                                sunFontIDs.canDisplayMID, ch);
    };

    virtual le_int32 getUnitsPerEM() const {
       return upem;
    };

    virtual LEGlyphID mapCharToGlyph(LEUnicode32 ch, const LECharMapper *mapper) const;

    virtual LEGlyphID mapCharToGlyph(LEUnicode32 ch) const;

    virtual void mapCharsToWideGlyphs(const LEUnicode chars[],
        le_int32 offset, le_int32 count, le_bool reverse,
        const LECharMapper *mapper, le_uint32 glyphs[]) const;

    virtual le_uint32 mapCharToWideGlyph(LEUnicode32 ch,
        const LECharMapper *mapper) const;

    virtual void getGlyphAdvance(LEGlyphID glyph, LEPoint &advance) const;

    virtual void getKerningAdjustment(LEPoint &adjustment) const;

    virtual void getWideGlyphAdvance(le_uint32 glyph, LEPoint &advance) const;

    virtual le_bool getGlyphPoint(LEGlyphID glyph,
        le_int32 pointNumber, LEPoint &point) const;

    float getXPixelsPerEm() const
    {
        return xppem;
    };

    float getYPixelsPerEm() const
    {
        return yppem;
    };

    float xUnitsToPoints(float xUnits) const
    {
        return xUnits * xScaleUnitsToPoints;
    };

    float yUnitsToPoints(float yUnits) const
    {
        return yUnits * yScaleUnitsToPoints;
    };

    void unitsToPoints(LEPoint &units, LEPoint &points) const
    {
        points.fX = xUnitsToPoints(units.fX);
        points.fY = yUnitsToPoints(units.fY);
    }

    float xPixelsToUnits(float xPixels) const
    {
        return xPixels * xScalePixelsToUnits;
    };

    float yPixelsToUnits(float yPixels) const
    {
        return yPixels * yScalePixelsToUnits;
    };

    void pixelsToUnits(LEPoint &pixels, LEPoint &units) const
    {
        units.fX = xPixelsToUnits(pixels.fX);
        units.fY = yPixelsToUnits(pixels.fY);
    };

    virtual float getScaleFactorX() const {
        return xScalePixelsToUnits;
    };

    virtual float getScaleFactorY() const {
        return yScalePixelsToUnits;
    };

    void transformFunits(float xFunits, float yFunits, LEPoint &pixels) const;

    virtual le_int32 getAscent() const { return 0; };  // not used
    virtual le_int32 getDescent() const { return 0; }; // not used
    virtual le_int32 getLeading() const { return 0; }; // not used
};

#endif
