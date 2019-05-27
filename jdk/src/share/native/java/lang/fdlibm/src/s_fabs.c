/*
 * fabs(x) returns the absolute value of x.
 */

#include "fdlibm.h"

#ifdef __STDC__
        double fabs(double x)
#else
        double fabs(x)
        double x;
#endif
{
        __HI(x) &= 0x7fffffff;
        return x;
}
