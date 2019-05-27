
#ifndef _JAVASOFT_JNI_MD_H_
#define _JAVASOFT_JNI_MD_H_

#if defined(SOLARIS) || defined(LINUX) || defined(_ALLBSD_SOURCE)


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
#if defined(_LP64)
  typedef long jlong;
#else
  typedef long long jlong;
#endif

#else
  #define JNIEXPORT __declspec(dllexport)
  #define JNIIMPORT __declspec(dllimport)
  #define JNICALL __stdcall

  typedef int jint;
  typedef __int64 jlong;
#endif

typedef signed char jbyte;

#endif /* !_JAVASOFT_JNI_MD_H_ */
