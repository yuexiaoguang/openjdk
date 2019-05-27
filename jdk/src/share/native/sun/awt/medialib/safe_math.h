
#ifndef __SAFE_MATH_H__
#define __SAFE_MATH_H__

#define SAFE_TO_MULT(a, b) \
    (((a) > 0) && ((b) >= 0) && ((0x7fffffff / (a)) > (b)))

#define SAFE_TO_ADD(a, b) \
    (((a) >= 0) && ((b) >= 0) && ((0x7fffffff - (a)) > (b)))

#endif // __SAFE_MATH_H__
