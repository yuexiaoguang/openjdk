
# Rules to build fix_empty_sec_hdr_flags, used by vm.make on Solaris

# Allow $(FIX_EMPTY_SEC_HDR_FLAGS) to be called from any directory.
# We don't set or use the GENERATED macro to avoid affecting
# other HotSpot Makefiles.
TOPDIR                          = $(shell echo `pwd`)
FIX_EMPTY_SEC_HDR_FLAGS         = $(TOPDIR)/../generated/fix_empty_sec_hdr_flags

FIX_EMPTY_SEC_HDR_FLAGS_DIR     = $(GAMMADIR)/src/os/solaris/fix_empty_sec_hdr_flags
FIX_EMPTY_SEC_HDR_FLAGS_SRC     = $(FIX_EMPTY_SEC_HDR_FLAGS_DIR)/fix_empty_sec_hdr_flags.c
FIX_EMPTY_SEC_HDR_FLAGS_FLAGS   = 
LIBS_FIX_EMPTY_SEC_HDR_FLAGS   += -lelf

ifeq ("${Platform_compiler}", "sparcWorks")
# Enable the following FIX_EMPTY_SEC_HDR_FLAGS_FLAGS addition if you need to
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
#FIX_EMPTY_SEC_HDR_FLAGS_FLAGS += -W0,-noglobal
endif # Platform_compiler == sparcWorks

$(FIX_EMPTY_SEC_HDR_FLAGS): $(FIX_EMPTY_SEC_HDR_FLAGS_SRC)
	$(CC) -g -o $@ $< $(FIX_EMPTY_SEC_HDR_FLAGS_FLAGS) $(LIBS_FIX_EMPTY_SEC_HDR_FLAGS)
