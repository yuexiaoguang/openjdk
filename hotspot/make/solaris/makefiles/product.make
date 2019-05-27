
# Sets make macros for making optimized version of Gamma VM
# (This is the "product", not the "release" version.)

# Compiler specific OPT_CFLAGS are passed in from gcc.make, sparcWorks.make
OPT_CFLAGS/DEFAULT= $(OPT_CFLAGS)
OPT_CFLAGS/BYFILE = $(OPT_CFLAGS/$@)$(OPT_CFLAGS/DEFAULT$(OPT_CFLAGS/$@))

# Workaround for a bug in dtrace.  If ciEnv::post_compiled_method_load_event()
# is inlined, the resulting dtrace object file needs a reference to this
# function, whose symbol name is too long for dtrace.  So disable inlining
# for this method for now. (fix this when dtrace bug 6258412 is fixed)
ifndef USE_GCC
OPT_CFLAGS/ciEnv.o = $(OPT_CFLAGS) -xinline=no%__1cFciEnvbFpost_compiled_method_load_event6MpnHnmethod__v_
endif

# (OPT_CFLAGS/SLOWER is also available, to alter compilation of buggy files)
ifeq ("${Platform_compiler}", "sparcWorks")

ifeq ($(COMPILER_REV_NUMERIC), 510)
# CC 5.10 has bug XXXXX with -xO4
OPT_CFLAGS/jvmtiClassFileReconstituter.o = $(OPT_CFLAGS/O2)
# Avoid apparent crash because of corrupted methodHandle in a tail call
OPT_CFLAGS/simpleThresholdPolicy.o = $(OPT_CFLAGS/DEFAULT) $(OPT_CCFLAGS/NO_TAIL_CALL_OPT)
endif # COMPILER_REV_NUMERIC == 510

ifeq ($(shell expr $(COMPILER_REV_NUMERIC) \>= 509), 1)
# dtrace cannot handle tail call optimization (6672627, 6693876)
OPT_CFLAGS/jni.o = $(OPT_CFLAGS/DEFAULT) $(OPT_CCFLAGS/NO_TAIL_CALL_OPT)
endif # COMPILER_NUMERIC_REV >= 509

# Workaround SS11 bug 6345274 (all platforms) (Fixed in SS11 patch and SS12)
ifeq ($(COMPILER_REV_NUMERIC),508)
OPT_CFLAGS/ciTypeFlow.o = $(OPT_CFLAGS/O2)
endif # COMPILER_REV_NUMERIC == 508

endif # Platform_compiler == sparcWorks

# If you set HOTSPARC_GENERIC=yes, you disable all OPT_CFLAGS settings
CFLAGS$(HOTSPARC_GENERIC) += $(OPT_CFLAGS/BYFILE)
# Set the environment variable HOTSPARC_GENERIC to "true"
# to inhibit the effect of the previous line on CFLAGS.

# Linker mapfiles
MAPFILE = $(GAMMADIR)/make/solaris/makefiles/mapfile-vers

ifndef USE_GCC
# This mapfile is only needed when compiling with dtrace support, 
# and mustn't be otherwise.
MAPFILE_DTRACE = $(GAMMADIR)/make/solaris/makefiles/mapfile-vers-$(TYPE)

endif

# If we can create .debuginfo files, then the VM is stripped in vm.make
# and this macro is not used.
# LINK_LIB.CXX/POST_HOOK += $(STRIP_LIB.CXX/POST_HOOK)

SYSDEFS += -DPRODUCT
VERSION = optimized
