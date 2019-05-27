/*
 * Implements the native code for the java.awt.AWTEvent class
 * and all of the classes in the java.awt.event package.
 *
 * THIS FILE DOES NOT IMPLEMENT ANY OF THE OBSOLETE java.awt.Event
 * CLASS. SEE awt_Event.[ch] FOR THAT CLASS' IMPLEMENTATION.
 */

#ifdef HEADLESS
    #error This file should not be included in headless library
#endif

#include "awt_p.h"
#include "java_awt_AWTEvent.h"
#include "java_awt_event_InputEvent.h"
#include "java_awt_event_KeyEvent.h"
#include "jni_util.h"

#include "awt_AWTEvent.h"

struct AWTEventIDs awtEventIDs;
struct InputEventIDs inputEventIDs;
struct KeyEventIDs keyEventIDs;

JNIEXPORT void JNICALL
Java_java_awt_AWTEvent_initIDs(JNIEnv *env, jclass cls)
{
    awtEventIDs.bdata = (*env)->GetFieldID(env, cls, "bdata", "[B");
    awtEventIDs.consumed = (*env)->GetFieldID(env, cls, "consumed", "Z");
    awtEventIDs.id = (*env)->GetFieldID(env, cls, "id", "I");
}

JNIEXPORT void JNICALL
Java_java_awt_event_InputEvent_initIDs(JNIEnv *env, jclass cls)
{
    inputEventIDs.modifiers = (*env)->GetFieldID(env, cls, "modifiers", "I");
}

JNIEXPORT void JNICALL
Java_java_awt_event_KeyEvent_initIDs(JNIEnv *env, jclass cls)
{
    keyEventIDs.keyCode = (*env)->GetFieldID(env, cls, "keyCode", "I");
    keyEventIDs.keyChar = (*env)->GetFieldID(env, cls, "keyChar", "C");
}

JNIEXPORT void JNICALL
Java_java_awt_AWTEvent_nativeSetSource(JNIEnv *env, jobject self,
                                       jobject newSource)
{

}
