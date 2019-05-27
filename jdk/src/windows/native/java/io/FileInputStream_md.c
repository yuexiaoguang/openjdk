#include "jni.h"
#include "jni_util.h"
#include "jvm.h"

#include "io_util.h"
#include "io_util_md.h"

#include "java_io_FileInputStream.h"

extern jfieldID fis_fd; /* id for jobject 'fd' in java.io.FileInputStream */

/*********************************************************************
 * Platform specific implementation of input stream native methods
 */

JNIEXPORT void JNICALL
Java_java_io_FileInputStream_close0(JNIEnv *env, jobject this) {
    handleClose(env, this, fis_fd);
}
