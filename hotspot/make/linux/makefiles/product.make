
# Sets make macros for making optimized version of Gamma VM
# (This is the "product", not the "release" version.)

# Compiler specific OPT_CFLAGS are passed in from gcc.make, sparcWorks.make
OPT_CFLAGS/DEFAULT= $(OPT_CFLAGS)
OPT_CFLAGS/BYFILE = $(OPT_CFLAGS/$@)$(OPT_CFLAGS/DEFAULT$(OPT_CFLAGS/$@))

# (OPT_CFLAGS/SLOWER is also available, to alter compilation of buggy files)

# If you set HOTSPARC_GENERIC=yes, you disable all OPT_CFLAGS settings
CFLAGS$(HOTSPARC_GENERIC) += $(OPT_CFLAGS/BYFILE)

# Set the environment variable HOTSPARC_GENERIC to "true"
# to inhibit the effect of the previous line on CFLAGS.

# Linker mapfile
MAPFILE = $(GAMMADIR)/make/linux/makefiles/mapfile-vers-product

SYSDEFS += -DPRODUCT
VERSION = optimized

# use -g to strip library as -x will discard its symbol table; -x is fine for
# executables.
# Note: these macros are not used in .debuginfo configs
STRIP_LIBJVM = $(STRIP) -g $@ || exit 1;
STRIP_AOUT   = $(STRIP) -x $@ || exit 1;

# If we can create .debuginfo files, then the VM is stripped in vm.make
# and this macro is not used.
# LINK_LIB.CXX/POST_HOOK += $(STRIP_$(LINK_INTO))
