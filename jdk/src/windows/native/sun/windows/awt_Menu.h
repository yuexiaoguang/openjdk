#ifndef AWT_MENU_H
#define AWT_MENU_H

#include "awt_MenuItem.h"

#include <java_awt_MenuItem.h>
#include <sun_awt_windows_WMenuItemPeer.h>
#include <java_awt_Menu.h>
#include <sun_awt_windows_WMenuPeer.h>

class AwtMenuBar;


/************************************************************************
 * AwtMenu class
 */

class AwtMenu : public AwtMenuItem {
public:
    // id's for methods executed on toolkit thread
    enum {
        MENU_ADDSEPARATOR = MENUITEM_LAST+1,
        MENU_DELITEM,
        MENU_LAST
    };

    /* method ids for java.awt.Menu */
    static jmethodID countItemsMID;
    static jmethodID getItemMID;

    AwtMenu();
    virtual ~AwtMenu();

    virtual void Dispose();

    virtual LPCTSTR GetClassName();

    /* Create a new AwtMenu.  This must be run on the main thread. */
    static AwtMenu* Create(jobject self, AwtMenu* parentMenu);

    INLINE HMENU GetHMenu() { return m_hMenu; }
    INLINE void SetHMenu(HMENU hMenu) {
        m_hMenu = hMenu;
        SetID(static_cast<UINT>(reinterpret_cast<INT_PTR>(GetHMenu())));
    }

    virtual AwtMenuBar* GetMenuBar();

    void AddSeparator();
    virtual void UpdateContainerLayout();
    void UpdateLayout();
    virtual void AddItem(AwtMenuItem *item);
    virtual void DeleteItem(UINT index);

    virtual HWND GetOwnerHWnd();

    /*for multifont menu */
    BOOL IsTopMenu();
    virtual AwtMenuItem* GetItem(jobject target, long index);

    virtual int CountItem(jobject target);

    virtual void SendDrawItem(AwtMenuItem* awtMenuItem,
                              DRAWITEMSTRUCT& drawInfo);
    virtual void SendMeasureItem(AwtMenuItem* awtMenuItem, HDC hDC,
                                 MEASUREITEMSTRUCT& measureInfo);
    void DrawItem(DRAWITEMSTRUCT& drawInfo);
    void DrawItems(DRAWITEMSTRUCT& drawInfo);
    void MeasureItem(HDC hDC, MEASUREITEMSTRUCT& measureInfo);
    void MeasureItems(HDC hDC, MEASUREITEMSTRUCT& measureInfo);

    virtual LRESULT WinThreadExecProc(ExecuteArgs * args);

    // invoked on Toolkit thread
    static void _CreateMenu(void *param);
    static void _CreateSubMenu(void *param);
    virtual BOOL IsSeparator() { return FALSE; }

protected:
    virtual void RemoveCmdID() { /* do nothing */ }

private:
    void UpdateLayout(const HMENU hmenu);
    HMENU    m_hMenu;
};

#endif /* AWT_MENU_H */
