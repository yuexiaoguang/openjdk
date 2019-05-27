
TYPE=MINIMAL1

# Force all variables to false, overriding any other
# setting that may have occurred in the makefiles. These
# can still be overridden by passing the variable as an
# argument to 'make'
INCLUDE_JVMTI := false
INCLUDE_FPROF := false
INCLUDE_VM_STRUCTS := false
INCLUDE_JNI_CHECK := false
INCLUDE_SERVICES := false
INCLUDE_MANAGEMENT := false
INCLUDE_ALL_GCS := false
INCLUDE_NMT := false
INCLUDE_TRACE := false
INCLUDE_CDS := false

CXXFLAGS += -DMINIMAL_JVM -DCOMPILER1 -DVMTYPE=\"Minimal\"
CFLAGS += -DMINIMAL_JVM -DCOMPILER1 -DVMTYPE=\"Minimal\"

Src_Dirs/MINIMAL1 = $(CORE_PATHS) $(COMPILER1_PATHS)

Src_Files_EXCLUDE/MINIMAL1 += $(COMPILER2_SPECIFIC_FILES) $(ZERO_SPECIFIC_FILES) $(SHARK_SPECIFIC_FILES) ciTypeFlow.cpp

-include $(HS_ALT_MAKE)/$(OSNAME)/makefiles/minimal1.make

.PHONY: $(HS_ALT_MAKE)/$(OSNAME)/makefiles/minimal1.make
