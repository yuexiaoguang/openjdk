#ifndef SHARE_VM_PRIMS_JVMTIGETLOADEDCLASSES_HPP
#define SHARE_VM_PRIMS_JVMTIGETLOADEDCLASSES_HPP

#include "jvmtifiles/jvmtiEnv.hpp"

class JvmtiGetLoadedClasses : AllStatic {
public:
  static jvmtiError getLoadedClasses(JvmtiEnv *env, jint* classCountPtr, jclass** classesPtr);
  static jvmtiError getClassLoaderClasses(JvmtiEnv *env, jobject initiatingLoader,
                                          jint* classCountPtr, jclass** classesPtr);
};

#endif // SHARE_VM_PRIMS_JVMTIGETLOADEDCLASSES_HPP
