#!/bin/sh

# This file sets common environment variables for all SA scripts

OS=`uname`
STARTDIR=`(cd \`dirname $0 \`; pwd)`
ARCH=`uname -m`

if [ "x$SA_JAVA" = "x" ]; then
   SA_JAVA=java
fi

if [ "$OS" = "Linux" ]; then
   if [ "$ARCH" = "ia64" ] ; then
     SA_LIBPATH=$STARTDIR/../src/os/linux/ia64:$STARTDIR/linux/ia64
     OPTIONS="-Dsa.library.path=$SA_LIBPATH"
     CPU=ia64
   elif [ "$ARCH" = "x86_64" ] ; then 
     SA_LIBPATH=$STARTDIR/../src/os/linux/amd64:$STARTDIR/linux/amd64
     OPTIONS="-Dsa.library.path=$SA_LIBPATH"
     CPU=amd64
   else
     SA_LIBPATH=$STARTDIR/../src/os/linux/i386:$STARTDIR/linux/i386
     OPTIONS="-Dsa.library.path=$SA_LIBPATH"
     CPU=i386
   fi
else
   # configure audit helper library if SA_ALTROOT is set
   if [ -n "$SA_ALTROOT" ]; then
     LD_AUDIT_32=$STARTDIR/../src/os/solaris/proc/`uname -p`/libsaproc_audit.so
     export LD_AUDIT_32
     if [ ! -f $LD_AUDIT_32 ]; then
       echo "SA_ALTROOT is set and can't find libsaproc_audit.so."
       echo "Make sure to build it with 'make natives'."
       exit 1
     fi
   fi
   SA_LIBPATH=$STARTDIR/../src/os/solaris/proc/`uname -p`:$STARTDIR/solaris/`uname -p`
   OPTIONS="-Dsa.library.path=$SA_LIBPATH -Dsun.jvm.hotspot.debugger.useProcDebugger"
   CPU=sparc
fi

if [ "x$SA_DISABLE_VERS_CHK" != "x" ]; then
   OPTIONS="-Dsun.jvm.hotspot.runtime.VM.disableVersionCheck ${OPTIONS}"
fi


SA_CLASSPATH=$STARTDIR/../build/classes:$STARTDIR/../src/share/lib/js.jar:$STARTDIR/sa.jar:$STARTDIR/lib/js.jar

if [ ! -z "$SA_TYPEDB" ]; then
  if [ ! -f $SA_TYPEDB ]; then
    echo "$SA_TYPEDB is unreadable"
    exit 1
  fi
  OPTIONS="-Dsun.jvm.hotspot.typedb=$SA_TYPEDB ${OPTIONS}"
fi

OPTIONS="-Djava.system.class.loader=sun.jvm.hotspot.SALauncherLoader ${OPTIONS}"

SA_JAVA_CMD="$SA_PREFIX_CMD $SA_JAVA -showversion ${OPTIONS} -cp $SA_CLASSPATH $SA_OPTIONS"
