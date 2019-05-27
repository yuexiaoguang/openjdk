#include "jni.h"
#include "jni_util.h"
#include "jlong.h"

#include <unistd.h>
#include <errno.h>

#include "sun_nio_fs_UnixCopyFile.h"

#define RESTARTABLE(_cmd, _result) do { \
  do { \
    _result = _cmd; \
  } while((_result == -1) && (errno == EINTR)); \
} while(0)

static void throwUnixException(JNIEnv* env, int errnum) {
    jobject x = JNU_NewObjectByName(env, "sun/nio/fs/UnixException",
        "(I)V", errnum);
    if (x != NULL) {
        (*env)->Throw(env, x);
    }
}

/**
 * Transfer all bytes from src to dst via user-space buffers
 */
JNIEXPORT void JNICALL
Java_sun_nio_fs_UnixCopyFile_transfer
    (JNIEnv* env, jclass this, jint dst, jint src, jlong cancelAddress)
{
    char buf[8192];
    volatile jint* cancel = (jint*)jlong_to_ptr(cancelAddress);

    for (;;) {
        ssize_t n, pos, len;
        RESTARTABLE(read((int)src, &buf, sizeof(buf)), n);
        if (n <= 0) {
            if (n < 0)
                throwUnixException(env, errno);
            return;
        }
        if (cancel != NULL && *cancel != 0) {
            throwUnixException(env, ECANCELED);
            return;
        }
        pos = 0;
        len = n;
        do {
            char* bufp = buf;
            bufp += pos;
            RESTARTABLE(write((int)dst, bufp, len), n);
            if (n == -1) {
                throwUnixException(env, errno);
                return;
            }
            pos += n;
            len -= n;
        } while (len > 0);
    }
}
