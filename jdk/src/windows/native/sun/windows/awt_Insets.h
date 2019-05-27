/************************************************************************
 * AwtInsets class
 */

#ifndef AWT_INSETS_H
#define AWT_INSETS_H

#include <jni.h>
#include <jni_util.h>

class AwtInsets {
public:

    /* java.awt.Insets field ids */
    static jfieldID leftID;
    static jfieldID rightID;
    static jfieldID topID;
    static jfieldID bottomID;

};

#endif // AWT_INSETS_H
