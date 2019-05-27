#ifndef _ROBOT_COMMON_H
#define _ROBOT_COMMON_H

#include "java_awt_event_InputEvent.h"

#include <X11/Xlib.h>
#include "gdefs.h"

int QueryColorMap(Display *disp,
                  Colormap src_cmap,
                  Visual *src_vis,
                  XColor **src_colors,
                  int *rShift, int *gShift, int *bShift);

#endif /* _ROBOT_COMMON_H */
