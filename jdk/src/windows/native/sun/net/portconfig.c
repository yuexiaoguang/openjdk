#include <windows.h>
#include "jni.h"
#include "net_util.h"
#include "sun_net_PortConfig.h"

#ifdef __cplusplus
extern "C" {
#endif

struct portrange {
    int lower;
    int higher;
};

static int getPortRange(struct portrange *range)
{
    OSVERSIONINFO ver;
    ver.dwOSVersionInfoSize = sizeof(ver);
    GetVersionEx(&ver);

    /* Check for major version 5 or less = Windows XP/2003 or older */
    if (ver.dwMajorVersion <= 5) {
        LONG ret;
        HKEY hKey;
        range->lower = 1024;
        range->higher = 4999;

        /* check registry to see if upper limit was raised */
        ret = RegOpenKeyEx(HKEY_LOCAL_MACHINE,
                   "SYSTEM\\CurrentControlSet\\Services\\Tcpip\\Parameters",
                   0, KEY_READ, (PHKEY)&hKey
        );
        if (ret == ERROR_SUCCESS) {
            DWORD maxuserport;
            ULONG ulType;
            DWORD dwLen = sizeof(maxuserport);
            ret = RegQueryValueEx(hKey, "MaxUserPort",  NULL, &ulType,
                             (LPBYTE)&maxuserport, &dwLen);
            RegCloseKey(hKey);
            if (ret == ERROR_SUCCESS) {
                range->higher = maxuserport;
            }
        }
    } else {
        /* There doesn't seem to be an API to access this. "MaxUserPort"
          * is affected, but is not sufficient to determine.
         * so we just use the defaults, which are less likely to change
          */
        range->lower = 49152;
        range->higher = 65535;
    }
    return 0;
}

/*
 * Class:     sun_net_PortConfig
 * Method:    getLower0
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_sun_net_PortConfig_getLower0
  (JNIEnv *env, jclass clazz)
{
    struct portrange range;
    getPortRange(&range);
    return range.lower;
}

/*
 * Class:     sun_net_PortConfig
 * Method:    getUpper0
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_sun_net_PortConfig_getUpper0
  (JNIEnv *env, jclass clazz)
{
    struct portrange range;
    getPortRange(&range);
    return range.higher;
}
#ifdef __cplusplus
}
#endif
