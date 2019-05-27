/*
 * This file is available under and governed by the GNU General Public
 * License version 2 only, as published by the Free Software Foundation.
 * However, the following notice accompanied the original version of this
 * file:
 *
 * $Xorg: extutil.h,v 1.3 2000/08/18 04:05:45 coskrey Exp $
 *
 */
/* $XFree86: xc/include/extensions/extutil.h,v 1.5 2001/01/17 17:53:20 dawes Exp $ */

#if defined(__linux__) || defined(MACOSX)

#ifndef _EXTUTIL_H_
#define _EXTUTIL_H_

/*
 * We need to keep a list of open displays since the Xlib display list isn't
 * public.  We also have to per-display info in a separate block since it isn't
 * stored directly in the Display structure.
 */
typedef struct _XExtDisplayInfo {
    struct _XExtDisplayInfo *next;      /* keep a linked list */
    Display *display;                   /* which display this is */
    XExtCodes *codes;                   /* the extension protocol codes */
    XPointer data;                      /* extra data for extension to use */
} XExtDisplayInfo;

typedef struct _XExtensionInfo {
    XExtDisplayInfo *head;              /* start of list */
    XExtDisplayInfo *cur;               /* most recently used */
    int ndisplays;                      /* number of displays */
} XExtensionInfo;

typedef struct _XExtensionHooks {
    int (*create_gc)(
#if NeedNestedPrototypes
              Display*                  /* display */,
              GC                        /* gc */,
              XExtCodes*                /* codes */
#endif
);
    int (*copy_gc)(
#if NeedNestedPrototypes
              Display*                  /* display */,
              GC                        /* gc */,
              XExtCodes*                /* codes */
#endif
);
    int (*flush_gc)(
#if NeedNestedPrototypes
              Display*                  /* display */,
              GC                        /* gc */,
              XExtCodes*                /* codes */
#endif
);
    int (*free_gc)(
#if NeedNestedPrototypes
              Display*                  /* display */,
              GC                        /* gc */,
              XExtCodes*                /* codes */
#endif
);
    int (*create_font)(
#if NeedNestedPrototypes
              Display*                  /* display */,
              XFontStruct*              /* fs */,
              XExtCodes*                /* codes */
#endif
);
    int (*free_font)(
#if NeedNestedPrototypes
              Display*                  /* display */,
              XFontStruct*              /* fs */,
              XExtCodes*                /* codes */
#endif
);
    int (*close_display)(
#if NeedNestedPrototypes
              Display*                  /* display */,
              XExtCodes*                /* codes */
#endif
);
    Bool (*wire_to_event)(
#if NeedNestedPrototypes
               Display*                 /* display */,
               XEvent*                  /* re */,
               xEvent*                  /* event */
#endif
);
    Status (*event_to_wire)(
#if NeedNestedPrototypes
              Display*                  /* display */,
              XEvent*                   /* re */,
              xEvent*                   /* event */
#endif
);
    int (*error)(
#if NeedNestedPrototypes
              Display*                  /* display */,
              xError*                   /* err */,
              XExtCodes*                /* codes */,
              int*                      /* ret_code */
#endif
);
    char *(*error_string)(
#if NeedNestedPrototypes
                Display*                /* display */,
                int                     /* code */,
                XExtCodes*              /* codes */,
                char*                   /* buffer */,
                int                     /* nbytes */
#endif
);
} XExtensionHooks;

extern XExtensionInfo *XextCreateExtension(
#if NeedFunctionPrototypes
    void
#endif
);
extern void XextDestroyExtension(
#if NeedFunctionPrototypes
    XExtensionInfo*     /* info */
#endif
);
extern XExtDisplayInfo *XextAddDisplay(
#if NeedFunctionPrototypes
    XExtensionInfo*     /* extinfo */,
    Display*            /* dpy */,
    char*               /* ext_name */,
    XExtensionHooks*    /* hooks */,
    int                 /* nevents */,
    XPointer            /* data */
#endif
);
extern int XextRemoveDisplay(
#if NeedFunctionPrototypes
    XExtensionInfo*     /* extinfo */,
    Display*            /* dpy */
#endif
);
extern XExtDisplayInfo *XextFindDisplay(
#if NeedFunctionPrototypes
    XExtensionInfo*     /* extinfo */,
    Display*            /* dpy */
#endif
);

#define XextHasExtension(i) ((i) && ((i)->codes))
#define XextCheckExtension(dpy,i,name,val) \
  if (!XextHasExtension(i)) { XMissingExtension (dpy, name); return val; }
#define XextSimpleCheckExtension(dpy,i,name) \
  if (!XextHasExtension(i)) { XMissingExtension (dpy, name); return; }


/*
 * helper macros to generate code that is common to all extensions; caller
 * should prefix it with static if extension source is in one file; this
 * could be a utility function, but have to stack 6 unused arguments for
 * something that is called many, many times would be bad.
 */
#define XEXT_GENERATE_FIND_DISPLAY(proc,extinfo,extname,hooks,nev,data) \
XExtDisplayInfo *proc (Display *dpy) \
{ \
    XExtDisplayInfo *dpyinfo; \
    if (!extinfo) { if (!(extinfo = XextCreateExtension())) return NULL; } \
    if (!(dpyinfo = XextFindDisplay (extinfo, dpy))) \
      dpyinfo = XextAddDisplay (extinfo,dpy,extname,hooks,nev,data); \
    return dpyinfo; \
}

#define XEXT_FIND_DISPLAY_PROTO(proc) \
        XExtDisplayInfo *proc(Display *dpy)

#define XEXT_GENERATE_CLOSE_DISPLAY(proc,extinfo) \
int proc (Display *dpy, XExtCodes *codes) \
{ \
    return XextRemoveDisplay (extinfo, dpy); \
}

#define XEXT_CLOSE_DISPLAY_PROTO(proc) \
        int proc(Display *dpy, XExtCodes *codes)

#define XEXT_GENERATE_ERROR_STRING(proc,extname,nerr,errl) \
char *proc (Display *dpy, int code, XExtCodes *codes, char *buf, int n) \
{  \
    code -= codes->first_error;  \
    if (code >= 0 && code < nerr) { \
        char tmp[256]; \
        sprintf (tmp, "%s.%d", extname, code); \
        XGetErrorDatabaseText (dpy, "XProtoError", tmp, errl[code], buf, n); \
        return buf; \
    } \
    return (char *)0; \
}

#define XEXT_ERROR_STRING_PROTO(proc) \
        char *proc(Display *dpy, int code, XExtCodes *codes, char *buf, int n)
#endif

#endif /* __linux__ || MACOSX */
