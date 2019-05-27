#include "awt_Component.h"

#include <commctrl.h>

#ifndef _COMCTL32UTIL_H
#define _COMCTL32UTIL_H

class ComCtl32Util
{
    public:
        static ComCtl32Util &GetInstance() {
            static ComCtl32Util theInstance;
            return theInstance;
        }

        void InitLibraries();

        INLINE BOOL IsToolTipControlInitialized() {
            return m_bToolTipControlInitialized;
        }

        WNDPROC SubclassHWND(HWND hwnd, WNDPROC _WindowProc);
        // DefWindowProc is the same as returned from SubclassHWND
        void UnsubclassHWND(HWND hwnd, WNDPROC _WindowProc, WNDPROC _DefWindowProc);
        // DefWindowProc is the same as returned from SubclassHWND or NULL
        LRESULT DefWindowProc(WNDPROC _DefWindowProc, HWND hwnd, UINT msg, WPARAM wParam, LPARAM lParam);

    private:
        ComCtl32Util();
        ~ComCtl32Util();

        BOOL m_bToolTipControlInitialized;

        // comctl32.dll version 6 window proc
        static LRESULT CALLBACK SharedWindowProc(HWND hwnd, UINT message,
                                                 WPARAM wParam, LPARAM lParam,
                                                 UINT_PTR uIdSubclass, DWORD_PTR dwRefData);
};

#endif // _COMCTL32UTIL_H
