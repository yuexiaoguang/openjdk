#ifndef __FONT_SCALER__
#define __FONT_SCALER__

/* This file contains shared definitions that
   are used by glue code of different rasterizers. */

#include "fontscalerdefs.h"

typedef struct {
        hsFixed t00, t01;
        hsFixed t10, t11;
} TransformationMatrix;

/* Known AA/FM types.
 * These are copied from sun.awt.SunHints.
 * Consider initialising them as ints using JNI for more robustness.
 */
#define TEXT_AA_OFF 1
#define TEXT_AA_ON  2
#define TEXT_AA_LCD_HRGB 4
#define TEXT_AA_LCD_HBGR 5
#define TEXT_AA_LCD_VRGB 6
#define TEXT_AA_LCD_VBGR 7
#define TEXT_FM_OFF 1
#define TEXT_FM_ON  2

#endif
