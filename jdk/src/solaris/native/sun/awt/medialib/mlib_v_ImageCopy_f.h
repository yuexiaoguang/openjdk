#ifndef __MLIB_V_IMAGECOPY_F_H
#define __MLIB_V_IMAGECOPY_F_H


#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

void mlib_v_ImageCopy_a1(mlib_d64 *sp,
                         mlib_d64 *dp,
                         mlib_s32 size);

void mlib_v_ImageCopy_a2(mlib_d64 *sp,
                         mlib_d64 *dp,
                         mlib_s32 width,
                         mlib_s32 height,
                         mlib_s32 stride,
                         mlib_s32 dstride);

void mlib_v_ImageCopy_blk(const void *src,
                          void       *dst,
                          mlib_s32   size);

#ifdef __cplusplus
}
#endif /* __cplusplus */
#endif /* __MLIB_V_IMAGECOPY_F_H */
