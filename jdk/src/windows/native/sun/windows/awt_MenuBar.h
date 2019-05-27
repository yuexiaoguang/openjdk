#ifndef AWT_MENUBAR_H
#define AWT_MENUBAR_H

#include "awt.h"
#include "awt_Menu.h"
#include <java_awt_MenuBar.h>
#include <sun_awt_windows_WMenuBarPeer.h>
#include <sun_awt_windows_WFramePeer.h>


class AwtFrame;


/************************************************************************
 * AwtMenuBar class
 */

class AwtMenuBar : public AwtMenu {
public:
    // id's for methods executed on toolkit thread
    enum MenuExecIds {
        MENUBAR_DELITEM = MENU_LAST+1
    };

    /* java.awt.MenuBar method ids */
    static jmethodID getMenuCountMID;
    static jmethodID getMenuMID;

    AwtMenuBar();
    virtual ~AwtMenuBar();

    virtual void Dispose();

    virtual LPCTSTR GetClassName();

    /* Create a new AwtMenuBar.  This must be run on the main thread. */
    static AwtMenuBar* Create(jobject self, jobject framePeer);

    virtual AwtMenuBar* GetMenuBar() { return this; }
    INLINE AwtFrame* GetFrame() { return m_frame; }

    virtual HWND GetOwnerHWnd();
    virtual void RedrawMenuBar();

    AwtMenuItem* GetItem(jobject target, long index);
    int CountItem(jobject menuBar);

    void SendDrawItem(AwtMenuItem* awtMenuItem,
                      DRAWITEMSTRUCT& drawInfo);
    void SendMeasureItem(AwtMenuItem* awtMenuItem,
                         HDC hDC, MEASUREITEMSTRUCT& measureInfo);
    void DrawItem(DRAWITEMSTRUCT& drawInfo);
    void MeasureItem(HDC hDC, MEASUREITEMSTRUCT& measureInfo);

    void AddItem(AwtMenuItem* item);
    void DeleteItem(UINT index);

    virtual LRESULT WinThreadExecProc(ExecuteArgs * args);

    // called on Toolkit thread
    static void _AddMenu(void *param);
protected:
    AwtFrame* m_frame;
};

#endif /* AWT_MENUBAR_H */
