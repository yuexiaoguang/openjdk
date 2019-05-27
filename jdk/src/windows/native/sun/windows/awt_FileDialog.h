#ifndef AWT_FILE_DIALOG_H
#define AWT_FILE_DIALOG_H

#include "stdhdrs.h"
#include <commdlg.h>

#include "awt_Toolkit.h"
#include "awt_Component.h"
#include "awt_Dialog.h"

#include "java_awt_FileDialog.h"
#include "sun_awt_windows_WFileDialogPeer.h"

/************************************************************************
 * AwtFileDialog class
 */

class AwtFileDialog {
public:
    /* sun.awt.windows.WFileDialogPeer field and method ids */
    static jfieldID parentID;
    static jfieldID fileFilterID;
    static jmethodID setHWndMID;
    static jmethodID handleSelectedMID;
    static jmethodID handleCancelMID;
    static jmethodID checkFilenameFilterMID;
    static jmethodID isMultipleModeMID;

    /* java.awt.FileDialog field and method ids */
    static jfieldID modeID;
    static jfieldID dirID;
    static jfieldID fileID;
    static jfieldID filterID;

    static void Initialize(JNIEnv *env, jstring filterDescription);
    static void Show(void *peer);

    static BOOL GetOpenFileName(LPOPENFILENAME);
    static BOOL GetSaveFileName(LPOPENFILENAME);

    virtual BOOL InheritsNativeMouseWheelBehavior();

    // some methods called on Toolkit thread
    static void _DisposeOrHide(void *param);
    static void _ToFront(void *param);
    static void _ToBack(void *param);

private:
    static UINT GetBufferLength(LPTSTR buffer, UINT limit);
};

#endif /* FILE_DIALOG_H */
