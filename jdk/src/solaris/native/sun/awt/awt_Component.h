#include "jni_util.h"

/* fieldIDs for Component fields that may be accessed from C */
struct ComponentIDs {
    jfieldID x;
    jfieldID y;
    jfieldID width;
    jfieldID height;
    jfieldID peer;
    jfieldID background;
    jfieldID foreground;
    jfieldID isPacked;
    jfieldID graphicsConfig;
    jfieldID name;
    jfieldID isProxyActive;
    jfieldID appContext;
    jmethodID getParent;
    jmethodID getLocationOnScreen;
};
