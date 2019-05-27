#ifndef __COVERAGETABLES_H
#define __COVERAGETABLES_H

/**
 * \file
 * \internal
 */

#include "LETypes.h"
#include "OpenTypeTables.h"

U_NAMESPACE_BEGIN

struct CoverageTable
{
    le_uint16 coverageFormat;

    le_int32 getGlyphCoverage(const LETableReference &base, LEGlyphID glyphID, LEErrorCode &success) const;
};

struct CoverageFormat1Table : CoverageTable
{
    le_uint16  glyphCount;
    TTGlyphID glyphArray[ANY_NUMBER];

    le_int32 getGlyphCoverage(LEReferenceTo<CoverageFormat1Table> &base, LEGlyphID glyphID, LEErrorCode &success) const;
};
LE_VAR_ARRAY(CoverageFormat1Table, glyphArray)


struct CoverageFormat2Table : CoverageTable
{
    le_uint16        rangeCount;
    GlyphRangeRecord rangeRecordArray[ANY_NUMBER];

    le_int32 getGlyphCoverage(LEReferenceTo<CoverageFormat2Table> &base, LEGlyphID glyphID, LEErrorCode &success) const;
};
LE_VAR_ARRAY(CoverageFormat2Table, rangeRecordArray)

U_NAMESPACE_END
#endif
