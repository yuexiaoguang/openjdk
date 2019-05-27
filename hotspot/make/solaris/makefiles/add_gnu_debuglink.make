
# Rules to build add_gnu_debuglink, used by vm.make on Solaris

# Allow $(ADD_GNU_DEBUGLINK) to be called from any directory.
# We don't set or use the GENERATED macro to avoid affecting
# other HotSpot Makefiles.
TOPDIR                    = $(shell echo `pwd`)
ADD_GNU_DEBUGLINK         = $(TOPDIR)/../generated/add_gnu_debuglink

ADD_GNU_DEBUGLINK_DIR     = $(GAMMADIR)/src/os/solaris/add_gnu_debuglink
ADD_GNU_DEBUGLINK_SRC     = $(ADD_GNU_DEBUGLINK_DIR)/add_gnu_debuglink.c
ADD_GNU_DEBUGLINK_FLAGS   = 
LIBS_ADD_GNU_DEBUGLINK   += -lelf

ifeq ("${Platform_compiler}", "sparcWorks")
# Enable the following ADD_GNU_DEBUGLINK_FLAGS addition if you need to
# compare the built ELF objects.
#
# The -g option makes static data global and the "-W0,-noglobal"
# option tells the compiler to not globalize static data using a unique
# globalization prefix. Instead force the use of a static globalization
# prefix based on the source filepath so the objects from two identical
# compilations are the same.
#
# Note: The blog says to use "-W0,-xglobalstatic", but that doesn't
#       seem to work. I got "-W0,-noglobal" from Kelly and that works.
#ADD_GNU_DEBUGLINK_FLAGS += -W0,-noglobal
endif # Platform_compiler == sparcWorks

$(ADD_GNU_DEBUGLINK): $(ADD_GNU_DEBUGLINK_SRC)
	$(CC) -g -o $@ $< $(ADD_GNU_DEBUGLINK_FLAGS) $(LIBS_ADD_GNU_DEBUGLINK)
