
# TLS helper, assembled from .s file

# The copied fdlibm routines in sharedRuntimeTrig.o must not be optimized
OPT_CFLAGS/sharedRuntimeTrig.o = $(OPT_CFLAGS/NOOPT)
# The copied fdlibm routines in sharedRuntimeTrans.o must not be optimized
OPT_CFLAGS/sharedRuntimeTrans.o = $(OPT_CFLAGS/NOOPT)
# Must also specify if CPU is little endian
CFLAGS += -DVM_LITTLE_ENDIAN

OPT_CFLAGS/compactingPermGenGen.o = -O1
