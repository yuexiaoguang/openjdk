#include <string.h>

#include "Any3Byte.h"

/*
 * This file declares, registers, and defines the various graphics
 * primitive loops to manipulate surfaces of type "Any3Byte".
 *
 * See also LoopMacros.h
 */

RegisterFunc RegisterAny3Byte;

DECLARE_SOLID_FILLRECT(Any3Byte);
DECLARE_SOLID_FILLSPANS(Any3Byte);
DECLARE_SOLID_PARALLELOGRAM(Any3Byte);
DECLARE_SOLID_DRAWLINE(Any3Byte);
DECLARE_XOR_FILLRECT(Any3Byte);
DECLARE_XOR_FILLSPANS(Any3Byte);
DECLARE_XOR_DRAWLINE(Any3Byte);
DECLARE_SOLID_DRAWGLYPHLIST(Any3Byte);
DECLARE_XOR_DRAWGLYPHLIST(Any3Byte);

NativePrimitive Any3BytePrimitives[] = {
    REGISTER_SOLID_FILLRECT(Any3Byte),
    REGISTER_SOLID_FILLSPANS(Any3Byte),
    REGISTER_SOLID_PARALLELOGRAM(Any3Byte),
    REGISTER_SOLID_LINE_PRIMITIVES(Any3Byte),
    REGISTER_XOR_FILLRECT(Any3Byte),
    REGISTER_XOR_FILLSPANS(Any3Byte),
    REGISTER_XOR_LINE_PRIMITIVES(Any3Byte),
    REGISTER_SOLID_DRAWGLYPHLIST(Any3Byte),
    REGISTER_XOR_DRAWGLYPHLIST(Any3Byte),
};

jboolean RegisterAny3Byte(JNIEnv *env)
{
    return RegisterPrimitives(env, Any3BytePrimitives,
                              ArraySize(Any3BytePrimitives));
}

DEFINE_ISOCOPY_BLIT(Any3Byte)

DEFINE_ISOSCALE_BLIT(Any3Byte)

DEFINE_ISOXOR_BLIT(Any3Byte)

DEFINE_SOLID_FILLRECT(Any3Byte)

DEFINE_SOLID_FILLSPANS(Any3Byte)

DEFINE_SOLID_PARALLELOGRAM(Any3Byte)

DEFINE_SOLID_DRAWLINE(Any3Byte)

DEFINE_XOR_FILLRECT(Any3Byte)

DEFINE_XOR_FILLSPANS(Any3Byte)

DEFINE_XOR_DRAWLINE(Any3Byte)

DEFINE_SOLID_DRAWGLYPHLIST(Any3Byte)

DEFINE_XOR_DRAWGLYPHLIST(Any3Byte)
