/*
 * wrapper hypot(x,y)
 */

#include "fdlibm.h"


#ifdef __STDC__
        double hypot(double x, double y)/* wrapper hypot */
#else
        double hypot(x,y)               /* wrapper hypot */
        double x,y;
#endif
{
#ifdef _IEEE_LIBM
        return __ieee754_hypot(x,y);
#else
        double z;
        z = __ieee754_hypot(x,y);
        if(_LIB_VERSION == _IEEE_) return z;
        if((!finite(z))&&finite(x)&&finite(y))
            return __kernel_standard(x,y,4); /* hypot overflow */
        else
            return z;
#endif
}
