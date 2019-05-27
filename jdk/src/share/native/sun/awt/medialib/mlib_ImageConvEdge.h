
#ifndef __MLIB_IMAGECONVEDGE_H
#define __MLIB_IMAGECONVEDGE_H

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

mlib_status mlib_ImageConvClearEdge_Bit(mlib_image     *img,
                                        mlib_s32       dx_l,
                                        mlib_s32       dx_r,
                                        mlib_s32       dy_t,
                                        mlib_s32       dy_b,
                                        const mlib_s32 *color,
                                        mlib_s32       cmask);

mlib_status mlib_ImageConvClearEdge(mlib_image     *dst,
                                    mlib_s32       dx_l,
                                    mlib_s32       dx_r,
                                    mlib_s32       dy_t,
                                    mlib_s32       dy_b,
                                    const mlib_s32 *color,
                                    mlib_s32       cmask);

mlib_status mlib_ImageConvClearEdge_Fp(mlib_image     *img,
                                       mlib_s32       dx_l,
                                       mlib_s32       dx_r,
                                       mlib_s32       dy_t,
                                       mlib_s32       dy_b,
                                       const mlib_d64 *color,
                                       mlib_s32       cmask);

mlib_status mlib_ImageConvZeroEdge(mlib_image *dst,
                                   mlib_s32   dx_l,
                                   mlib_s32   dx_r,
                                   mlib_s32   dy_t,
                                   mlib_s32   dy_b,
                                   mlib_s32   cmask);

mlib_status mlib_ImageConvCopyEdge_Bit(mlib_image       *dst,
                                       const mlib_image *src,
                                       mlib_s32         dx_l,
                                       mlib_s32         dx_r,
                                       mlib_s32         dy_t,
                                       mlib_s32         dy_b,
                                       mlib_s32         cmask);

mlib_status mlib_ImageConvCopyEdge(mlib_image       *dst,
                                   const mlib_image *src,
                                   mlib_s32         dx_l,
                                   mlib_s32         dx_r,
                                   mlib_s32         dy_t,
                                   mlib_s32         dy_b,
                                   mlib_s32         cmask);

mlib_status mlib_ImageConvCopyEdge_Fp(mlib_image       *dst,
                                      const mlib_image *src,
                                      mlib_s32         dx_l,
                                      mlib_s32         dx_r,
                                      mlib_s32         dy_t,
                                      mlib_s32         dy_b,
                                      mlib_s32         cmask);

#ifdef __cplusplus
}
#endif /* __cplusplus */
#endif /* __MLIB_IMAGECONVEDGE_H */
