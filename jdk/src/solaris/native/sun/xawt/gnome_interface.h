#ifndef _GNOME_INTERFACE_H
#define _GNOME_INTERFACE_H
#include "gtk2_interface.h"
#include <dlfcn.h>
#include <jvm_md.h>
#include <jni.h>

typedef gboolean (GNOME_URL_SHOW_TYPE)(const char *, void **);
typedef gboolean (GNOME_VFS_INIT_TYPE)(void);

extern GNOME_URL_SHOW_TYPE *gnome_url_show;
gboolean gnome_load();

#endif /* !_GNOME_INTERFACE_H */
