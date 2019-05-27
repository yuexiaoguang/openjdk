
#ifndef MLIB_STATUS_H
#define MLIB_STATUS_H

#ifdef  __cplusplus
extern "C" {
#endif

typedef enum {
  MLIB_SUCCESS     = 0,
  MLIB_FAILURE     = 1,
  MLIB_NULLPOINTER = 2,
  MLIB_OUTOFRANGE  = 3
} mlib_status;

#ifdef  __cplusplus
}
#endif

#endif  /* MLIB_STATUS_H */
