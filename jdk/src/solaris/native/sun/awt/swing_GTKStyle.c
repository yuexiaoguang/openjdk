#include <stdlib.h>
#include "gtk2_interface.h"
#include "com_sun_java_swing_plaf_gtk_GTKStyle.h"

/*
 * Class:     com_sun_java_swing_plaf_gtk_GTKStyle
 * Method:    nativeGetXThickness
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL
Java_com_sun_java_swing_plaf_gtk_GTKStyle_nativeGetXThickness(
    JNIEnv *env, jclass klass, jint widget_type)
{
    jint ret;
    fp_gdk_threads_enter();
    ret = gtk2_get_xthickness(env, widget_type);
    fp_gdk_threads_leave();
    return ret;
}

/*
 * Class:     com_sun_java_swing_plaf_gtk_GTKStyle
 * Method:    nativeGetYThickness
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL
Java_com_sun_java_swing_plaf_gtk_GTKStyle_nativeGetYThickness(
    JNIEnv *env, jclass klass, jint widget_type)
{
    jint ret;
    fp_gdk_threads_enter();
    ret = gtk2_get_ythickness(env, widget_type);
    fp_gdk_threads_leave();
    return ret;
}

/*
 * Class:     com_sun_java_swing_plaf_gtk_GTKStyle
 * Method:    nativeGetColorForState
 * Signature: (III)I
 */
JNIEXPORT jint JNICALL
Java_com_sun_java_swing_plaf_gtk_GTKStyle_nativeGetColorForState(
    JNIEnv *env, jclass klass, jint widget_type,
    jint state_type, jint type_id)
{
    jint ret;
    fp_gdk_threads_enter();
    ret = gtk2_get_color_for_state(env, widget_type, state_type, type_id);
    fp_gdk_threads_leave();
    return ret;
}

/*
 * Class:     com_sun_java_swing_plaf_gtk_GTKStyle
 * Method:    nativeGetClassValue
 * Signature: (ILjava/lang/String;)Ljava/lang/Object;
 */
JNIEXPORT jobject JNICALL
Java_com_sun_java_swing_plaf_gtk_GTKStyle_nativeGetClassValue(
    JNIEnv *env, jclass klass, jint widget_type, jstring key)
{
    jobject ret;
    fp_gdk_threads_enter();
    ret = gtk2_get_class_value(env, widget_type, key);
    fp_gdk_threads_leave();
    return ret;
}

/*
 * Class:     com_sun_java_swing_plaf_gtk_GTKStyle
 * Method:    nativeGetPangoFontName
 * Signature: (I)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL
Java_com_sun_java_swing_plaf_gtk_GTKStyle_nativeGetPangoFontName(
    JNIEnv *env, jclass klass, jint widget_type)
{
    jstring ret;
    fp_gdk_threads_enter();
    ret = gtk2_get_pango_font_name(env, widget_type);
    fp_gdk_threads_leave();
    return ret;
}
