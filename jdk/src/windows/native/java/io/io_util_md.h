#include "jni.h"
#include "jni_util.h"

/*
 * Macros to use the right data type for file descriptors
 */
#define FD jlong

/*
 * Prototypes for functions in io_util_md.c called from io_util.c,
 * FileDescriptor.c, FileInputStream.c, FileOutputStream.c
 */
WCHAR* pathToNTPath(JNIEnv *env, jstring path, jboolean throwFNFE);
WCHAR* fileToNTPath(JNIEnv *env, jobject file, jfieldID id);
WCHAR* getPrefixed(const WCHAR* path, int pathlen);
WCHAR* currentDir(int di);
int currentDirLength(const WCHAR* path, int pathlen);
int handleAvailable(FD fd, jlong *pbytes);
int handleSync(FD fd);
int handleSetLength(FD fd, jlong length);
JNIEXPORT jint handleRead(FD fd, void *buf, jint len);
jint handleWrite(FD fd, const void *buf, jint len);
jint handleAppend(FD fd, const void *buf, jint len);
jint handleClose(JNIEnv *env, jobject this, jfieldID fid);
jlong handleLseek(FD fd, jlong offset, jint whence);

/*
 * Returns an opaque handle to file named by "path".  If an error occurs,
 * returns -1 and an exception is pending.
 */
FD winFileHandleOpen(JNIEnv *env, jstring path, int flags);

/*
 * Macros to set/get fd from the java.io.FileDescriptor.
 * If GetObjectField returns null, SET_FD will stop and GET_FD
 * will simply return -1 to avoid crashing VM.
 */
#define SET_FD(this, fd, fid) \
    if ((*env)->GetObjectField(env, (this), (fid)) != NULL) \
        (*env)->SetLongField(env, (*env)->GetObjectField(env, (this), (fid)), IO_handle_fdID, (fd))

#define GET_FD(this, fid) \
    ((*env)->GetObjectField(env, (this), (fid)) == NULL) ? \
      -1 : (*env)->GetLongField(env, (*env)->GetObjectField(env, (this), (fid)), IO_handle_fdID)

/*
 * Macros to set/get fd when inside java.io.FileDescriptor
 */
#define THIS_FD(obj) (*env)->GetLongField(env, obj, IO_handle_fdID)

/*
 * Route the routines away from VM
 */
#define IO_Append handleAppend
#define IO_Write handleWrite
#define IO_Sync handleSync
#define IO_Read handleRead
#define IO_Lseek handleLseek
#define IO_Available handleAvailable
#define IO_SetLength handleSetLength

/*
 * Setting the handle field in Java_java_io_FileDescriptor_set for
 * standard handles stdIn, stdOut, stdErr
 */
#define SET_HANDLE(fd) \
if (fd == 0) { \
    return (jlong)GetStdHandle(STD_INPUT_HANDLE); \
} else if (fd == 1) { \
    return (jlong)GetStdHandle(STD_OUTPUT_HANDLE); \
} else if (fd == 2) { \
    return (jlong)GetStdHandle(STD_ERROR_HANDLE); \
} else { \
    return (jlong)-1; \
} \

/* INVALID_FILE_ATTRIBUTES is not defined in VC++6.0's header files but
 * in later release. Keep here just in case someone is still using VC++6.0
 */
#ifndef INVALID_FILE_ATTRIBUTES
#define INVALID_FILE_ATTRIBUTES ((DWORD)-1)
#endif
