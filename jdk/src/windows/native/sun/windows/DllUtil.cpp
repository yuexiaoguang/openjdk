#include "DllUtil.h"
#include <jdk_util.h>

// Disable warning about using this in the initializer list.
#pragma warning( disable : 4355)

DllUtil::~DllUtil()
{
    if (module != NULL) {
        ::FreeLibrary(module);
        module = NULL;
    }
}

HMODULE DllUtil::GetModule()
{
    if (!module) {
        module = JDK_LoadSystemLibrary(name);
    }
    return module;
}

FARPROC DllUtil::GetProcAddress(LPCSTR name)
{
    if (GetModule()) {
        return ::GetProcAddress(GetModule(), name);
    }
    throw LibraryUnavailableException();
}

DwmAPI & DwmAPI::GetInstance()
{
    static DwmAPI dll;
    return dll;
}

DwmAPI::DwmAPI() :
    DllUtil("DWMAPI.DLL"),
    DwmIsCompositionEnabledFunction((DllUtil*)this, "DwmIsCompositionEnabled"),
    DwmGetWindowAttributeFunction((DllUtil*)this, "DwmGetWindowAttribute")
{
}

HRESULT DwmAPI::DwmIsCompositionEnabled(BOOL * pfEnabled)
{
    if (GetInstance().DwmIsCompositionEnabledFunction()) {
        return GetInstance().DwmIsCompositionEnabledFunction()(pfEnabled);
    }
    throw FunctionUnavailableException();
}

HRESULT DwmAPI::DwmGetWindowAttribute(HWND hwnd, DWORD dwAttribute,
        PVOID pvAttribute, DWORD cbAttribute)
{
    if (GetInstance().DwmGetWindowAttributeFunction()) {
        return GetInstance().DwmGetWindowAttributeFunction()(hwnd, dwAttribute,
                pvAttribute, cbAttribute);
    }
    throw FunctionUnavailableException();
}


