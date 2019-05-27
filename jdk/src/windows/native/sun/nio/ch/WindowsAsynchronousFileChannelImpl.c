#include <windows.h>

#include "jni.h"
#include "jni_util.h"
#include "jlong.h"
#include "nio.h"
#include "nio_util.h"

#include "sun_nio_ch_WindowsAsynchronousFileChannelImpl.h"


JNIEXPORT jint JNICALL
Java_sun_nio_ch_WindowsAsynchronousFileChannelImpl_readFile(JNIEnv* env, jclass this,
    jlong handle, jlong address, jint len, jlong offset, jlong ov)
{
    BOOL res;

    OVERLAPPED* lpOverlapped = (OVERLAPPED*)jlong_to_ptr(ov);
    lpOverlapped->Offset = (DWORD)offset;
    lpOverlapped->OffsetHigh = (DWORD)((long)(offset >> 32));
    lpOverlapped->hEvent = NULL;

    res = ReadFile((HANDLE) jlong_to_ptr(handle),
                   (LPVOID) jlong_to_ptr(address),
                   (DWORD)len,
                   NULL,
                   lpOverlapped);

    if (res == 0) {
        int error = GetLastError();
        if (error == ERROR_IO_PENDING)
            return IOS_UNAVAILABLE;
        if (error == ERROR_HANDLE_EOF)
            return IOS_EOF;
        JNU_ThrowIOExceptionWithLastError(env, "ReadFile failed");
        return IOS_THROWN;
    }

    return IOS_UNAVAILABLE;
}

JNIEXPORT jint JNICALL
Java_sun_nio_ch_WindowsAsynchronousFileChannelImpl_writeFile(JNIEnv* env, jclass this,
    jlong handle, jlong address, jint len, jlong offset, jlong ov)
{
    BOOL res;

    OVERLAPPED* lpOverlapped = (OVERLAPPED*)jlong_to_ptr(ov);
    lpOverlapped->Offset = (DWORD)offset;
    lpOverlapped->OffsetHigh = (DWORD)((long)(offset >> 32));
    lpOverlapped->hEvent = NULL;

    res = WriteFile((HANDLE)jlong_to_ptr(handle),
                   (LPVOID) jlong_to_ptr(address),
                   (DWORD)len,
                   NULL,
                   lpOverlapped);

    if (res == 0) {
        int error = GetLastError();
        if (error == ERROR_IO_PENDING)
            return IOS_UNAVAILABLE;
        JNU_ThrowIOExceptionWithLastError(env, "WriteFile failed");
        return IOS_THROWN;
    }

    return IOS_UNAVAILABLE;
}

JNIEXPORT jint JNICALL
Java_sun_nio_ch_WindowsAsynchronousFileChannelImpl_lockFile(JNIEnv *env, jobject this, jlong handle,
                                                            jlong pos, jlong size, jboolean shared, jlong ov)
{
    BOOL res;
    HANDLE h = jlong_to_ptr(handle);
    DWORD lowPos = (DWORD)pos;
    long highPos = (long)(pos >> 32);
    DWORD lowNumBytes = (DWORD)size;
    DWORD highNumBytes = (DWORD)(size >> 32);
    DWORD flags = (shared == JNI_TRUE) ? 0 : LOCKFILE_EXCLUSIVE_LOCK;
    OVERLAPPED* lpOverlapped = (OVERLAPPED*)jlong_to_ptr(ov);

    lpOverlapped->Offset = lowPos;
    lpOverlapped->OffsetHigh = highPos;
    lpOverlapped->hEvent = NULL;

    res = LockFileEx(h, flags, 0, lowNumBytes, highNumBytes, lpOverlapped);
    if (res == 0) {
        int error = GetLastError();
        if (error == ERROR_IO_PENDING) {
            return IOS_UNAVAILABLE;
        }
        JNU_ThrowIOExceptionWithLastError(env, "WriteFile failed");
        return IOS_THROWN;
    }
    return 0;
}

JNIEXPORT void JNICALL
Java_sun_nio_ch_WindowsAsynchronousFileChannelImpl_close0(JNIEnv* env, jclass this,
    jlong handle)
{
    HANDLE h = (HANDLE)jlong_to_ptr(handle);
    CloseHandle(h);
}
