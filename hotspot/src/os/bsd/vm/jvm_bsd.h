
#ifndef OS_BSD_VM_JVM_BSD_H
#define OS_BSD_VM_JVM_BSD_H

/*
// HotSpot integration note:
//
// This is derived from the JDK classic file:
// "$JDK/src/solaris/javavm/export/jvm_md.h":15 (ver. 1.10 98/04/22)
// All local includes have been commented out.
*/

#ifndef JVM_MD_H
#define JVM_MD_H

/*
 * This file is currently collecting system-specific dregs for the
 * JNI conversion, which should be sorted out later.
 */
#ifdef __NetBSD__
/*
 * Since we are compiling with c++, we need the following to make c macros
 * visible.
 */
# if !defined(__STDC_LIMIT_MACROS)
#  define __STDC_LIMIT_MACROS           1
# endif
# if !defined(__STDC_CONSTANT_MACROS)
#  define __STDC_CONSTANT_MACROS        1
# endif
# if !defined(__STDC_FORMAT_MACROS)
#  define __STDC_FORMAT_MACROS          1
# endif
#endif

#include <dirent.h>             /* For DIR */
#include <sys/param.h>          /* For MAXPATHLEN */
#include <sys/socket.h>         /* For socklen_t */
#include <unistd.h>             /* For F_OK, R_OK, W_OK */

#define JNI_ONLOAD_SYMBOLS      {"JNI_OnLoad"}
#define JNI_ONUNLOAD_SYMBOLS    {"JNI_OnUnload"}
#define JVM_ONLOAD_SYMBOLS      {"JVM_OnLoad"}
#define AGENT_ONLOAD_SYMBOLS    {"Agent_OnLoad"}
#define AGENT_ONUNLOAD_SYMBOLS  {"Agent_OnUnload"}
#define AGENT_ONATTACH_SYMBOLS  {"Agent_OnAttach"}

#define JNI_LIB_PREFIX "lib"
#ifdef __APPLE__
#define JNI_LIB_SUFFIX ".dylib"
#else
#define JNI_LIB_SUFFIX ".so"
#endif

// Hack: MAXPATHLEN is 4095 on some Bsd and 4096 on others. This may
//       cause problems if JVM and the rest of JDK are built on different
//       Bsd releases. Here we define JVM_MAXPATHLEN to be MAXPATHLEN + 1,
//       so buffers declared in VM are always >= 4096.
#define JVM_MAXPATHLEN MAXPATHLEN + 1

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

/* O Flags */

#define JVM_O_RDONLY     O_RDONLY
#define JVM_O_WRONLY     O_WRONLY
#define JVM_O_RDWR       O_RDWR
#define JVM_O_O_APPEND   O_APPEND
#define JVM_O_EXCL       O_EXCL
#define JVM_O_CREAT      O_CREAT

/* Signal definitions */

#define BREAK_SIGNAL     SIGQUIT           /* Thread dumping support.    */
#define INTERRUPT_SIGNAL SIGUSR1           /* Interruptible I/O support. */
#define SHUTDOWN1_SIGNAL SIGHUP            /* Shutdown Hooks support.    */
#define SHUTDOWN2_SIGNAL SIGINT
#define SHUTDOWN3_SIGNAL SIGTERM

#ifndef SIGRTMIN
#ifdef __OpenBSD__
#define SIGRTMIN        1
#else
#define SIGRTMIN        33
#endif
#endif
#ifndef SIGRTMAX
#ifdef __OpenBSD__
#define SIGRTMAX        31
#else
#define SIGRTMAX        63
#endif
#endif
#endif /* JVM_MD_H */

#endif // OS_BSD_VM_JVM_BSD_H
