
#ifndef __MLIB_IMAGE_LOOKUP_C_FUNC_INTENAL_H
#define __MLIB_IMAGE_LOOKUP_C_FUNC_INTENAL_H

#ifdef __cplusplus
extern "C" {
#endif

/* mlib_c_ImageLookUp.c */

#define mlib_c_ImageLookUp_U8_U16       mlib_c_ImageLookUp_U8_S16
#define mlib_c_ImageLookUpSI_U8_U16     mlib_c_ImageLookUpSI_U8_S16

void mlib_c_ImageLookUp_U8_U8(const mlib_u8 *src,
                              mlib_s32      slb,
                              mlib_u8       *dst,
                              mlib_s32      dlb,
                              mlib_s32      xsize,
                              mlib_s32      ysize,
                              mlib_s32      csize,
                              const mlib_u8 **table);

void mlib_c_ImageLookUp_S16_U8(const mlib_s16 *src,
                               mlib_s32       slb,
                               mlib_u8        *dst,
                               mlib_s32       dlb,
                               mlib_s32       xsize,
                               mlib_s32       ysize,
                               mlib_s32       csize,
                               const mlib_u8  **table);

void mlib_c_ImageLookUp_U16_U8(const mlib_u16 *src,
                               mlib_s32       slb,
                               mlib_u8        *dst,
                               mlib_s32       dlb,
                               mlib_s32       xsize,
                               mlib_s32       ysize,
                               mlib_s32       csize,
                               const mlib_u8  **table);

void mlib_c_ImageLookUp_S32_U8(const mlib_s32 *src,
                               mlib_s32       slb,
                               mlib_u8        *dst,
                               mlib_s32       dlb,
                               mlib_s32       xsize,
                               mlib_s32       ysize,
                               mlib_s32       csize,
                               const mlib_u8  **table);

void mlib_c_ImageLookUp_U8_S16(const mlib_u8  *src,
                               mlib_s32       slb,
                               mlib_s16       *dst,
                               mlib_s32       dlb,
                               mlib_s32       xsize,
                               mlib_s32       ysize,
                               mlib_s32       csize,
                               const mlib_s16 **table);

void mlib_c_ImageLookUp_S16_S16(const mlib_s16 *src,
                                mlib_s32       slb,
                                mlib_s16       *dst,
                                mlib_s32       dlb,
                                mlib_s32       xsize,
                                mlib_s32       ysize,
                                mlib_s32       csize,
                                const mlib_s16 **table);

void mlib_c_ImageLookUp_U16_S16(const mlib_u16 *src,
                                mlib_s32       slb,
                                mlib_s16       *dst,
                                mlib_s32       dlb,
                                mlib_s32       xsize,
                                mlib_s32       ysize,
                                mlib_s32       csize,
                                const mlib_s16 **table);

void mlib_c_ImageLookUp_S32_S16(const mlib_s32 *src,
                                mlib_s32       slb,
                                mlib_s16       *dst,
                                mlib_s32       dlb,
                                mlib_s32       xsize,
                                mlib_s32       ysize,
                                mlib_s32       csize,
                                const mlib_s16 **table);

void mlib_c_ImageLookUp_S16_U16(const mlib_s16 *src,
                                mlib_s32       slb,
                                mlib_u16       *dst,
                                mlib_s32       dlb,
                                mlib_s32       xsize,
                                mlib_s32       ysize,
                                mlib_s32       csize,
                                const mlib_s16 **table);

void mlib_c_ImageLookUp_U16_U16(const mlib_u16 *src,
                                mlib_s32       slb,
                                mlib_u16       *dst,
                                mlib_s32       dlb,
                                mlib_s32       xsize,
                                mlib_s32       ysize,
                                mlib_s32       csize,
                                const mlib_s16 **table);

void mlib_c_ImageLookUp_S32_U16(const mlib_s32 *src,
                                mlib_s32       slb,
                                mlib_u16       *dst,
                                mlib_s32       dlb,
                                mlib_s32       xsize,
                                mlib_s32       ysize,
                                mlib_s32       csize,
                                const mlib_s16 **table);

void mlib_c_ImageLookUp_U8_S32(const mlib_u8  *src,
                               mlib_s32       slb,
                               mlib_s32       *dst,
                               mlib_s32       dlb,
                               mlib_s32       xsize,
                               mlib_s32       ysize,
                               mlib_s32       csize,
                               const mlib_s32 **table);

void mlib_c_ImageLookUp_S16_S32(const mlib_s16 *src,
                                mlib_s32       slb,
                                mlib_s32       *dst,
                                mlib_s32       dlb,
                                mlib_s32       xsize,
                                mlib_s32       ysize,
                                mlib_s32       csize,
                                const mlib_s32 **table);

void mlib_c_ImageLookUp_U16_S32(const mlib_u16 *src,
                                mlib_s32       slb,
                                mlib_s32       *dst,
                                mlib_s32       dlb,
                                mlib_s32       xsize,
                                mlib_s32       ysize,
                                mlib_s32       csize,
                                const mlib_s32 **table);

void mlib_c_ImageLookUp_S32_S32(const mlib_s32 *src,
                                mlib_s32       slb,
                                mlib_s32       *dst,
                                mlib_s32       dlb,
                                mlib_s32       xsize,
                                mlib_s32       ysize,
                                mlib_s32       csize,
                                const mlib_s32 **table);

void mlib_c_ImageLookUpSI_U8_U8(const mlib_u8 *src,
                                mlib_s32      slb,
                                mlib_u8       *dst,
                                mlib_s32      dlb,
                                mlib_s32      xsize,
                                mlib_s32      ysize,
                                mlib_s32      csize,
                                const mlib_u8 **table);

void mlib_c_ImageLookUpSI_S16_U8(const mlib_s16 *src,
                                 mlib_s32       slb,
                                 mlib_u8        *dst,
                                 mlib_s32       dlb,
                                 mlib_s32       xsize,
                                 mlib_s32       ysize,
                                 mlib_s32       csize,
                                 const mlib_u8  **table);

void mlib_c_ImageLookUpSI_U16_U8(const mlib_u16 *src,
                                 mlib_s32       slb,
                                 mlib_u8        *dst,
                                 mlib_s32       dlb,
                                 mlib_s32       xsize,
                                 mlib_s32       ysize,
                                 mlib_s32       csize,
                                 const mlib_u8  **table);

void mlib_c_ImageLookUpSI_S32_U8(const mlib_s32 *src,
                                 mlib_s32       slb,
                                 mlib_u8        *dst,
                                 mlib_s32       dlb,
                                 mlib_s32       xsize,
                                 mlib_s32       ysize,
                                 mlib_s32       csize,
                                 const mlib_u8  **table);

void mlib_c_ImageLookUpSI_U8_S16(const mlib_u8  *src,
                                 mlib_s32       slb,
                                 mlib_s16       *dst,
                                 mlib_s32       dlb,
                                 mlib_s32       xsize,
                                 mlib_s32       ysize,
                                 mlib_s32       csize,
                                 const mlib_s16 **table);

void mlib_c_ImageLookUpSI_S16_S16(const mlib_s16 *src,
                                  mlib_s32       slb,
                                  mlib_s16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  mlib_s32       csize,
                                  const mlib_s16 **table);

void mlib_c_ImageLookUpSI_U16_S16(const mlib_u16 *src,
                                  mlib_s32       slb,
                                  mlib_s16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  mlib_s32       csize,
                                  const mlib_s16 **table);

void mlib_c_ImageLookUpSI_S32_S16(const mlib_s32 *src,
                                  mlib_s32       slb,
                                  mlib_s16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  mlib_s32       csize,
                                  const mlib_s16 **table);

void mlib_c_ImageLookUpSI_S16_U16(const mlib_s16 *src,
                                  mlib_s32       slb,
                                  mlib_u16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  mlib_s32       csize,
                                  const mlib_u16 **table);

void mlib_c_ImageLookUpSI_U16_U16(const mlib_u16 *src,
                                  mlib_s32       slb,
                                  mlib_u16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  mlib_s32       csize,
                                  const mlib_u16 **table);

void mlib_c_ImageLookUpSI_S32_U16(const mlib_s32 *src,
                                  mlib_s32       slb,
                                  mlib_u16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  mlib_s32       csize,
                                  const mlib_u16 **table);

void mlib_c_ImageLookUpSI_U8_S32(const mlib_u8  *src,
                                 mlib_s32       slb,
                                 mlib_s32       *dst,
                                 mlib_s32       dlb,
                                 mlib_s32       xsize,
                                 mlib_s32       ysize,
                                 mlib_s32       csize,
                                 const mlib_s32 **table);

void mlib_c_ImageLookUpSI_S16_S32(const mlib_s16 *src,
                                  mlib_s32       slb,
                                  mlib_s32       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  mlib_s32       csize,
                                  const mlib_s32 **table);

void mlib_c_ImageLookUpSI_U16_S32(const mlib_u16 *src,
                                  mlib_s32       slb,
                                  mlib_s32       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  mlib_s32       csize,
                                  const mlib_s32 **table);

void mlib_c_ImageLookUpSI_S32_S32(const mlib_s32 *src,
                                  mlib_s32       slb,
                                  mlib_s32       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  mlib_s32       csize,
                                  const mlib_s32 **table);

#ifdef __cplusplus
}
#endif
#endif /* __MLIB_IMAGE_LOOKUP_C_FUNC_INTENAL_H */
