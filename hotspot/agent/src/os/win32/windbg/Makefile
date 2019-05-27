
# set WINDBG_HOME and JAVA_HOME environment variables before this make.

SAWINDBGDLL = sawindbg.dll
CPP32=cl.exe
CPP64=cl.exe
LINK32=link.exe
LINK64=link.exe
JAVAH=$(JAVA_HOME)/bin/javah
WINDBG_INCLUDE=$(WINDBG_HOME)/sdk/inc
WINDBG_LIB32=$(WINDBG_HOME)/sdk/lib/i386
WINDBG_LIB_IA64=$(WINDBG_HOME)/sdk/lib/ia64
WINDBG_LIB_AMD64=$(WINDBG_HOME)/sdk/lib/amd64

SADIS=../../../share/native/sadis.c

# These do not need to be optimized (don't run a lot of code) and it
# will be useful to have the assertion checks in place

CFLAGS32=/nologo /MD /W3 /Gm /GX /ZI /Od /D "WIN32" /D "_WINDOWS" /D "_DEBUG" /D "_CONSOLE" /D "_MBCS" /YX /FD /GZ /c

CFLAGS64=/nologo /MD /W3 /GX /Od /D "WIN32" /D "WIN64" /D "_WINDOWS" /D "_DEBUG" /D "_CONSOLE" /D "_MBCS" /YX /FD /c

LIBS32= $(WINDBG_LIB32)/dbgeng.lib  \
     /nologo /subsystem:console /debug /machine:I386

LIBS_IA64= $(WINDBG_LIB_IA64)/dbgeng.lib  \
     /nologo /subsystem:console /debug /machine:IA64

LIBS_AMD64= $(WINDBG_LIB_AMD64)/dbgeng.lib bufferoverflowU.lib \
     /nologo /subsystem:console /debug /machine:AMD64

default: i386/$(SAWINDBGDLL)

ia64: ia64/$(SAWINDBGDLL)

amd64: amd64/$(SAWINDBGDLL)

i386/$(SAWINDBGDLL) : sawindbg.cpp $(SADIS)
	@ mkdir -p i386
	@ $(JAVAH) -jni -classpath ../../../../build/classes sun.jvm.hotspot.debugger.windbg.WindbgDebuggerLocal sun.jvm.hotspot.debugger.x86.X86ThreadContext
	@ $(JAVAH) -jni -classpath ../../../../build/classes sun.jvm.hotspot.asm.Disassembler
	@ $(CPP32) /I$(JAVA_HOME)/include /I$(JAVA_HOME)/include/win32 /I$(WINDBG_INCLUDE) $(CFLAGS32) /Fp"i386/sawindbg.pch" /Fo"i386/" /Fd"i386/" /c sawindbg.cpp
	@ $(CPP32) /I$(JAVA_HOME)/include /I$(JAVA_HOME)/include/win32 /I$(WINDBG_INCLUDE) $(CFLAGS32) /Fp"i386/sadis.pch" /Fo"i386/" /Fd"i386/" /c  $(SADIS)
	$(LINK32) /out:$@ /DLL i386/sawindbg.obj i386/sadis.obj $(LIBS32) 

ia64/$(SAWINDBGDLL) : sawindbg.cpp $(SADIS)
	@ mkdir -p ia64
	@ $(JAVAH) -jni -classpath ../../../../build/classes sun.jvm.hotspot.debugger.windbg.WindbgDebuggerLocal sun.jvm.hotspot.debugger.ia64.IA64ThreadContext
	@ $(JAVAH) -jni -classpath ../../../../build/classes sun.jvm.hotspot.asm.Disassembler
	@ $(CPP64) /I$(JAVA_HOME)/include /I$(JAVA_HOME)/include/win32 /I$(WINDBG_INCLUDE) $(CFLAGS64) /Fp"ia64/sawindbg.pch" /Fo"ia64/" /Fd"ia64/" /c sawindbg.cpp
	@ $(CPP64) /I$(JAVA_HOME)/include /I$(JAVA_HOME)/include/win32 /I$(WINDBG_INCLUDE) $(CFLAGS64) /Fp"ia64/sadis.pch" /Fo"ia64/" /Fd"ia64/" /c $(SADIS)
	$(LINK64) /out:$@ /DLL ia64/sawindbg.obj ia64/sadis.obj $(LIBS_IA64)

amd64/$(SAWINDBGDLL) : sawindbg.cpp $(SADIS)
	@ mkdir -p amd64
	@ $(JAVAH) -jni -classpath ../../../../build/classes sun.jvm.hotspot.debugger.windbg.WindbgDebuggerLocal sun.jvm.hotspot.debugger.amd64.AMD64ThreadContext
	@ $(JAVAH) -jni -classpath ../../../../build/classes sun.jvm.hotspot.asm.Disassembler
	@ $(CPP64) /I$(JAVA_HOME)/include /I$(JAVA_HOME)/include/win32 /I$(WINDBG_INCLUDE) $(CFLAGS64) /Fp"amd64/sawindbg.pch" /Fo"amd64/" /Fd"amd64/" /c sawindbg.cpp
	@ $(CPP64) /I$(JAVA_HOME)/include /I$(JAVA_HOME)/include/win32 /I$(WINDBG_INCLUDE) $(CFLAGS64) /Fp"amd64/sadis.pch" /Fo"amd64/" /Fd"amd64/" /c $(SADIS)
	$(LINK64) /out:$@ /DLL amd64/sawindbg.obj amd64/sadis.obj $(LIBS_AMD64)

clean:
	rm *.h
	rm -rf i386
	rm -rf ia64
	rm -rf amd64

