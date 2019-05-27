
# A list of object files built without the platform specific PIC flags, e.g.
# -fPIC on linux. Performance measurements show that by compiling GC related 
# code, we could significantly reduce the GC pause time on 32 bit Linux/Unix
# platforms. See 6454213 for more details.
include $(GAMMADIR)/make/scm.make

ifneq ($(OSNAME), windows)
  ifndef LP64
    PARTIAL_NONPIC=1
  endif
  PIC_ARCH = ppc arm
  ifneq ("$(filter $(PIC_ARCH),$(BUILDARCH))","")
    PARTIAL_NONPIC=0
  endif
  ifeq ($(PARTIAL_NONPIC),1)
    NONPIC_DIRS  = memory oops gc_implementation gc_interface 
    NONPIC_DIRS  := $(foreach dir,$(NONPIC_DIRS), $(GAMMADIR)/src/share/vm/$(dir))
    # Look for source files under NONPIC_DIRS
    NONPIC_FILES := $(foreach dir,$(NONPIC_DIRS),\
                      $(shell find $(dir) \( $(SCM_DIRS) \) -prune -o \
		      -name '*.cpp' -print))
    NONPIC_OBJ_FILES := $(notdir $(subst .cpp,.o,$(NONPIC_FILES)))
  endif
endif
