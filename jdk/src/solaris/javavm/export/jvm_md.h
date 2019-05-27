#ifndef _JAVASOFT_JVM_MD_H_
#define _JAVASOFT_JVM_MD_H_

/*
 * This file is currently collecting system-specific dregs for the
 * JNI conversion, which should be sorted out later.
 */

#include <dirent.h>             /* For DIR */
#include <sys/param.h>          /* For MAXPATHLEN */
#include <unistd.h>             /* For F_OK, R_OK, W_OK */
#include <stddef.h>             /* For ptrdiff_t */
#include <stdint.h>             /* For uintptr_t */

#define JNI_ONLOAD_SYMBOLS   {"JNI_OnLoad"}
#define JNI_ONUNLOAD_SYMBOLS {"JNI_OnUnload"}

#define JNI_LIB_PREFIX "lib"
#ifdef __APPLE__
#define JNI_LIB_SUFFIX ".dylib"
#define VERSIONED_JNI_LIB_NAME(NAME, VERSION) JNI_LIB_PREFIX NAME "." VERSION JNI_LIB_SUFFIX
#else
#define JNI_LIB_SUFFIX ".so"
#define VERSIONED_JNI_LIB_NAME(NAME, VERSION) JNI_LIB_PREFIX NAME JNI_LIB_SUFFIX "." VERSION
#endif
#define JNI_LIB_NAME(NAME) JNI_LIB_PREFIX NAME JNI_LIB_SUFFIX

#define JVM_MAXPATHLEN MAXPATHLEN

#define JVM_R_OK    R_OK
#define JVM_W_OK    W_OK
#define JVM_X_OK    X_OK
#define JVM_F_OK    F_OK

/*
 * File I/O
 */

#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <errno.h>
#include <sys/signal.h>

/* O Flags */

#define JVM_O_RDONLY     O_RDONLY
#define JVM_O_WRONLY     O_WRONLY
#define JVM_O_RDWR       O_RDWR
#define JVM_O_O_APPEND   O_APPEND
#define JVM_O_EXCL       O_EXCL
#define JVM_O_CREAT      O_CREAT
#define JVM_O_DELETE     0x10000

/* Signals */

#define JVM_SIGINT     SIGINT
#define JVM_SIGTERM    SIGTERM


#endif /* !_JAVASOFT_JVM_MD_H_ */
