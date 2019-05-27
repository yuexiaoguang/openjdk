#ifndef j2d_md_h_Included
#define j2d_md_h_Included
#include <sys/types.h>

/*
 * Linux and MACOSX's version of <sys/types.h> does not define intptr_t
 */
#if defined(__linux__) || defined(MACOSX)
#include <stdint.h>
#endif /* __linux__ || MACOSX */

typedef unsigned char   jubyte;
typedef unsigned short  jushort;
typedef unsigned int    juint;

#endif /* j2d_md_h_Included */
