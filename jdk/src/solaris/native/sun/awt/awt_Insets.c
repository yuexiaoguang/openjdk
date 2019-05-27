#include "java_awt_Insets.h"
#include "jni_util.h"

#include "awt_Insets.h"

struct InsetsIDs insetsIDs;


JNIEXPORT void JNICALL
Java_java_awt_Insets_initIDs(JNIEnv *env, jclass cls)
{
    insetsIDs.top = (*env)->GetFieldID(env, cls, "top", "I");
    insetsIDs.bottom = (*env)->GetFieldID(env, cls, "bottom", "I");
    insetsIDs.left = (*env)->GetFieldID(env, cls, "left", "I");
    insetsIDs.right = (*env)->GetFieldID(env, cls, "right", "I");
}
