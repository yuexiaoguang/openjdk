#ifndef SHARE_VM_ADLC_ADLC_HPP
#define SHARE_VM_ADLC_ADLC_HPP

//
// Standard include file for ADLC parser
//

// standard library constants
#include "stdio.h"
#include "stdlib.h"
#include <iostream>
#include "string.h"
#include "ctype.h"
#include "stdarg.h"
#include <sys/types.h>

#if _MSC_VER >= 1300
using namespace std;
#endif

#if _MSC_VER >= 1400
#define strdup _strdup
#endif

/* Make sure that we have the intptr_t and uintptr_t definitions */
#ifdef _WIN32
#ifndef _INTPTR_T_DEFINED
#ifdef _WIN64
typedef __int64 intptr_t;
#else
typedef int intptr_t;
#endif
#define _INTPTR_T_DEFINED
#endif

#ifndef _UINTPTR_T_DEFINED
#ifdef _WIN64
typedef unsigned __int64 uintptr_t;
#else
typedef unsigned int uintptr_t;
#endif
#define _UINTPTR_T_DEFINED
#endif
#endif // _WIN32

#if defined(LINUX) || defined(_ALLBSD_SOURCE)
  #include <inttypes.h>
#endif // LINUX || _ALLBSD_SOURCE

// Macros
#define uint32 unsigned int
#define uint   unsigned int

// VM components
#include "opto/opcodes.hpp"

// Macros
// Debugging note:  Put a breakpoint on "abort".
#undef assert
#define assert(cond, msg) { if (!(cond)) { fprintf(stderr, "assert fails %s %d: %s\n", __FILE__, __LINE__, msg); abort(); }}
#undef max
#define max(a, b)   (((a)>(b)) ? (a) : (b))

// ADLC components
#include "arena.hpp"
#include "opto/adlcVMDeps.hpp"
#include "filebuff.hpp"
#include "dict2.hpp"
#include "forms.hpp"
#include "formsopt.hpp"
#include "formssel.hpp"
#include "archDesc.hpp"
#include "adlparse.hpp"

// globally define ArchDesc for convenience.  Alternatively every form
// could have a backpointer to the AD but it's too complicated to pass
// it everywhere it needs to be available.
extern ArchDesc* globalAD;

#endif // SHARE_VM_ADLC_ADLC_HPP
