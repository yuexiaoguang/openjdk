#include "jvm.h"
#include "sun_reflect_NativeConstructorAccessorImpl.h"
#include "sun_reflect_NativeMethodAccessorImpl.h"

JNIEXPORT jobject JNICALL Java_sun_reflect_NativeMethodAccessorImpl_invoke0
(JNIEnv *env, jclass unused, jobject m, jobject obj, jobjectArray args)
{
    return JVM_InvokeMethod(env, m, obj, args);
}

JNIEXPORT jobject JNICALL Java_sun_reflect_NativeConstructorAccessorImpl_newInstance0
(JNIEnv *env, jclass unused, jobject c, jobjectArray args)
{
    return JVM_NewInstanceFromConstructor(env, c, args);
}
