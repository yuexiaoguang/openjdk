
# Must also specify if CPU is little endian
CFLAGS += -DVM_LITTLE_ENDIAN

# TLS helper, assembled from .s file

#
# Special case flags for compilers and compiler versions on i486.
#
ifeq ("${Platform_compiler}", "sparcWorks")
# ILD is gone as of SS11 (5.8), not supported in SS10 (5.7)
ifeq ($(shell expr $(COMPILER_REV_NUMERIC) \< 507), 1)
  #
  # Bug in ild causes it to fail randomly. Until we get a fix we can't
  # use ild.
  #
  ILDFLAG/debug     = -xildoff
endif
endif
