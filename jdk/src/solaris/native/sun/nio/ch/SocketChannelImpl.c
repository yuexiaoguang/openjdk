#include <netdb.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>
#include <poll.h>

#if __linux__
#include <netinet/in.h>
#endif

#include "jni.h"
#include "jni_util.h"
#include "net_util.h"
#include "jvm.h"
#include "jlong.h"
#include "sun_nio_ch_SocketChannelImpl.h"
#include "nio_util.h"
#include "nio.h"


JNIEXPORT jint JNICALL
Java_sun_nio_ch_SocketChannelImpl_checkConnect(JNIEnv *env, jobject this,
                                               jobject fdo, jboolean block,
                                               jboolean ready)
{
    int error = 0;
    socklen_t n = sizeof(int);
    jint fd = fdval(env, fdo);
    int result = 0;
    struct pollfd poller;

    poller.revents = 1;
    if (!ready) {
        poller.fd = fd;
        poller.events = POLLOUT;
        poller.revents = 0;
        result = poll(&poller, 1, block ? -1 : 0);
        if (result < 0) {
            JNU_ThrowIOExceptionWithLastError(env, "Poll failed");
            return IOS_THROWN;
        }
        if (!block && (result == 0))
            return IOS_UNAVAILABLE;
    }

    if (poller.revents) {
        errno = 0;
        result = getsockopt(fd, SOL_SOCKET, SO_ERROR, &error, &n);
        if (result < 0) {
            handleSocketError(env, errno);
            return JNI_FALSE;
        } else if (error) {
            handleSocketError(env, error);
            return JNI_FALSE;
        }
        return 1;
    }
    return 0;
}

JNIEXPORT jint JNICALL
Java_sun_nio_ch_SocketChannelImpl_sendOutOfBandData(JNIEnv* env, jclass this,
                                                    jobject fdo, jbyte b)
{
    int n = send(fdval(env, fdo), (const void*)&b, 1, MSG_OOB);
    return convertReturnVal(env, n, JNI_FALSE);
}
