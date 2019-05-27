
# The copied fdlibm routines in sharedRuntimeTrig.o must not be optimized
OPT_CFLAGS/sharedRuntimeTrig.o = $(OPT_CFLAGS/NOOPT)

# Must also specify if CPU is big endian
CFLAGS += -DVM_BIG_ENDIAN

