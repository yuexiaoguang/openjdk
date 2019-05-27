#ifndef __DEVICETABLES_H
#define __DEVICETABLES_H

/**
 * \file
 * \internal
 */

#include "LETypes.h"
#include "OpenTypeTables.h"

U_NAMESPACE_BEGIN

struct DeviceTable
{
    le_uint16  startSize;
    le_uint16  endSize;
    le_uint16  deltaFormat;
    le_uint16  deltaValues[ANY_NUMBER];

    le_int16   getAdjustment(const LEReferenceTo<DeviceTable> &base, le_uint16 ppem, LEErrorCode &success) const;

private:
    static const le_uint16 fieldMasks[];
    static const le_uint16 fieldSignBits[];
    static const le_uint16 fieldBits[];
};
LE_VAR_ARRAY(DeviceTable, deltaValues)

U_NAMESPACE_END
#endif


