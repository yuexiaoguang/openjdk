#include "LETypes.h"
#include "MorphTables.h"
#include "SubtableProcessor2.h"
#include "NonContextualGlyphSubst.h"
#include "NonContextualGlyphSubstProc2.h"
#include "SegmentArrayProcessor2.h"
#include "LEGlyphStorage.h"
#include "LESwaps.h"

U_NAMESPACE_BEGIN

UOBJECT_DEFINE_RTTI_IMPLEMENTATION(SegmentArrayProcessor2)

SegmentArrayProcessor2::SegmentArrayProcessor2()
{
}

SegmentArrayProcessor2::SegmentArrayProcessor2(const LEReferenceTo<MorphSubtableHeader2> &morphSubtableHeader, LEErrorCode &success)
  : NonContextualGlyphSubstitutionProcessor2(morphSubtableHeader, success)
{
  const LEReferenceTo<NonContextualGlyphSubstitutionHeader2> header(morphSubtableHeader, success);
  segmentArrayLookupTable = LEReferenceTo<SegmentArrayLookupTable>(morphSubtableHeader,  success, &header->table); // don't parent to 'header' as it is on the stack
}

SegmentArrayProcessor2::~SegmentArrayProcessor2()
{
}

void SegmentArrayProcessor2::process(LEGlyphStorage &glyphStorage, LEErrorCode &success)
{
    const LookupSegment *segments = segmentArrayLookupTable->segments;
    le_int32 glyphCount = glyphStorage.getGlyphCount();
    le_int32 glyph;

    for (glyph = 0; glyph < glyphCount; glyph += 1) {
        LEGlyphID thisGlyph = glyphStorage[glyph];
        // lookupSegment already range checked by lookupSegment() function.
        const LookupSegment *lookupSegment = segmentArrayLookupTable->lookupSegment(segmentArrayLookupTable, segments, thisGlyph, success);

        if (lookupSegment != NULL&& LE_SUCCESS(success))  {
            TTGlyphID firstGlyph = SWAPW(lookupSegment->firstGlyph);
            TTGlyphID lastGlyph = SWAPW(lookupSegment->lastGlyph);
            le_int16  offset = SWAPW(lookupSegment->value);
            TTGlyphID thisGlyphId=  LE_GET_GLYPH(thisGlyph);
            LEReferenceToArrayOf<TTGlyphID> glyphArray(subtableHeader, success, offset, lastGlyph - firstGlyph + 1);
            if (offset != 0 && thisGlyphId <= lastGlyph && thisGlyphId >= firstGlyph && LE_SUCCESS(success) ) {
              TTGlyphID   newGlyph   = SWAPW(glyphArray[thisGlyphId]);
              glyphStorage[glyph] = LE_SET_GLYPH(thisGlyph, newGlyph);
            }
        }
    }
}

U_NAMESPACE_END
