/** ------------------------------------------------------------------------
        This file contains routines for manipulating generic lists.
        Lists are implemented with a "harness".  In other words, each
        node in the list consists of two pointers, one to the data item
        and one to the next node in the list.  The head of the list is
        the same struct as each node, but the "item" ptr is used to point
        to the current member of the list (used by the first_in_list and
        next_in_list functions).

 ------------------------------------------------------------------------ **/

extern int32_t GetMultiVisualRegions(
#if NeedFunctionPrototypes
    Display *, Window, int32_t, int32_t, uint32_t,
    uint32_t, int32_t *, int32_t *, XVisualInfo **, int32_t *,
    OverlayInfo  **, int32_t *, XVisualInfo ***, list_ptr *,
    list_ptr *, int32_t *
#endif
);

extern XImage *ReadAreaToImage(
#if NeedFunctionPrototypes
    Display *, Window, int32_t, int32_t, uint32_t,
    uint32_t, int32_t, XVisualInfo *, int32_t,
    OverlayInfo *, int32_t, XVisualInfo **, list_ptr,
    list_ptr, int32_t, int32_t
#endif
);

extern void initFakeVisual(
#if NeedFunctionPrototypes
    Visual *
#endif
);
