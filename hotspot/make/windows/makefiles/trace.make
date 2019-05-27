
# This makefile (trace.make) is included from the trace.make in the
# build directories.
#
# It knows how to build and run the tools to generate trace files.

!include $(WorkSpace)/make/windows/makefiles/rules.make

# #########################################################################


TraceAltSrcDir = $(WorkSpace)/src/closed/share/vm/trace
TraceSrcDir = $(WorkSpace)/src/share/vm/trace

TraceGeneratedNames =     \
    traceEventClasses.hpp \
    traceEventIds.hpp     \
    traceTypes.hpp

!if EXISTS($(TraceAltSrcDir))
TraceGeneratedNames = $(TraceGeneratedNames) \
    traceRequestables.hpp \
    traceEventControl.hpp \
    traceProducer.cpp
!endif


#Note: TraceGeneratedFiles must be kept in sync with TraceGeneratedNames by hand.
#Should be equivalent to "TraceGeneratedFiles = $(TraceGeneratedNames:%=$(TraceOutDir)/%)"
TraceGeneratedFiles = \
    $(TraceOutDir)/traceEventClasses.hpp \
	$(TraceOutDir)/traceEventIds.hpp     \
	$(TraceOutDir)/traceTypes.hpp

!if EXISTS($(TraceAltSrcDir))
TraceGeneratedFiles = $(TraceGeneratedFiles) \
	$(TraceOutDir)/traceRequestables.hpp \
    $(TraceOutDir)/traceEventControl.hpp \
	$(TraceOutDir)/traceProducer.cpp
!endif

XSLT = $(QUIETLY) $(REMOTE) $(RUN_JAVA) -classpath $(JvmtiOutDir) jvmtiGen

XML_DEPS = $(TraceSrcDir)/trace.xml $(TraceSrcDir)/tracetypes.xml \
    $(TraceSrcDir)/trace.dtd $(TraceSrcDir)/xinclude.mod

!if EXISTS($(TraceAltSrcDir))
XML_DEPS = $(XML_DEPS) $(TraceAltSrcDir)/traceevents.xml
!endif

.PHONY: all clean cleanall

# #########################################################################

default::
	@if not exist $(TraceOutDir) mkdir $(TraceOutDir)

$(TraceOutDir)/traceEventIds.hpp: $(TraceSrcDir)/trace.xml $(TraceSrcDir)/traceEventIds.xsl $(XML_DEPS)
	@echo Generating $@
	@$(XSLT) -IN $(TraceSrcDir)/trace.xml -XSL $(TraceSrcDir)/traceEventIds.xsl -OUT $(TraceOutDir)/traceEventIds.hpp

$(TraceOutDir)/traceTypes.hpp: $(TraceSrcDir)/trace.xml $(TraceSrcDir)/traceTypes.xsl $(XML_DEPS)
	@echo Generating $@
	@$(XSLT) -IN $(TraceSrcDir)/trace.xml -XSL $(TraceSrcDir)/traceTypes.xsl -OUT $(TraceOutDir)/traceTypes.hpp

!if !EXISTS($(TraceAltSrcDir))

$(TraceOutDir)/traceEventClasses.hpp: $(TraceSrcDir)/trace.xml $(TraceSrcDir)/traceEventClasses.xsl $(XML_DEPS)
	@echo Generating OpenJDK $@
	@$(XSLT) -IN $(TraceSrcDir)/trace.xml -XSL $(TraceSrcDir)/traceEventClasses.xsl -OUT $(TraceOutDir)/traceEventClasses.hpp

!else

$(TraceOutDir)/traceEventClasses.hpp: $(TraceSrcDir)/trace.xml $(TraceAltSrcDir)/traceEventClasses.xsl $(XML_DEPS)
	@echo Generating AltSrc $@
	@$(XSLT) -IN $(TraceSrcDir)/trace.xml -XSL $(TraceAltSrcDir)/traceEventClasses.xsl -OUT $(TraceOutDir)/traceEventClasses.hpp

$(TraceOutDir)/traceProducer.cpp: $(TraceSrcDir)/trace.xml $(TraceAltSrcDir)/traceProducer.xsl $(XML_DEPS)
	@echo Generating AltSrc $@
	@$(XSLT) -IN $(TraceSrcDir)/trace.xml -XSL $(TraceAltSrcDir)/traceProducer.xsl -OUT $(TraceOutDir)/traceProducer.cpp

$(TraceOutDir)/traceRequestables.hpp: $(TraceSrcDir)/trace.xml $(TraceAltSrcDir)/traceRequestables.xsl $(XML_DEPS)
	@echo Generating AltSrc $@
	@$(XSLT) -IN $(TraceSrcDir)/trace.xml -XSL $(TraceAltSrcDir)/traceRequestables.xsl -OUT $(TraceOutDir)/traceRequestables.hpp

$(TraceOutDir)/traceEventControl.hpp: $(TraceSrcDir)/trace.xml $(TraceAltSrcDir)/traceEventControl.xsl $(XML_DEPS)
	@echo Generating AltSrc $@
	@$(XSLT) -IN $(TraceSrcDir)/trace.xml -XSL $(TraceAltSrcDir)/traceEventControl.xsl -OUT $(TraceOutDir)/traceEventControl.hpp

!endif

# #########################################################################

cleanall :
	rm $(TraceGeneratedFiles)


