
# top.make is included in the Makefile in the build directories.
# It DOES NOT include the vm dependency info in order to be faster.
# Its main job is to implement the incremental form of make lists.
# It also:
#   -builds and runs adlc via adlc.make
#   -generates JVMTI source and docs via jvmti.make (JSR-163)
#   -generate sa-jdi.jar (JDI binding to core files)

# It assumes the following flags are set:
# CFLAGS Platform_file, Src_Dirs_I, Src_Dirs_V, SYSDEFS, AOUT, Jvm_Obj_Files

# -- D. Ungar (5/97) from a file by Bill Bush

# Don't override the built-in $(MAKE).
# Instead, use "gmake" (or "gnumake") from the command line.  --Rose
#MAKE = gmake

include $(GAMMADIR)/make/altsrc.make

GENERATED   = ../generated
VM          = $(GAMMADIR)/src/share/vm
Plat_File   = $(Platform_file)
CDG         = cd $(GENERATED); 

Cached_plat = $(GENERATED)/platform.current

AD_Dir   = $(GENERATED)/adfiles
ADLC     = $(AD_Dir)/adlc
AD_Spec  = $(call altsrc-replace,$(HS_COMMON_SRC)/cpu/$(Platform_arch)/vm/$(Platform_arch_model).ad)
AD_Src   = $(call altsrc-replace,$(HS_COMMON_SRC)/share/vm/adlc)
AD_Names = ad_$(Platform_arch_model).hpp ad_$(Platform_arch_model).cpp
AD_Files = $(AD_Names:%=$(AD_Dir)/%)

# AD_Files_If_Required/COMPILER1 = ad_stuff
AD_Files_If_Required/COMPILER2 = ad_stuff
AD_Files_If_Required/TIERED = ad_stuff
AD_Files_If_Required = $(AD_Files_If_Required/$(TYPE))

# Wierd argument adjustment for "gnumake -j..."
adjust-mflags   = $(GENERATED)/adjust-mflags
MFLAGS-adjusted = -r `$(adjust-mflags) "$(MFLAGS)" "$(HOTSPOT_BUILD_JOBS)"`


# default target: update lists, make vm
# done in stages to force sequential order with parallel make
#

default: vm_build_preliminaries the_vm
	@echo All done.

# This is an explicit dependency for the sake of parallel makes.
vm_build_preliminaries:  checks $(Cached_plat) $(AD_Files_If_Required) jvmti_stuff trace_stuff sa_stuff
	@# We need a null action here, so implicit rules don't get consulted.

$(Cached_plat): $(Plat_File)
	$(CDG) cp $(Plat_File) $(Cached_plat)

# make AD files as necessary
ad_stuff: $(Cached_plat) $(adjust-mflags)
	@$(MAKE) -f adlc.make $(MFLAGS-adjusted)

# generate JVMTI files from the spec
jvmti_stuff: $(Cached_plat) $(adjust-mflags)
	@$(MAKE) -f jvmti.make $(MFLAGS-adjusted)

# generate trace files 
trace_stuff: jvmti_stuff $(Cached_plat) $(adjust-mflags)
	@$(MAKE) -f trace.make $(MFLAGS-adjusted)

# generate SA jar files and native header
sa_stuff:
	@$(MAKE) -f sa.make $(MFLAGS-adjusted)

# and the VM: must use other makefile with dependencies included

# We have to go to great lengths to get control over the -jN argument
# to the recursive invocation of vm.make.  The problem is that gnumake
# resets -jN to -j1 for recursive runs.  (How helpful.)
# Note that the user must specify the desired parallelism level via a
# command-line or environment variable name HOTSPOT_BUILD_JOBS.
$(adjust-mflags): $(GAMMADIR)/make/$(Platform_os_family)/makefiles/adjust-mflags.sh
	@+rm -f $@ $@+
	@+cat $< > $@+
	@+chmod +x $@+
	@+mv $@+ $@

the_vm: vm_build_preliminaries $(adjust-mflags)
	@$(MAKE) -f vm.make $(MFLAGS-adjusted)

install gamma: the_vm
	@$(MAKE) -f vm.make $@

# next rules support "make foo.[oi]"

%.o %.i %.s:
	$(MAKE) -f vm.make $(MFLAGS) $@
	#$(MAKE) -f vm.make $@

# this should force everything to be rebuilt
clean: 
	rm -f $(GENERATED)/*.class
	$(MAKE) -f vm.make $(MFLAGS) clean

# just in case it doesn't, this should do it
realclean:
	$(MAKE) -f vm.make $(MFLAGS) clean
	rm -fr $(GENERATED)

.PHONY: default vm_build_preliminaries
.PHONY: lists ad_stuff jvmti_stuff trace_stuff sa_stuff the_vm clean realclean
.PHONY: checks check_os_version install
