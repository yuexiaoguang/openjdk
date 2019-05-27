
# Rules to build signal interposition library, used by vm.make

# libjsig.so: signal interposition library
JSIG      = jsig
LIBJSIG   = lib$(JSIG).so

LIBJSIG_DEBUGINFO   = lib$(JSIG).debuginfo
LIBJSIG_DIZ         = lib$(JSIG).diz

JSIGSRCDIR = $(GAMMADIR)/src/os/$(Platform_os_family)/vm

DEST_JSIG           = $(JDK_LIBDIR)/$(LIBJSIG)
DEST_JSIG_DEBUGINFO = $(JDK_LIBDIR)/$(LIBJSIG_DEBUGINFO)
DEST_JSIG_DIZ       = $(JDK_LIBDIR)/$(LIBJSIG_DIZ)

LIBJSIG_MAPFILE = $(MAKEFILES_DIR)/mapfile-vers-jsig

LFLAGS_JSIG += $(MAPFLAG:FILENAME=$(LIBJSIG_MAPFILE))

ifdef USE_GCC
LFLAGS_JSIG += -D_REENTRANT
else
LFLAGS_JSIG += -mt -xnolib
endif

$(LIBJSIG): $(ADD_GNU_DEBUGLINK) $(FIX_EMPTY_SEC_HDR_FLAGS) $(JSIGSRCDIR)/jsig.c $(LIBJSIG_MAPFILE)
	@echo Making signal interposition lib...
	$(QUIETLY) $(CC) $(SYMFLAG) $(ARCHFLAG) $(SHARED_FLAG) $(PICFLAG) \
                         $(LFLAGS_JSIG) -o $@ $(JSIGSRCDIR)/jsig.c -ldl
ifeq ($(ENABLE_FULL_DEBUG_SYMBOLS),1)
# gobjcopy crashes on "empty" section headers with the SHF_ALLOC flag set.
# Clear the SHF_ALLOC flag (if set) from empty section headers.
# An empty section header has sh_addr == 0 and sh_size == 0.
# This problem has only been seen on Solaris X64, but we call this tool
# on all Solaris builds just in case.
	$(QUIETLY) $(FIX_EMPTY_SEC_HDR_FLAGS) $@
	$(QUIETLY) $(OBJCOPY) --only-keep-debug $@ $(LIBJSIG_DEBUGINFO)
# $(OBJCOPY) --add-gnu-debuglink=... corrupts SUNW_* sections.
# Use $(ADD_GNU_DEBUGLINK) until a fixed $(OBJCOPY) is available.
#	$(QUIETLY) $(OBJCOPY) --add-gnu-debuglink=$(LIBJSIG_DEBUGINFO) $@
	$(QUIETLY) $(ADD_GNU_DEBUGLINK) $(LIBJSIG_DEBUGINFO) $@
  ifeq ($(STRIP_POLICY),all_strip)
	$(QUIETLY) $(STRIP) $@
  else
    ifeq ($(STRIP_POLICY),min_strip)
	$(QUIETLY) $(STRIP) -x $@
    # implied else here is no stripping at all
    endif
  endif
  ifeq ($(ZIP_DEBUGINFO_FILES),1)
	$(ZIPEXE) -q -y $(LIBJSIG_DIZ) $(LIBJSIG_DEBUGINFO)
	$(RM) $(LIBJSIG_DEBUGINFO)
  endif
endif

install_jsig: $(LIBJSIG)
	@echo "Copying $(LIBJSIG) to $(DEST_JSIG)"
	$(QUIETLY) test -f $(LIBJSIG_DEBUGINFO) && \
	    cp -f $(LIBJSIG_DEBUGINFO) $(DEST_JSIG_DEBUGINFO)
	$(QUIETLY) test -f $(LIBJSIG_DIZ) && \
	    cp -f $(LIBJSIG_DIZ) $(DEST_JSIG_DIZ)
	$(QUIETLY) cp -f $(LIBJSIG) $(DEST_JSIG) && echo "Done"

.PHONY: install_jsig
