#include "LETypes.h"
#include "LEGlyphFilter.h"
#include "GDEFMarkFilter.h"
#include "GlyphDefinitionTables.h"

U_NAMESPACE_BEGIN

GDEFMarkFilter::GDEFMarkFilter(const LEReferenceTo<GlyphDefinitionTableHeader> &gdefTable, LEErrorCode &success)
  : classDefTable(gdefTable->getGlyphClassDefinitionTable(gdefTable, success))
{
  if(!classDefTable.isValid()) {
    success = LE_INTERNAL_ERROR;
  }
}

GDEFMarkFilter::~GDEFMarkFilter()
{
    // nothing to do?
}

le_bool GDEFMarkFilter::accept(LEGlyphID glyph, LEErrorCode &success) const
{
  le_int32 glyphClass = classDefTable->getGlyphClass(classDefTable, glyph, success);

  return glyphClass == gcdMarkGlyph;
}

U_NAMESPACE_END
