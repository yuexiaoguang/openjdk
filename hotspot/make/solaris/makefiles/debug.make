
# Sets make macros for making debug version of VM

# Compiler specific DEBUG_CFLAGS are passed in from gcc.make, sparcWorks.make
DEBUG_CFLAGS/DEFAULT= $(DEBUG_CFLAGS)
DEBUG_CFLAGS/BYFILE = $(DEBUG_CFLAGS/$@)$(DEBUG_CFLAGS/DEFAULT$(DEBUG_CFLAGS/$@))

ifeq ("${Platform_compiler}", "sparcWorks")

ifeq ($(COMPILER_REV_NUMERIC),508)
  # SS11 SEGV when compiling with -g and -xarch=v8, using different backend
  DEBUG_CFLAGS/compileBroker.o = $(DEBUG_CFLAGS) -xO0
  DEBUG_CFLAGS/jvmtiTagMap.o   = $(DEBUG_CFLAGS) -xO0
endif
endif

# _NMT_NOINLINE_ informs NMT that no inlining by Compiler
CFLAGS += $(DEBUG_CFLAGS/BYFILE) -D_NMT_NOINLINE_

# Set the environment variable HOTSPARC_GENERIC to "true"
# to inhibit the effect of the previous line on CFLAGS.

# Linker mapfiles
MAPFILE = $(GAMMADIR)/make/solaris/makefiles/mapfile-vers \
          $(GAMMADIR)/make/solaris/makefiles/mapfile-vers-debug

# This mapfile is only needed when compiling with dtrace support,
# and mustn't be otherwise.
MAPFILE_DTRACE = $(GAMMADIR)/make/solaris/makefiles/mapfile-vers-$(TYPE)

VERSION = debug
SYSDEFS += -DASSERT
PICFLAGS = DEFAULT
