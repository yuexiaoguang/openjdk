#include "LETypes.h"
#include "OpenTypeTables.h"
#include "GlyphPositioningTables.h"
#include "CursiveAttachmentSubtables.h"
#include "AnchorTables.h"
#include "GlyphIterator.h"
#include "OpenTypeUtilities.h"
#include "LESwaps.h"

U_NAMESPACE_BEGIN

le_uint32 CursiveAttachmentSubtable::process(const LEReferenceTo<CursiveAttachmentSubtable> &base, GlyphIterator *glyphIterator, const LEFontInstance *fontInstance, LEErrorCode &success) const
{
    LEGlyphID glyphID       = glyphIterator->getCurrGlyphID();
    le_int32  coverageIndex = getGlyphCoverage(base, glyphID, success);
    le_uint16 eeCount       = SWAPW(entryExitCount);

    if (coverageIndex < 0 || coverageIndex >= eeCount || LE_FAILURE(success)) {
        glyphIterator->setCursiveGlyph();
        return 0;
    }

    LEPoint entryAnchor, exitAnchor;
    Offset entryOffset = SWAPW(entryExitRecords[coverageIndex].entryAnchor);
    Offset exitOffset  = SWAPW(entryExitRecords[coverageIndex].exitAnchor);

    if (entryOffset != 0) {
        LEReferenceTo<AnchorTable> entryAnchorTable(base, success, entryOffset);

        if( LE_SUCCESS(success) ) {
          entryAnchorTable->getAnchor(entryAnchorTable, glyphID, fontInstance, entryAnchor, success);
          glyphIterator->setCursiveEntryPoint(entryAnchor);
        }
    } else {
        //glyphIterator->clearCursiveEntryPoint();
    }

    if (exitOffset != 0) {
        LEReferenceTo<AnchorTable> exitAnchorTable(base, success, exitOffset);

        if( LE_SUCCESS(success) ) {
          exitAnchorTable->getAnchor(exitAnchorTable, glyphID, fontInstance, exitAnchor, success);
          glyphIterator->setCursiveExitPoint(exitAnchor);
        }
    } else {
        //glyphIterator->clearCursiveExitPoint();
    }

    return 1;
}

U_NAMESPACE_END
