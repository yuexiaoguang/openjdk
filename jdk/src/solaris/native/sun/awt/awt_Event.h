/***
 *** THIS IMPLEMENTS ONLY THE OBSOLETE java.awt.Event CLASS! SEE
 *** awt_AWTEvent.[ch] FOR THE NEWER EVENT CLASSES.
 ***
 ***/
#ifndef _AWT_EVENT_H_
#define _AWT_EVENT_H

#include "jni_util.h"

struct EventIDs {
  jfieldID data;
  jfieldID consumed;
  jfieldID id;
};

extern struct EventIDs eventIDs;

#endif /* _AWT_EVENT_H_ */
