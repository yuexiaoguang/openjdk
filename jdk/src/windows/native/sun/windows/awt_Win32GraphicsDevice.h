#ifndef AWT_WIN32GRAPHICSDEVICE_H
#define AWT_WIN32GRAPHICSDEVICE_H

#include "awt.h"
extern "C" {
    #include "img_globals.h"
} // extern "C"
#include "colordata.h"
#include "awt_Palette.h"
#include "Devices.h"

class AwtPalette;
class Devices;

class AwtWin32GraphicsDevice {
public:
                            AwtWin32GraphicsDevice(int screen, HMONITOR mhnd, Devices *arr);
                            ~AwtWin32GraphicsDevice();
    void                    UpdateDeviceColorState();
    void                    SetGrayness(int grayValue);
    int                     GetGrayness() { return colorData->grayscale; }
    HDC                     GetDC();
    void                    ReleaseDC(HDC hDC);
    jobject                 GetColorModel(JNIEnv *env,
                                          jboolean useDeviceSettings);
    void                    Initialize();
    void                    UpdateDynamicColorModel();
    BOOL                    UpdateSystemPalette();
    unsigned int            *GetSystemPaletteEntries();
    unsigned char           *GetSystemInverseLUT();
    void                    SetJavaDevice(JNIEnv *env, jobject objPtr);
    HPALETTE                SelectPalette(HDC hDC);
    void                    RealizePalette(HDC hDC);
    HPALETTE                GetPalette();
    ColorData               *GetColorData() { return cData; }
    int                     GetBitDepth() { return colorData->bitsperpixel; }
    HMONITOR                GetMonitor() { return monitor; }
    LPMONITORINFO           GetMonitorInfo() { return pMonitorInfo; }
    jobject                 GetJavaDevice() { return javaDevice; }
    int                     GetDeviceIndex() { return screen; }
    void                    Release();
    void                    DisableOffscreenAcceleration();
    void                    Invalidate(JNIEnv *env);

    static int              DeviceIndexForWindow(HWND hWnd);
    static jobject          GetColorModel(JNIEnv *env, jboolean dynamic,
                                          int deviceIndex);
    static HPALETTE         SelectPalette(HDC hDC, int deviceIndex);
    static void             RealizePalette(HDC hDC, int deviceIndex);
    static ColorData        *GetColorData(int deviceIndex);
    static int              GetGrayness(int deviceIndex);
    static void             UpdateDynamicColorModel(int deviceIndex);
    static BOOL             UpdateSystemPalette(int deviceIndex);
    static HPALETTE         GetPalette(int deviceIndex);
    static HMONITOR         GetMonitor(int deviceIndex);
    static LPMONITORINFO    GetMonitorInfo(int deviceIndex);
    static void             ResetAllMonitorInfo();
    static BOOL             IsPrimaryPalettized() { return primaryPalettized; }
    static int              GetDefaultDeviceIndex() { return primaryIndex; }
    static void             DisableOffscreenAccelerationForDevice(HMONITOR hMonitor);
    static HDC              GetDCFromScreen(int screen);
    static int              GetScreenFromHMONITOR(HMONITOR mon);

    static int              primaryIndex;
    static BOOL             primaryPalettized;
    static jclass           indexCMClass;
    static jclass           wToolkitClass;
    static jfieldID         dynamicColorModelID;
    static jfieldID         indexCMrgbID;
    static jfieldID         indexCMcacheID;
    static jmethodID        paletteChangedMID;

private:
    static BOOL             AreSameMonitors(HMONITOR mon1, HMONITOR mon2);
    ImgColorData            *colorData;
    AwtPalette              *palette;
    ColorData               *cData;     // Could be static, but may sometime
                                        // have per-device info in this structure
    BITMAPINFO              *gpBitmapInfo;
    int                     screen;
    HMONITOR                monitor;
    LPMONITORINFO           pMonitorInfo;
    jobject                 javaDevice;
    Devices                 *devicesArray;

    static HDC              MakeDCFromMonitor(HMONITOR);
};

#endif AWT_WIN32GRAPHICSDEVICE_H
