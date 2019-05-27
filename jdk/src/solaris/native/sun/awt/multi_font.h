/*
 * header for Multi Font String
 */
#ifndef _MULTI_FONT_H_
#define _MULTI_FONT_H_

#ifndef HEADLESS
jboolean awtJNI_IsMultiFont(JNIEnv *env,jobject this);
jboolean awtJNI_IsMultiFontMetrics(JNIEnv *env,jobject this);
XFontSet awtJNI_MakeFontSet(JNIEnv *env,jobject font);
struct FontData *awtJNI_GetFontData(JNIEnv *env,jobject font, char **errmsg);
int32_t awtJNI_GetMFStringWidth(JNIEnv * env, jcharArray s, int32_t offset,
                                int32_t length, jobject font);
#endif /* !HEADLESS */

#endif /* _MULTI_FONT_H_ */
