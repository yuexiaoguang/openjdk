#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "jlong.h"
#include "java_nio_MappedByteBuffer.h"
#include <stdlib.h>

JNIEXPORT jboolean JNICALL
Java_java_nio_MappedByteBuffer_isLoaded0(JNIEnv *env, jobject obj, jlong address,
                                         jlong len, jint numPages)
{
    jboolean loaded = JNI_FALSE;
    /* Information not available?
    MEMORY_BASIC_INFORMATION info;
    void *a = (void *) jlong_to_ptr(address);
    int result = VirtualQuery(a, &info, (DWORD)len);
    */
    return loaded;
}

JNIEXPORT void JNICALL
Java_java_nio_MappedByteBuffer_load0(JNIEnv *env, jobject obj, jlong address,
                                     jlong len)
{
    // no madvise available
}

JNIEXPORT void JNICALL
Java_java_nio_MappedByteBuffer_force0(JNIEnv *env, jobject obj, jobject fdo,
                                      jlong address, jlong len)
{
    void *a = (void *) jlong_to_ptr(address);
    BOOL result;
    int retry;

    /*
     * FlushViewOfFile can fail with ERROR_LOCK_VIOLATION if the memory
     * system is writing dirty pages to disk. As there is no way to
     * synchronize the flushing then we retry a limited number of times.
     */
    retry = 0;
    do {
        result = FlushViewOfFile(a, (DWORD)len);
        if ((result != 0) || (GetLastError() != ERROR_LOCK_VIOLATION))
            break;
        retry++;
    } while (retry < 3);

    /**
     * FlushViewOfFile only initiates the writing of dirty pages to disk
     * so we have to call FlushFileBuffers to and ensure they are written.
     */
    if (result != 0) {
        // by right, the jfieldID initialization should be in a static
        // initializer but we do it here instead to avoiding needing to
        // load nio.dll during startup.
        static jfieldID handle_fdID;
        HANDLE h;
        if (handle_fdID == NULL) {
            jclass clazz = (*env)->FindClass(env, "java/io/FileDescriptor");
            if (clazz == NULL)
                return; // exception thrown
            handle_fdID = (*env)->GetFieldID(env, clazz, "handle", "J");
        }
        h = jlong_to_ptr((*env)->GetLongField(env, fdo, handle_fdID));
        result = FlushFileBuffers(h);
        if (result == 0 && GetLastError() == ERROR_ACCESS_DENIED) {
            // read-only mapping
            result = 1;
        }
    }

    if (result == 0) {
        JNU_ThrowIOExceptionWithLastError(env, "Flush failed");
    }
}
