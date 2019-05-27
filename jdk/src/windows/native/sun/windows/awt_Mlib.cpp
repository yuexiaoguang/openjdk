#include "jni.h"
#include "awt_Mlib.h"
#include "java_awt_image_BufferedImage.h"

#include <windows.h>
#include "alloc.h"

extern "C"
{
    /*
 * This is called by awt_ImagingLib.initLib() to figure out if there
 * is a native imaging lib tied to the ImagingLib.java (other than
 * the shared medialib).
 */
    mlib_status awt_getImagingLib(JNIEnv *env, mlibFnS_t *sMlibFns,
                                  mlibSysFnS_t *sMlibSysFns) {
        static HINSTANCE hDLL = NULL;
        mlibSysFnS_t tempSysFns;
        mlib_status ret = MLIB_SUCCESS;

        /* Try to receive handle for the library. Routine should find
         * the library successfully because this library is already
         * loaded to the process space by the System.loadLibrary() call.
         * Here we just need to get handle to initialize the pointers to
         * required mlib routines.
         */
        hDLL = ::GetModuleHandle(TEXT("mlib_image.dll"));

        if (hDLL == NULL) {
            return MLIB_FAILURE;
        }

        /* Initialize pointers to medilib routines... */
        tempSysFns.createFP = (MlibCreateFP_t)
            ::GetProcAddress(hDLL, "j2d_mlib_ImageCreate");
        if (tempSysFns.createFP == NULL) {
            ret = MLIB_FAILURE;
        }

        if (ret == MLIB_SUCCESS) {
            tempSysFns.createStructFP = (MlibCreateStructFP_t)
                ::GetProcAddress(hDLL, "j2d_mlib_ImageCreateStruct");
            if (tempSysFns.createStructFP == NULL) {
                ret = MLIB_FAILURE;
            }
        }

        if (ret == MLIB_SUCCESS) {
            tempSysFns.deleteImageFP = (MlibDeleteFP_t)
                ::GetProcAddress(hDLL, "j2d_mlib_ImageDelete");
            if (tempSysFns.deleteImageFP == NULL) {
                ret = MLIB_FAILURE;
            }
        }
        if (ret == MLIB_SUCCESS) {
            *sMlibSysFns = tempSysFns;
        }

        mlib_status (*fPtr)();
        mlibFnS_t* pMlibFns = sMlibFns;
        int i = 0;
        while ((ret == MLIB_SUCCESS) && (pMlibFns[i].fname != NULL)) {
            fPtr = (mlib_status (*)())
                ::GetProcAddress(hDLL, pMlibFns[i].fname);
            if (fPtr != NULL) {
                pMlibFns[i].fptr = fPtr;
            } else {
                ret = MLIB_FAILURE;
            }
            i++;
        }

        return ret;
    }

    mlib_start_timer awt_setMlibStartTimer() {
        return NULL;
    }

    mlib_stop_timer awt_setMlibStopTimer() {
        return NULL;
    }
}
