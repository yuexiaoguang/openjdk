#ifndef AWT_BUTTON_H
#define AWT_BUTTON_H

#include "awt_Component.h"

#include "java_awt_Button.h"
#include "sun_awt_windows_WButtonPeer.h"


/************************************************************************
 * AwtButton class
 */

class AwtButton : public AwtComponent {
public:
    /* java.awt.Button label field ID */
    static jfieldID labelID;

    AwtButton();

    virtual LPCTSTR GetClassName();

    static AwtButton* Create(jobject self, jobject hParent);

    /* Windows message handler functions */
    MsgRouting WmMouseDown(UINT flags, int x, int y, int button);
    MsgRouting WmMouseUp(UINT flags, int x, int y, int button);
    MsgRouting OwnerDrawItem(UINT ctrlId, DRAWITEMSTRUCT& drawInfo);
    MsgRouting WmPaint(HDC hDC);

    MsgRouting HandleEvent(MSG *msg, BOOL synthetic);

    BOOL IsFocusingMouseMessage(MSG *pMsg);
    BOOL IsFocusingKeyMessage(MSG *pMsg);

    // called on Toolkit thread from JNI
    static void _SetLabel(void *param);
private:
    // 4530087: variable to keep track of left mouse press
    BOOL leftButtonDown;
    void NotifyListeners();
};

#endif // AWT_BUTTON_H
