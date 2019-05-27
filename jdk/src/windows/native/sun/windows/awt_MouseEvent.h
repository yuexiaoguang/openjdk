/************************************************************************
 * AwtMouseEvent class
 */

#ifndef AWT_MOUSEEVENT_H
#define AWT_MOUSEEVENT_H

#include <jni.h>
#include <jni_util.h>

class AwtMouseEvent {
public:

    /* java.awt.MouseEvent field ids */
    static jfieldID xID;
    static jfieldID yID;
    static jfieldID buttonID;

};

#endif // AWT_MOUSEEVENT_H
