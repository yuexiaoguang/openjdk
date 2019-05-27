
#ifndef __MLIB_IMAGECREATE_H
#define __MLIB_IMAGECREATE_H

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

mlib_image* mlib_ImageSet(mlib_image *image,
                          mlib_type  type,
                          mlib_s32   channels,
                          mlib_s32   width,
                          mlib_s32   height,
                          mlib_s32   stride,
                          const void *data);

mlib_image *mlib_ImageSetSubimage(mlib_image       *dst,
                                  const mlib_image *src,
                                  mlib_s32         x,
                                  mlib_s32         y,
                                  mlib_s32         w,
                                  mlib_s32         h);

#ifdef __cplusplus
}
#endif /* __cplusplus */
#endif /* __MLIB_IMAGECREATE_H */
