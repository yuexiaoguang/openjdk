#include "LETypes.h"
#include "LEFontInstance.h"
#include "OpenTypeTables.h"
#include "AnchorTables.h"
#include "MarkArrays.h"
#include "LESwaps.h"

U_NAMESPACE_BEGIN

le_int32 MarkArray::getMarkClass(const LETableReference &base, LEGlyphID glyphID,
                                 le_int32 coverageIndex, const LEFontInstance *fontInstance,
                              LEPoint &anchor, LEErrorCode &success) const
{
    le_int32 markClass = -1;

    if ( coverageIndex >= 0 && LE_SUCCESS(success) ) {
        le_uint16 mCount = SWAPW(markCount);
        if (coverageIndex < mCount) {
          LEReferenceToArrayOf<MarkRecord> markRecordArrayRef(base, success, markRecordArray, mCount);
            if(LE_FAILURE(success)) {
              return markClass;
            }
            const MarkRecord *markRecord = &markRecordArray[coverageIndex];
            Offset anchorTableOffset = SWAPW(markRecord->markAnchorTableOffset);
            LEReferenceTo<AnchorTable> anchorTable(base, success, anchorTableOffset);

            if(LE_FAILURE(success)) {
              return markClass;
            }

            anchorTable->getAnchor(anchorTable, glyphID, fontInstance, anchor, success);
            markClass = SWAPW(markRecord->markClass);
        }

        // XXXX If we get here, the table is mal-formed
    }

    return markClass;
}

U_NAMESPACE_END
