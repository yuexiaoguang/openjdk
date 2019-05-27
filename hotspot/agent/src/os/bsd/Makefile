
ARCH := $(shell if ([ `uname -m` = "ia64" ])  ; then echo ia64 ; elif ([ `uname -m` = "amd64" ]) ; then echo amd64; elif ([ `uname -m` = "x86_64" ]) ; then echo amd64; elif ([ `uname -m` = "sparc64" ]) ; then echo sparc; else echo i386 ; fi )

OS       := $(shell uname -s)

GCC      = gcc

JAVAH    = ${JAVA_HOME}/bin/javah

ifneq ($(OS), Darwin)
SOURCES  = salibelf.c   \
        symtab.c        \
	libproc_impl.c  \
	ps_proc.c       \
	ps_core.c       \
	BsdDebuggerLocal.c
OBJS    = $(SOURCES:.c=.o)
OBJSPLUS = $(OBJS) sadis.o
LIBSA = $(ARCH)/libsaproc.so

LIBS     = -lutil -lthread_db

else

SOURCES  = symtab.c     \
	libproc_impl.c  \
	ps_core.c
OBJS    = $(SOURCES:.c=.o)
OBJSPLUS = MacosxDebuggerLocal.o sadis.o $(OBJS)
EXTINCLUDE = -I/System/Library/Frameworks/JavaVM.framework/Headers -I.
EXTCFLAGS = -m64 -D__APPLE__ -framework JavaNativeFoundation
FOUNDATIONFLAGS = -framework Foundation -F/System/Library/Frameworks/JavaVM.framework/Frameworks -framework JavaNativeFoundation -framework Security -framework CoreFoundation
LIBSA = $(ARCH)/libsaproc.dylib
endif   # Darwin

INCLUDES = -I${JAVA_HOME}/include -I${JAVA_HOME}/include/$(shell uname -s | tr "[:upper:]" "[:lower:]") $(EXTINCLUDE)



CFLAGS   = -c -fPIC -g -Wall -D_ALLBSD_SOURCE -D_GNU_SOURCE -D$(ARCH) $(INCLUDES) $(EXTCFLAGS)



all: $(LIBSA)

MacosxDebuggerLocal.o: MacosxDebuggerLocal.m
	echo "OS="$(OS)
	$(JAVAH) -jni -classpath ../../../build/classes  \
		sun.jvm.hotspot.debugger.x86.X86ThreadContext \
		sun.jvm.hotspot.debugger.amd64.AMD64ThreadContext
	$(GCC) $(CFLAGS) $(FOUNDATIONFLAGS) $<

sadis.o: ../../share/native/sadis.c
	$(JAVAH) -jni -classpath ../../../build/classes \
		sun.jvm.hotspot.asm.Disassembler
	$(GCC) $(CFLAGS) $<

.c.obj:
	$(GCC) $(CFLAGS)

ifndef LDNOMAP
  LFLAGS_LIBSA = -Xlinker --version-script=mapfile
endif

$(LIBSA): $(OBJSPLUS) mapfile 
	if [ ! -d $(ARCH) ] ; then mkdir $(ARCH) ; fi
	$(GCC) -shared $(LFLAGS_LIBSA) -o $(LIBSA) $(FOUNDATIONFLAGS) $(OBJSPLUS) $(LIBS) $(SALIBS)

test.o: $(LIBSA) test.c
	$(GCC) -c -o test.o -g -D_GNU_SOURCE -D$(ARCH) $(INCLUDES) test.c

test: test.o
	$(GCC) -o test test.o -L$(ARCH) -lsaproc $(LIBS)

clean:
	rm -f $(LIBSA)
	rm -f *.o
	rm -f test.o
	-rmdir $(ARCH)
