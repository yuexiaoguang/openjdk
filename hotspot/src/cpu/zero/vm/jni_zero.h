
// Note: please do not change these without also changing jni_md.h in the JDK
// repository
#ifndef __has_attribute
  #define __has_attribute(x) 0
#endif
#if (defined(__GNUC__) && ((__GNUC__ > 4) || (__GNUC__ == 4) && (__GNUC_MINOR__ > 2))) || __has_attribute(visibility)
  #define JNIEXPORT     __attribute__((visibility("default")))
  #define JNIIMPORT     __attribute__((visibility("default")))
#else
  #define JNIEXPORT
  #define JNIIMPORT
#endif
#define JNICALL

typedef int jint;
typedef signed char jbyte;

#ifdef _LP64
typedef long jlong;
#else
typedef long long jlong;
#endif
