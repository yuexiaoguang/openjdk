#!/bin/ksh

# This script runs the test program, sagtest.java, with the regular
# JPDA jdi.
# It then starts up the debuggee part of the test, sagtarg.java,
# and calls gcore to create file sagcore for use in running
# the SA JDI client.

set -x
# jdk is a jdk with the vm from the sa workspace
while [ $# != 0 ] ; do
    case $1 in
      -vv)
        set -x
        ;;
      -gui)
        theClass=sun.jvm.hotspot.HSDB
        ;;
     -jdk)
        jdk=$2
        shift
        ;;
     -jdbx)
        do=jdbx
        ;;
     -jdb)
        do=jdb
        ;;
     -help | help)
        doUsage
        exit
        ;;
     -dontkill)
        dontkill=true
        ;;
     -d64)
        d64=-d64
        ;;
     -*)
        javaArgs="$javaArgs $1"
        ;;
     *)
        echo "$1" | grep -s '^[0-9]*$' > /dev/null
        if [ $? = 0 ] ; then
            # it is a pid
            args="$args $1"
        else
            # It is a core.        
            # We have to pass the name of the program that produced the
            # core, and the core file itself.
            args="$jdk/bin/java $1"
        fi
        ;;
   esac
   shift
done

# First, run the sagtest.java with the regular JPDA jdi
workdir=./workdir
mkdir -p $workdir
CLASSPATH=$jdk/classes:$jdk/lib/tools.jar:$workdir
export CLASSPATH

$jdk/bin/javac -g  -source 1.5 -classpath $jdk/classes:$jdk/lib/tools.jar:$workdir -J-Xms40m -d $workdir \
    TestScaffold.java \
    VMConnection.java \
    TargetListener.java \
    TargetAdapter.java \
    sagdoit.java \
    sagtarg.java \
    sagtest.java

if [ $? != 0 ] ; then
    exit 1
fi

$jdk/bin/java $javaArgs -Dtest.classes=$workdir sagtest

# Now run create a core file for use in running sa-jdi

if [ ! core.satest -nt sagtarg.class ] ; then
    tmp=/tmp/sagsetup
    rm -f $tmp
    $jdk/bin/java $d64 sagtarg > $tmp &
    pid=$!
    while [ ! -s $tmp ] ; do
        # Kludge alert!
        sleep 2
    done
    #rm -f $tmp

    # force core dump of the debuggee
    OS=`uname`
    if [ "$OS" = "Linux" ]; then
        # Linux does not have gcore command. Instead, we use 'gdb's
        # gcore command. Note that only some versions of gdb support
        # gdb command.
        echo "gcore" > gdbscript
        gdb -batch -p $pid -x gdbscript
        rm -f gdbscript
    else
        gcore  $* $pid
    fi
    mv core.$pid sagcore

    if [ "$dontkill" != "true" ]; then
       kill -9 $pid
    fi
fi

