
# Setup common to Zero (non-Shark) and Shark versions of VM

# The copied fdlibm routines in sharedRuntimeTrig.o must not be optimized
OPT_CFLAGS/sharedRuntimeTrig.o = $(OPT_CFLAGS/NOOPT)
# The copied fdlibm routines in sharedRuntimeTrans.o must not be optimized
OPT_CFLAGS/sharedRuntimeTrans.o = $(OPT_CFLAGS/NOOPT)

# Specify that the CPU is little endian, if necessary
ifeq ($(ZERO_ENDIANNESS), little)
  CFLAGS += -DVM_LITTLE_ENDIAN
endif

# Specify that the CPU is 64 bit, if necessary
ifeq ($(ARCH_DATA_MODEL), 64)
  CFLAGS += -D_LP64=1
endif

# Specify the path to the FFI headers
ifdef ALT_PACKAGE_PATH
  PACKAGE_PATH = $(ALT_PACKAGE_PATH)
else
  ifeq ($(OS_VENDOR),Apple)
    PACKAGE_PATH = /opt/local
  else
    ifeq ($(OS_VENDOR),NetBSD)
      PACKAGE_PATH = /usr/pkg
      LIBS += -Wl,-R${PACKAGE_PATH}/lib
    else
      PACKAGE_PATH = /usr/local
    endif
  endif
endif

CFLAGS += -I$(PACKAGE_PATH)/include
LIBS += -L$(PACKAGE_PATH)/lib -lffi

OPT_CFLAGS/compactingPermGenGen.o = -O1
