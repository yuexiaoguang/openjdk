/*
 * isnan(x) returns 1 is x is nan, else 0;
 * no branching!
 */

#include "fdlibm.h"

#ifdef __STDC__
        int isnan(double x)
#else
        int isnan(x)
        double x;
#endif
{
        int hx,lx;
        hx = (__HI(x)&0x7fffffff);
        lx = __LO(x);
        hx |= (unsigned)(lx|(-lx))>>31;
        hx = 0x7ff00000 - hx;
        return ((unsigned)(hx))>>31;
}
