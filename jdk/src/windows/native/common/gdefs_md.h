/*
 * Win32 dependent type definitions
 */

#include <stddef.h>
#ifndef _WIN64
typedef int intptr_t;
typedef unsigned int uintptr_t;
typedef unsigned long DWORD_PTR, *PDWORD_PTR;
#endif
