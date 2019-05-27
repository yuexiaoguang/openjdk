#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "jlong.h"

/* this is a fake c file to make the build happy since there is no
   real SocketDispatcher.c file on Solaris but there is on windows. */

static jfieldID fd_fdID;        /* for jint 'fd' in java.io.FileDescriptor */
