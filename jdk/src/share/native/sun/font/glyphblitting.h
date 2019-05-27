#ifndef BlittingIncludesDefined
#define BlittingIncludesDefined

#include "jni.h"
#include "GlyphImageRef.h"
#include "SurfaceData.h"

#ifdef  __cplusplus
extern "C" {
#endif

typedef struct {
  int numGlyphs;
  ImageRef *glyphs;
} GlyphBlitVector;

extern jint RefineBounds(GlyphBlitVector *gbv, SurfaceDataBounds *bounds);
extern GlyphBlitVector* setupBlitVector(JNIEnv *env, jobject glyphlist);
extern GlyphBlitVector* setupLCDBlitVector(JNIEnv *env, jobject glyphlist);

#ifdef  __cplusplus
}
#endif


#endif
