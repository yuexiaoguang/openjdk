
#include <jni.h>
#include "SoundDefs.h"
#include "Configure.h"          // put flags for debug msgs etc. here

// return 1 if this platform is big endian, or 0 for little endian
int UTIL_IsBigEndianPlatform();


// ERROR PRINTS
#ifdef USE_ERROR
#define ERROR0(string)                        { fprintf(stdout, (string)); fflush(stdout); }
#define ERROR1(string, p1)                    { fprintf(stdout, (string), (p1)); fflush(stdout); }
#define ERROR2(string, p1, p2)                { fprintf(stdout, (string), (p1), (p2)); fflush(stdout); }
#define ERROR3(string, p1, p2, p3)            { fprintf(stdout, (string), (p1), (p2), (p3)); fflush(stdout); }
#define ERROR4(string, p1, p2, p3, p4)        { fprintf(stdout, (string), (p1), (p2), (p3), (p4)); fflush(stdout); }
#else
#define ERROR0(string)
#define ERROR1(string, p1)
#define ERROR2(string, p1, p2)
#define ERROR3(string, p1, p2, p3)
#define ERROR4(string, p1, p2, p3, p4)
#endif


// TRACE PRINTS
#ifdef USE_TRACE
#define TRACE0(string)                        { fprintf(stdout, (string)); fflush(stdout); }
#define TRACE1(string, p1)                    { fprintf(stdout, (string), (p1)); fflush(stdout); }
#define TRACE2(string, p1, p2)                { fprintf(stdout, (string), (p1), (p2)); fflush(stdout); }
#define TRACE3(string, p1, p2, p3)            { fprintf(stdout, (string), (p1), (p2), (p3)); fflush(stdout); }
#define TRACE4(string, p1, p2, p3, p4)        { fprintf(stdout, (string), (p1), (p2), (p3), (p4)); fflush(stdout); }
#define TRACE5(string, p1, p2, p3, p4, p5)    { fprintf(stdout, (string), (p1), (p2), (p3), (p4), (p5)); fflush(stdout); }
#else
#define TRACE0(string)
#define TRACE1(string, p1)
#define TRACE2(string, p1, p2)
#define TRACE3(string, p1, p2, p3)
#define TRACE4(string, p1, p2, p3, p4)
#define TRACE5(string, p1, p2, p3, p4, p5)
#endif


// VERBOSE TRACE PRINTS
#ifdef USE_VERBOSE_TRACE
#define VTRACE0(string)                 fprintf(stdout, (string));
#define VTRACE1(string, p1)             fprintf(stdout, (string), (p1));
#define VTRACE2(string, p1, p2)         fprintf(stdout, (string), (p1), (p2));
#define VTRACE3(string, p1, p2, p3)     fprintf(stdout, (string), (p1), (p2), (p3));
#define VTRACE4(string, p1, p2, p3, p4) fprintf(stdout, (string), (p1), (p2), (p3), (p4));
#else
#define VTRACE0(string)
#define VTRACE1(string, p1)
#define VTRACE2(string, p1, p2)
#define VTRACE3(string, p1, p2, p3)
#define VTRACE4(string, p1, p2, p3, p4)
#endif


void ThrowJavaMessageException(JNIEnv *e, const char *exClass, const char *msg);
