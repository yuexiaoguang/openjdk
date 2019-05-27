#ifndef AWT_CURSOR_H
#define AWT_CURSOR_H

#include "ObjectList.h"
#include "awt_Object.h"
#include "awt_Toolkit.h"

class AwtComponent;

/************************************************************************
 * AwtCursor class
 */

class AwtCursor : public AwtObject {
public:
    /* java.awt.Cursor */
    static jmethodID mSetPDataID;
    static jfieldID pDataID;
    static jfieldID typeID;

    /* java.awt.Point */
    static jfieldID pointXID;
    static jfieldID pointYID;

    /* sun.awt.GlobalCursorManager */
    static jclass globalCursorManagerClass;
    static jmethodID updateCursorID;

    AwtCursor(JNIEnv *env, HCURSOR hCur, jobject jCur);
    AwtCursor(JNIEnv *env, HCURSOR hCur, jobject jCur, int xH, int yH,
              int nWid, int nHgt, int nS, int *col, BYTE *hM);
    virtual ~AwtCursor();

    virtual void Dispose();

    INLINE HCURSOR GetHCursor() {
        if (dirty) {
            Rebuild();
        }
        return hCursor;
    }
    static AwtCursor * CreateSystemCursor(jobject jCursor);
    static void UpdateCursor(AwtComponent *comp);
    static HCURSOR  GetCursor(JNIEnv *env, AwtComponent *comp);

    static void setPData(jobject cursor, jlong pdata) {
        JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
        env->CallVoidMethod(cursor, mSetPDataID, pdata);
    }

private:
    void Rebuild();

    HCURSOR hCursor;
    jweak jCursor;

    /* data needed to reconstruct new cursor */
    int xHotSpot;
    int yHotSpot;
    int nWidth;
    int nHeight;
    int nSS;
    int  *cols;
    BYTE *mask;

    BOOL custom;
    BOOL dirty;

    static AwtObjectList customCursors;
};

#endif /* AWT_CURSOR_H */
