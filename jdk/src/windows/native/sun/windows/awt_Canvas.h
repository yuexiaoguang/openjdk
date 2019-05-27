#ifndef AWT_CANVAS_H
#define AWT_CANVAS_H

#include "awt_Component.h"
#include "sun_awt_windows_WCanvasPeer.h"


/************************************************************************
 * AwtCanvas class
 */

class AwtCanvas : public AwtComponent {
public:
    AwtCanvas();
    virtual ~AwtCanvas();

    virtual LPCTSTR GetClassName();
    static AwtCanvas* Create(jobject self, jobject hParent);

    virtual MsgRouting WmEraseBkgnd(HDC hDC, BOOL& didErase);
    virtual MsgRouting WmPaint(HDC hDC);

    virtual MsgRouting HandleEvent(MSG *msg, BOOL synthetic);

    static void _ResetTargetGC(void *);
    static void _SetEraseBackground(void *);

private:
    jboolean m_eraseBackground;
    jboolean m_eraseBackgroundOnResize;
 };

#endif /* AWT_CANVAS_H */
