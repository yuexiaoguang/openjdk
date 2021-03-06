#ifndef SHARE_VM_PRIMS_WBTESTMETHODS_PARSERTESTS_H
#define SHARE_VM_PRIMS_WBTESTMETHODS_PARSERTESTS_H

#include "prims/jni.h"
#include "prims/whitebox.hpp"

WB_METHOD_DECLARE(jobjectArray) WB_ParseCommandLine(JNIEnv* env, jobject o, jstring args, jobjectArray arguments);

#endif //SHARE_VM_PRIMS_WBTESTMETHODS_PARSERTESTS_H
