#ifndef GlyphImageRef_h_Included
#define GlyphImageRef_h_Included

#ifdef  __cplusplus
extern "C" {
#endif

/*
 * Previously private structure in GlyphVector.cpp, exposed in order
 * to allow C code to access this without making C++ method calls in C
 * only library.
 */

typedef struct {
    void *glyphInfo;
    const void *pixels;
    int rowBytes;
    int rowBytesOffset;
    int width;
    int height;
    int x;
    int y;
} ImageRef;

#ifdef  __cplusplus
}
#endif


#endif /* GlyphImageRef_h_Included */
