#include "jvm.h"
#include "sun_reflect_Reflection.h"

JNIEXPORT jclass JNICALL Java_sun_reflect_Reflection_getCallerClass__
(JNIEnv *env, jclass unused)
{
    return JVM_GetCallerClass(env, JVM_CALLER_DEPTH);
}

JNIEXPORT jclass JNICALL Java_sun_reflect_Reflection_getCallerClass__I
(JNIEnv *env, jclass unused, jint depth)
{
    return JVM_GetCallerClass(env, depth);
}

JNIEXPORT jint JNICALL Java_sun_reflect_Reflection_getClassAccessFlags
(JNIEnv *env, jclass unused, jclass cls)
{
    return JVM_GetClassAccessFlags(env, cls);
}
