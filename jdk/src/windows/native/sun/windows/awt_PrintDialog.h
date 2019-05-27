#ifndef _AWT_PRINT_DIALOG_H_
#define _AWT_PRINT_DIALOG_H_

#include "stdhdrs.h"
#include <commdlg.h>

/************************************************************************
 * AwtPrintDialog class
 */

class AwtPrintDialog {
public:
    static jfieldID AwtPrintDialog::controlID;
    static jfieldID AwtPrintDialog::parentID;
    static jfieldID AwtPrintDialog::pageID;
    static jmethodID AwtPrintDialog::setHWndMID;

    static BOOL PrintDlg(LPPRINTDLG);

    // called on Toolkit thread
    static jboolean _Show(void *param);

    static void _ToFront(void *param);
    static void _ToBack(void *param);
};

#endif
