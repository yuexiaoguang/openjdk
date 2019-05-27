#include <windows.h>
#include "sun_io_Win32ErrorMode.h"

/*
 * Class:     sun/io/Win32ErrorMode
 * Method:    setErrorMode
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_sun_io_Win32ErrorMode_setErrorMode
  (JNIEnv *env, jclass thisClass, jlong mode)
{
    return (jlong)SetErrorMode((UINT)mode);
}
