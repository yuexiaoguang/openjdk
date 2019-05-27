#include "awt.h"
#include "ComCtl32Util.h"

ComCtl32Util::ComCtl32Util() {
    m_bToolTipControlInitialized = FALSE;
}

ComCtl32Util::~ComCtl32Util() {
}

void ComCtl32Util::InitLibraries() {
    INITCOMMONCONTROLSEX iccex;
    memset(&iccex, 0, sizeof(INITCOMMONCONTROLSEX));
    iccex.dwSize = sizeof(INITCOMMONCONTROLSEX);
    iccex.dwICC = ICC_TAB_CLASSES;
    m_bToolTipControlInitialized = ::InitCommonControlsEx(&iccex);
}

WNDPROC ComCtl32Util::SubclassHWND(HWND hwnd, WNDPROC _WindowProc) {
    if (IS_WINXP) {
        const SUBCLASSPROC p = SharedWindowProc; // let compiler check type of SharedWindowProc
        ::SetWindowSubclass(hwnd, p, (UINT_PTR)_WindowProc, NULL); // _WindowProc is used as subclass ID
        return NULL;
    } else {
        return (WNDPROC)::SetWindowLongPtr(hwnd, GWLP_WNDPROC, (LONG_PTR)_WindowProc);
    }
}

void ComCtl32Util::UnsubclassHWND(HWND hwnd, WNDPROC _WindowProc, WNDPROC _DefWindowProc) {
    if (IS_WINXP) {
        const SUBCLASSPROC p = SharedWindowProc; // let compiler check type of SharedWindowProc
        ::RemoveWindowSubclass(hwnd, p, (UINT_PTR)_WindowProc); // _WindowProc is used as subclass ID
    } else {
        ::SetWindowLongPtr(hwnd, GWLP_WNDPROC, (LONG_PTR)_DefWindowProc);
    }
}

LRESULT ComCtl32Util::DefWindowProc(WNDPROC _DefWindowProc, HWND hwnd, UINT msg, WPARAM wParam, LPARAM lParam) {
    if (IS_WINXP) {
        return ::DefSubclassProc(hwnd, msg, wParam, lParam);
    } else if (_DefWindowProc != NULL) {
        return ::CallWindowProc(_DefWindowProc, hwnd, msg, wParam, lParam);
    } else {
        return ::DefWindowProc(hwnd, msg, wParam, lParam);
    }
}

LRESULT ComCtl32Util::SharedWindowProc(HWND hwnd, UINT msg,
                                       WPARAM wParam, LPARAM lParam,
                                       UINT_PTR uIdSubclass, DWORD_PTR dwRefData)
{
    TRY;

    WNDPROC _WindowProc = (WNDPROC)uIdSubclass;
    return ::CallWindowProc(_WindowProc, hwnd, msg, wParam, lParam);

    CATCH_BAD_ALLOC_RET(0);
}
