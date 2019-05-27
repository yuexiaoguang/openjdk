
# The copied fdlibm routines in sharedRuntimeTrig.o must not be optimized
OPT_CFLAGS/sharedRuntimeTrig.o = $(OPT_CFLAGS/NOOPT)

# Must also specify if CPU is big endian
CFLAGS += -DVM_BIG_ENDIAN

ifdef E500V2
ASFLAGS += -Wa,-mspe -Wa,--defsym -Wa,E500V2=1 
endif
