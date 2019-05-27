
#include "jvm.h"
#include "jni.h"
#include "java_lang_Compiler.h"

static JNINativeMethod methods[] = {
    {"compileClass",
     "(Ljava/lang/Class;)Z",
     (void *)&JVM_CompileClass},
    {"compileClasses",
     "(Ljava/lang/String;)Z",
     (void *)&JVM_CompileClasses},
    {"command",
     "(Ljava/lang/Object;)Ljava/lang/Object;",
     (void *)&JVM_CompilerCommand},
    {"enable",
     "()V",
     (void *)&JVM_EnableCompiler},
    {"disable",
     "()V",
     (void *)&JVM_DisableCompiler}
};

JNIEXPORT void JNICALL
Java_java_lang_Compiler_registerNatives(JNIEnv *env, jclass compCls)
{
    (*env)->RegisterNatives(env, compCls, methods,
                            sizeof methods / sizeof methods[0]);
}
