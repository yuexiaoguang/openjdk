/*
 * significand(x) computes just
 *      scalb(x, (double) -ilogb(x)),
 * for exercising the fraction-part(F) IEEE 754-1985 test vector.
 */

#include "fdlibm.h"

#ifdef __STDC__
        double significand(double x)
#else
        double significand(x)
        double x;
#endif
{
        return __ieee754_scalb(x,(double) -ilogb(x));
}
