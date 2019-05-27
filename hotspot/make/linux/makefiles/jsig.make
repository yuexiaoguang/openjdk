
# Rules to build signal interposition library, used by vm.make

# libjsig.so: signal interposition library
JSIG = jsig
LIBJSIG = lib$(JSIG).so

LIBJSIG_DEBUGINFO   = lib$(JSIG).debuginfo
LIBJSIG_DIZ         = lib$(JSIG).diz

JSIGSRCDIR = $(GAMMADIR)/src/os/$(Platform_os_family)/vm

DEST_JSIG           = $(JDK_LIBDIR)/$(LIBJSIG)
DEST_JSIG_DEBUGINFO = $(JDK_LIBDIR)/$(LIBJSIG_DEBUGINFO)
DEST_JSIG_DIZ       = $(JDK_LIBDIR)/$(LIBJSIG_DIZ)

LIBJSIG_MAPFILE = $(MAKEFILES_DIR)/mapfile-vers-jsig

# On Linux we really dont want a mapfile, as this library is small 
# and preloaded using LD_PRELOAD, making functions private will 
# cause problems with interposing. See CR: 6466665
# LFLAGS_JSIG += $(MAPFLAG:FILENAME=$(LIBJSIG_MAPFILE))

LFLAGS_JSIG += -D_GNU_SOURCE -D_REENTRANT $(LDFLAGS_HASH_STYLE)

# DEBUG_BINARIES overrides everything, use full -g debug information
ifeq ($(DEBUG_BINARIES), true)
  JSIG_DEBUG_CFLAGS = -g
endif

$(LIBJSIG): $(JSIGSRCDIR)/jsig.c $(LIBJSIG_MAPFILE)
	@echo Making signal interposition lib...
	$(QUIETLY) $(CC) $(SYMFLAG) $(ARCHFLAG) $(SHARED_FLAG) $(PICFLAG) \
                         $(LFLAGS_JSIG) $(JSIG_DEBUG_CFLAGS) $(EXTRA_CFLAGS) -o $@ $< -ldl
ifeq ($(ENABLE_FULL_DEBUG_SYMBOLS),1)
	$(QUIETLY) $(OBJCOPY) --only-keep-debug $@ $(LIBJSIG_DEBUGINFO)
	$(QUIETLY) $(OBJCOPY) --add-gnu-debuglink=$(LIBJSIG_DEBUGINFO) $@
  ifeq ($(STRIP_POLICY),all_strip)
	$(QUIETLY) $(STRIP) $@
  else
    ifeq ($(STRIP_POLICY),min_strip)
	$(QUIETLY) $(STRIP) -g $@
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
