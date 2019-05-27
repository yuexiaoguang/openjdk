
#ifndef MLIB_IMAGE_ROWTABLE_H
#define MLIB_IMAGE_ROWTABLE_H

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

void *mlib_ImageCreateRowTable(mlib_image *image);
void mlib_ImageDeleteRowTable(mlib_image *image);

static void *mlib_ImageGetRowTable(mlib_image *img)
{
  return img->state;
}

#ifdef __cplusplus
}
#endif /* __cplusplus */
#endif /* MLIB_IMAGE_ROWTABLE_H */
