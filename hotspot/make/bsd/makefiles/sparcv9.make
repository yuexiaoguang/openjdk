
# gcc 4.0 miscompiles this code in -m64
OPT_CFLAGS/macro.o = -O0

CFLAGS += -D_LP64=1
