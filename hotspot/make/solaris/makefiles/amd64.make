
# Must also specify if CPU is little endian
CFLAGS += -DVM_LITTLE_ENDIAN

#
# Special case flags for compilers and compiler versions on amd64.
#
ifeq ("${Platform_compiler}", "sparcWorks")

# Temporary until SS10 C++ compiler is fixed
OPT_CFLAGS/generateOptoStub.o = -xO2
# Temporary util SS12u1 C++ compiler is fixed
OPT_CFLAGS/c1_LinearScan.o = -xO2
else

ifeq ("${Platform_compiler}", "gcc")
# gcc
# The serviceability agent relies on frame pointer (%rbp) to walk thread stack
CFLAGS += -fno-omit-frame-pointer

else
# error
_JUNK2_ := $(shell echo >&2 \
       "*** ERROR: this compiler is not yet supported by this code base!")
       @exit 1
endif
endif
