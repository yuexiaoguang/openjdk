
#include "Utilities.h"
// Platform.java includes
#include "com_sun_media_sound_Platform.h"


/*
 * Class:     com_sun_media_sound_Platform
 * Method:    nIsBigEndian
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_com_sun_media_sound_Platform_nIsBigEndian(JNIEnv *env, jclass clss) {
    return UTIL_IsBigEndianPlatform();
}

/*
 * Class:     com_sun_media_sound_Platform
 * Method:    nIsSigned8
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_com_sun_media_sound_Platform_nIsSigned8(JNIEnv *env, jclass clss) {
#if ((X_ARCH == X_SPARC) || (X_ARCH == X_SPARCV9))
    return 1;
#else
    return 0;
#endif
}

/*
 * Class:     com_sun_media_sound_Platform
 * Method:    nGetExtraLibraries
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_sun_media_sound_Platform_nGetExtraLibraries(JNIEnv *env, jclass clss) {
    return (*env)->NewStringUTF(env, EXTRA_SOUND_JNI_LIBS);
}

/*
 * Class:     com_sun_media_sound_Platform
 * Method:    nGetLibraryForFeature
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_sun_media_sound_Platform_nGetLibraryForFeature
  (JNIEnv *env, jclass clazz, jint feature) {

// for every OS
#if X_PLATFORM == X_WINDOWS
    switch (feature) {
    case com_sun_media_sound_Platform_FEATURE_MIDIIO:
        return com_sun_media_sound_Platform_LIB_MAIN;
    case com_sun_media_sound_Platform_FEATURE_PORTS:
        return com_sun_media_sound_Platform_LIB_MAIN;
    case com_sun_media_sound_Platform_FEATURE_DIRECT_AUDIO:
        return com_sun_media_sound_Platform_LIB_DSOUND;
    }
#endif
#if (X_PLATFORM == X_SOLARIS)
    switch (feature) {
    case com_sun_media_sound_Platform_FEATURE_MIDIIO:
        return com_sun_media_sound_Platform_LIB_MAIN;
    case com_sun_media_sound_Platform_FEATURE_PORTS:
        return com_sun_media_sound_Platform_LIB_MAIN;
    case com_sun_media_sound_Platform_FEATURE_DIRECT_AUDIO:
        return com_sun_media_sound_Platform_LIB_MAIN;
    }
#endif
#if (X_PLATFORM == X_LINUX)
    switch (feature) {
    case com_sun_media_sound_Platform_FEATURE_MIDIIO:
        return com_sun_media_sound_Platform_LIB_ALSA;
    case com_sun_media_sound_Platform_FEATURE_PORTS:
        return com_sun_media_sound_Platform_LIB_ALSA;
    case com_sun_media_sound_Platform_FEATURE_DIRECT_AUDIO:
        return com_sun_media_sound_Platform_LIB_ALSA;
    }
#endif
#if (X_PLATFORM == X_MACOSX)
    switch (feature) {
    case com_sun_media_sound_Platform_FEATURE_MIDIIO:
        return com_sun_media_sound_Platform_LIB_MAIN;
    case com_sun_media_sound_Platform_FEATURE_PORTS:
        return com_sun_media_sound_Platform_LIB_MAIN;
    case com_sun_media_sound_Platform_FEATURE_DIRECT_AUDIO:
        return com_sun_media_sound_Platform_LIB_MAIN;
    }
#endif
#if (X_PLATFORM == X_BSD)
    switch (feature) {
    case com_sun_media_sound_Platform_FEATURE_MIDIIO:
       return com_sun_media_sound_Platform_LIB_MAIN;
#ifdef __FreeBSD__
    case com_sun_media_sound_Platform_FEATURE_PORTS:
       return com_sun_media_sound_Platform_LIB_ALSA;
    case com_sun_media_sound_Platform_FEATURE_DIRECT_AUDIO:
       return com_sun_media_sound_Platform_LIB_ALSA;
#else
    case com_sun_media_sound_Platform_FEATURE_PORTS:
       return com_sun_media_sound_Platform_LIB_MAIN;
    case com_sun_media_sound_Platform_FEATURE_DIRECT_AUDIO:
       // XXXBSD: When native Direct Audio support is ported change
       // this back to returning com_sun_media_sound_Platform_LIB_MAIN
       return 0;
#endif
    }
#endif
    return 0;
}
