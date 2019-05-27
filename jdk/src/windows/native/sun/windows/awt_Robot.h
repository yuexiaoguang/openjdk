#ifndef AWT_ROBOT_H
#define AWT_ROBOT_H

#include "awt_Toolkit.h"
#include "awt_Object.h"
#include "sun_awt_windows_WRobotPeer.h"
#include "jlong.h"

class AwtRobot : public AwtObject
{
    public:
        AwtRobot( jobject peer );
        virtual ~AwtRobot();

        void MouseMove( jint x, jint y);
        void MousePress( jint buttonMask );
        void MouseRelease( jint buttonMask );

        void MouseWheel(jint wheelAmt);
        jint getNumberOfButtons();

        void GetRGBPixels(jint x, jint y, jint width, jint height, jintArray pixelArray);

        void KeyPress( jint key );
        void KeyRelease( jint key );
        static AwtRobot * GetRobot( jobject self );

    private:
        void DoKeyEvent( jint jkey, DWORD dwFlags );
        static jint WinToJavaPixel(USHORT r, USHORT g, USHORT b);
};

#endif // AWT_ROBOT_H
