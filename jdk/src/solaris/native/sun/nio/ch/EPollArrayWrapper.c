#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "jlong.h"

#include "sun_nio_ch_EPollArrayWrapper.h"

#include <unistd.h>
#include <sys/time.h>
#include <sys/epoll.h>

#define RESTARTABLE(_cmd, _result) do { \
  do { \
    _result = _cmd; \
  } while((_result == -1) && (errno == EINTR)); \
} while(0)


static int
iepoll(int epfd, struct epoll_event *events, int numfds, jlong timeout)
{
    jlong start, now;
    int remaining = timeout;
    struct timeval t;
    int diff;

    gettimeofday(&t, NULL);
    start = t.tv_sec * 1000 + t.tv_usec / 1000;

    for (;;) {
        int res = epoll_wait(epfd, events, numfds, timeout);
        if (res < 0 && errno == EINTR) {
            if (remaining >= 0) {
                gettimeofday(&t, NULL);
                now = t.tv_sec * 1000 + t.tv_usec / 1000;
                diff = now - start;
                remaining -= diff;
                if (diff < 0 || remaining <= 0) {
                    return 0;
                }
                start = now;
            }
        } else {
            return res;
        }
    }
}

JNIEXPORT void JNICALL
Java_sun_nio_ch_EPollArrayWrapper_init(JNIEnv *env, jclass this)
{
}

JNIEXPORT jint JNICALL
Java_sun_nio_ch_EPollArrayWrapper_epollCreate(JNIEnv *env, jobject this)
{
    /*
     * epoll_create expects a size as a hint to the kernel about how to
     * dimension internal structures. We can't predict the size in advance.
     */
    int epfd = epoll_create(256);
    if (epfd < 0) {
       JNU_ThrowIOExceptionWithLastError(env, "epoll_create failed");
    }
    return epfd;
}

JNIEXPORT jint JNICALL
Java_sun_nio_ch_EPollArrayWrapper_sizeofEPollEvent(JNIEnv* env, jclass this)
{
    return sizeof(struct epoll_event);
}

JNIEXPORT jint JNICALL
Java_sun_nio_ch_EPollArrayWrapper_offsetofData(JNIEnv* env, jclass this)
{
    return offsetof(struct epoll_event, data);
}

JNIEXPORT void JNICALL
Java_sun_nio_ch_EPollArrayWrapper_epollCtl(JNIEnv *env, jobject this, jint epfd,
                                           jint opcode, jint fd, jint events)
{
    struct epoll_event event;
    int res;

    event.events = events;
    event.data.fd = fd;

    RESTARTABLE(epoll_ctl(epfd, (int)opcode, (int)fd, &event), res);

    /*
     * A channel may be registered with several Selectors. When each Selector
     * is polled a EPOLL_CTL_DEL op will be inserted into its pending update
     * list to remove the file descriptor from epoll. The "last" Selector will
     * close the file descriptor which automatically unregisters it from each
     * epoll descriptor. To avoid costly synchronization between Selectors we
     * allow pending updates to be processed, ignoring errors. The errors are
     * harmless as the last update for the file descriptor is guaranteed to
     * be EPOLL_CTL_DEL.
     */
    if (res < 0 && errno != EBADF && errno != ENOENT && errno != EPERM) {
        JNU_ThrowIOExceptionWithLastError(env, "epoll_ctl failed");
    }
}

JNIEXPORT jint JNICALL
Java_sun_nio_ch_EPollArrayWrapper_epollWait(JNIEnv *env, jobject this,
                                            jlong address, jint numfds,
                                            jlong timeout, jint epfd)
{
    struct epoll_event *events = jlong_to_ptr(address);
    int res;

    if (timeout <= 0) {           /* Indefinite or no wait */
        RESTARTABLE(epoll_wait(epfd, events, numfds, timeout), res);
    } else {                      /* Bounded wait; bounded restarts */
        res = iepoll(epfd, events, numfds, timeout);
    }

    if (res < 0) {
        JNU_ThrowIOExceptionWithLastError(env, "epoll_wait failed");
    }
    return res;
}

JNIEXPORT void JNICALL
Java_sun_nio_ch_EPollArrayWrapper_interrupt(JNIEnv *env, jobject this, jint fd)
{
    int fakebuf[1];
    fakebuf[0] = 1;
    if (write(fd, fakebuf, 1) < 0) {
        JNU_ThrowIOExceptionWithLastError(env,"write to interrupt fd failed");
    }
}
