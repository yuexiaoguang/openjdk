/************************************************************************
 * AwtKeyEvent class
 */

#ifndef AWT_KEYEVENT_H
#define AWT_KEYEVENT_H

#include <jni.h>
#include <jni_util.h>

class AwtKeyEvent {
public:

    /* java.awt.KeyEvent field ids */
    static jfieldID keyCodeID;
    static jfieldID keyCharID;
    static jfieldID rawCodeID;
    static jfieldID primaryLevelUnicodeID;
    static jfieldID scancodeID;
    static jfieldID extendedKeyCodeID;
};

#endif // AWT_KEYEVENT_H
