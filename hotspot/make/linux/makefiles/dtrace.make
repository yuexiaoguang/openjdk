
# Linux does not build jvm_db
LIBJVM_DB =

# Only OPENJDK builds test and support SDT probes currently.
ifndef OPENJDK
REASON = "This JDK does not support SDT probes"
else

# We need a recent GCC for the default
ifeq "$(shell expr \( $(CC_VER_MAJOR) \>= 4 \) \& \( $(CC_VER_MINOR) \>= 4 \) )" "0"
REASON = "gcc version is too old"
else

# But it does have a SystemTap dtrace compatible sys/sdt.h
ifneq ($(ALT_SDT_H),)
  SDT_H_FILE = $(ALT_SDT_H)
else
  SDT_H_FILE = /usr/include/sys/sdt.h
endif
DTRACE_ENABLED = $(shell test -f $(SDT_H_FILE) && echo $(SDT_H_FILE))
REASON = "$(SDT_H_FILE) not found"

ifneq ($(DTRACE_ENABLED),)
  CFLAGS += -DDTRACE_ENABLED
endif

endif
endif

# Phony target used in vm.make build target to check whether enabled.
.PHONY: dtraceCheck
ifeq ($(DTRACE_ENABLED),)
dtraceCheck:
	$(QUIETLY) echo "**NOTICE** Dtrace support disabled: $(REASON)"
else
dtraceCheck:
endif

# It doesn't support HAVE_DTRACE_H though.

