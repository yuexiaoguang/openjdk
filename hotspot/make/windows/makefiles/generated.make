
!include ../local.make
!include $(WorkSpace)/make/windows/makefiles/projectcreator.make
!include local.make

# Pick up rules for building JVMTI (JSR-163)
JvmtiOutDir=jvmtifiles
!include $(WorkSpace)/make/windows/makefiles/jvmti.make

# Pick up rules for building trace
TraceOutDir=tracefiles
!include $(WorkSpace)/make/windows/makefiles/trace.make

# Pick up rules for building SA
!include $(WorkSpace)/make/windows/makefiles/sa.make

AdlcOutDir=adfiles

!if ("$(Variant)" == "compiler2") || ("$(Variant)" == "tiered")
default:: $(AdlcOutDir)/ad_$(Platform_arch_model).cpp $(AdlcOutDir)/dfa_$(Platform_arch_model).cpp $(JvmtiGeneratedFiles) $(TraceGeneratedFiles) buildobjfiles
!else
default:: $(JvmtiGeneratedFiles) $(TraceGeneratedFiles) buildobjfiles
!endif

buildobjfiles:
	@ sh $(WorkSpace)/make/windows/create_obj_files.sh $(Variant) $(Platform_arch) $(Platform_arch_model) $(WorkSpace) .	> objfiles.make

classes/ProjectCreator.class: $(ProjectCreatorSources)
	if exist classes rmdir /s /q classes
	mkdir classes
	$(COMPILE_JAVAC) -classpath $(WorkSpace)\src\share\tools\ProjectCreator -d classes $(ProjectCreatorSources)

!if ("$(Variant)" == "compiler2") || ("$(Variant)" == "tiered")

!include $(WorkSpace)/make/windows/makefiles/compile.make
!include $(WorkSpace)/make/windows/makefiles/adlc.make

!endif

!include $(WorkSpace)/make/windows/makefiles/shared.make
