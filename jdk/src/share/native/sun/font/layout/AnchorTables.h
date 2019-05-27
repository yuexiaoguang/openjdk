#ifndef __ANCHORTABLES_H
#define __ANCHORTABLES_H

/**
 * \file
 * \internal
 */

#include "LETypes.h"
#include "LEFontInstance.h"
#include "OpenTypeTables.h"

U_NAMESPACE_BEGIN

struct AnchorTable
{
    le_uint16  anchorFormat;
    le_int16   xCoordinate;
    le_int16   yCoordinate;

  void    getAnchor(const LETableReference &base, LEGlyphID glyphID, const LEFontInstance *fontInstance,
                      LEPoint &anchor, LEErrorCode &success) const;
};

struct Format1AnchorTable : AnchorTable
{
  void getAnchor(const LEReferenceTo<Format1AnchorTable>& base,
                 const LEFontInstance *fontInstance, LEPoint &anchor, LEErrorCode &success) const;
};

struct Format2AnchorTable : AnchorTable
{
    le_uint16  anchorPoint;

    void getAnchor(const LEReferenceTo<Format2AnchorTable>& base,
                   LEGlyphID glyphID, const LEFontInstance *fontInstance,
                   LEPoint &anchor, LEErrorCode &success) const;
};

struct Format3AnchorTable : AnchorTable
{
    Offset  xDeviceTableOffset;
    Offset  yDeviceTableOffset;

    void getAnchor(const LEReferenceTo<Format3AnchorTable>& base,
                   const LEFontInstance *fontInstance, LEPoint &anchor,
                   LEErrorCode &success) const;
};

U_NAMESPACE_END
#endif


