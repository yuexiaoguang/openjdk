#ifndef _DEVICES_H_
#define _DEVICES_H_

#include "awt.h"
#include "awt_Toolkit.h"
#include "awt_Win32GraphicsDevice.h"

class AwtWin32GraphicsDevice;

class Devices {

public:
static Devices*                 GetInstance();
static BOOL                     UpdateInstance(JNIEnv *env);
       int                      GetNumDevices() { return numDevices; }
       AwtWin32GraphicsDevice*  GetDeviceReference(int index, BOOL adjust = TRUE);
       AwtWin32GraphicsDevice*  GetDevice(int index, BOOL adjust = TRUE);
       int                      Release();
       AwtWin32GraphicsDevice** GetRawArray();

       class InstanceAccess {
       public:
           INLINE   InstanceAccess() { devices = Devices::GetInstance(); }
           INLINE  ~InstanceAccess() { devices->Release(); }
           Devices* operator->()     { return devices; }
        private:
           Devices* devices;
           // prevent bad things like copying or getting address of
           InstanceAccess& operator=(const InstanceAccess&);
           InstanceAccess* operator&();
       };
friend class InstanceAccess;

private:
                                Devices(int numElements);
       void                     AddReference();

       AwtWin32GraphicsDevice** devices;
       int                      refCount;
       int                      numDevices;

static Devices*                 theInstance;
static CriticalSection          arrayLock;

};

// Some helper functions (from awt_MMStub.h/cpp)

BOOL WINAPI MonitorBounds (HMONITOR, RECT*);

#endif _DEVICES_H_
