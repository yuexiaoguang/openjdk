#!/bin/sh
## some tests require path to find test source dir
if [ "${TESTSRC}" = "" ]
then
  TESTSRC=${PWD}
  echo "TESTSRC not set.  Using "${TESTSRC}" as default"
fi
echo "TESTSRC=${TESTSRC}"
## Adding common setup Variables for running shell tests.
. ${TESTSRC}/../../test_env.sh

# Amount of physical memory in megabytes
MEM=0
if [ -f "/proc/meminfo" ]; then
  # Linux, Windows/Cygwin
  MEM=`cat /proc/meminfo |grep ^MemTotal: | awk '{print $2}'`
  MEM="$(($MEM / 1024))"
elif [ -x "/usr/sbin/prtconf" ]; then
  # Solaris
  MEM=`/usr/sbin/prtconf | grep "^Memory size" | awk '{print $3}'`
elif [ -x "/usr/sbin/system_profiler" ]; then
  # MacOS
  MEMo=`/usr/sbin/system_profiler SPHardwareDataType | grep Memory:`
  MEM=`echo "$MEMo" | awk '{print $2}'`
  MEMu=`echo "$MEMo" | awk '{print $3}'`
  case $MEMu in
  GB)
    MEM="$(($MEM * 1024))"
    ;;
  MB)
    ;;
  *)
    echo "Unknown memory unit in system_profile output: $MEMu"
    ;;
  esac
elif [ -n "$ROOTDIR" -a -x "$ROOTDIR/mksnt/sysinf" ]; then
  # Windows/MKS
  MEM=`"$ROOTDIR/mksnt/sysinf" memory -v | grep "Total Physical Memory: " | sed 's/Total Physical Memory: *//g'`
  MEM="$(($machine_memory / 1024))"
else
  echo "Unable to determine amount of physical memory on the machine"
fi

if [ $MEM -lt 2000 ]; then
  echo "Test skipped due to low (or unknown) memory on the system: $MEM Mb"
  exit 0
fi

echo "MEMORY=$MEM Mb"

set -x

cp ${TESTSRC}/Test7005594.java .
cp ${TESTSRC}/Test7005594.sh .

${COMPILEJAVA}/bin/javac ${TESTJAVACOPTS} -d . Test7005594.java

${TESTJAVA}/bin/java ${TESTVMOPTS} -Xms1600m -XX:+IgnoreUnrecognizedVMOptions -XX:-ZapUnusedHeapArea -Xcomp -XX:CompileOnly=Test7005594.test Test7005594 > test.out 2>&1

result=$?

cat test.out

if [ $result -eq 95 ]
then
  echo "Passed"
  exit 0
fi

if [ $result -eq 97 ]
then
  echo "Failed"
  exit 1
fi

# The test should pass when no enough space for object heap
grep "Could not reserve enough space for object heap" test.out
if [ $? = 0 ]
then
  echo "Passed"
  exit 0
else
  echo "Failed"
  exit 1
fi
