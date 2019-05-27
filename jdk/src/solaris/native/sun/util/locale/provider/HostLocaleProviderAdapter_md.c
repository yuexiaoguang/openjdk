#include "sun_util_locale_provider_HostLocaleProviderAdapterImpl.h"
#include <gdefs.h>
#include <string.h>
#include <langinfo.h>
#include <locale.h>

#define BUFLEN 64

/*
 * Class:     sun_util_locale_provider_HostLocaleProviderAdapterImpl
 * Method:    getPattern
 * Signature: (IILjava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_sun_util_locale_provider_HostLocaleProviderAdapterImpl_getPattern
  (JNIEnv *env, jclass cls, jint dateStyle, jint timeStyle, jstring jlangtag) {

    // TEMPORARY!
    char locale[BUFLEN];
    char * pch;
    char * old;
    char * ret;
    const char *langtag = (*env)->GetStringUTFChars(env, jlangtag, JNI_FALSE);

    strcpy(locale, langtag);
    pch = strchr(locale, '-');
    if (pch != NULL) {
        *pch = '_';
    }
    pch = strchr(locale, '-');
    if (pch != NULL) {
        *pch = '\0';
    }
    strcat(locale, ".UTF-8");
    old = setlocale(LC_TIME, "");
    setlocale(LC_TIME, locale);

    if (dateStyle != (-1) && timeStyle != (-1)) {
        ret = nl_langinfo(D_T_FMT);
    } else if (dateStyle != (-1)) {
        ret = nl_langinfo(D_FMT);
    } else if (timeStyle != (-1)) {
        ret = nl_langinfo(T_FMT);
    } else {
        ret = "yyyy/MM/dd";
    }

    setlocale(LC_TIME, old);

    (*env)->ReleaseStringUTFChars(env, jlangtag, langtag);

    return (*env)->NewStringUTF(env, ret);
}
