#ifndef AWT_TEXTFIELD_H
#define AWT_TEXTFIELD_H

#include "awt_TextComponent.h"

#include "java_awt_TextField.h"
#include "sun_awt_windows_WTextFieldPeer.h"

#include <ole2.h>
#include <richedit.h>
#include <richole.h>

/************************************************************************
 * AwtTextField class
 */

class AwtTextField : public AwtTextComponent {
public:
    AwtTextField();

    static AwtTextField* Create(jobject self, jobject parent);

    /*
     *  Windows message handler functions
     */
    MsgRouting HandleEvent(MSG *msg, BOOL synthetic);

    virtual LRESULT WindowProc(UINT message, WPARAM wParam, LPARAM lParam);
    // invoked on Toolkit thread
    static void _SetEchoChar(void *param);

protected:

private:
    void EditSetSel(CHARRANGE &cr);

};

#endif /* AWT_TEXTFIELD_H */
