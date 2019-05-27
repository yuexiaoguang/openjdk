
# Sets make macros for making debug version of VM

# Compiler specific DEBUG_CFLAGS are passed in from gcc.make, sparcWorks.make
DEBUG_CFLAGS/DEFAULT= $(DEBUG_CFLAGS)
DEBUG_CFLAGS/BYFILE = $(DEBUG_CFLAGS/$@)$(DEBUG_CFLAGS/DEFAULT$(DEBUG_CFLAGS/$@))

# _NMT_NOINLINE_ informs NMT that no inlining by Compiler
CFLAGS += $(DEBUG_CFLAGS/BYFILE) -D_NMT_NOINLINE_

# Set the environment variable HOTSPARC_GENERIC to "true"
# to inhibit the effect of the previous line on CFLAGS.

# Linker mapfile
MAPFILE = $(GAMMADIR)/make/linux/makefiles/mapfile-vers-debug

VERSION = debug
SYSDEFS += -DASSERT
PICFLAGS = DEFAULT
