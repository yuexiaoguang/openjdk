#ifndef DLLUTIL_H
#define DLLUTIL_H

#include <tchar.h>
#include <windows.h>

/**
 * Utility class to handle dynamically loadable libraries.
 *
 * NOTE: THIS CLASS IS NOT THREAD-SAFE!
 */
class DllUtil {
    public:
        class Exception {};
        class LibraryUnavailableException : public Exception {};
        class FunctionUnavailableException : public Exception {};

        FARPROC GetProcAddress(LPCSTR name);

    protected:
        DllUtil(const char * name) : name(name), module(NULL) {}
        virtual ~DllUtil();

        HMODULE GetModule();

        template <class FunctionType> class Function {
            public:
                Function(DllUtil * dll, LPCSTR name) :
                    dll(dll), name(name), function(NULL) {}

                inline FunctionType operator () () {
                    if (!function) {
                        function = (FunctionType)dll->GetProcAddress(name);
                    }
                    return function;
                }

            private:
                DllUtil * const dll;
                LPCSTR name;

                FunctionType function;
        };

    private:
        const char * const name;
        HMODULE module;
};

class DwmAPI : public DllUtil {
    public:
        // See DWMWINDOWATTRIBUTE enum in dwmapi.h
        static const DWORD DWMWA_EXTENDED_FRAME_BOUNDS = 9;

        static HRESULT DwmIsCompositionEnabled(BOOL * pfEnabled);
        static HRESULT DwmGetWindowAttribute(HWND hwnd, DWORD dwAttribute,
                PVOID pvAttribute, DWORD cbAttribute);

    private:
        static DwmAPI & GetInstance();
        DwmAPI();

        typedef HRESULT (WINAPI *DwmIsCompositionEnabledType)(BOOL*);
        Function<DwmIsCompositionEnabledType> DwmIsCompositionEnabledFunction;

        typedef HRESULT (WINAPI *DwmGetWindowAttributeType)(HWND hwnd, DWORD dwAttribute,
                PVOID pvAttribute, DWORD cbAttribute);
        Function<DwmGetWindowAttributeType> DwmGetWindowAttributeFunction;
};

#endif // DLLUTIL_H

