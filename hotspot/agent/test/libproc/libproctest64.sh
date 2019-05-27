#!/bin/ksh


# This script is used to run consistency check of Serviceabilty Agent
# after making any libproc.so changes. Prints "PASSED" or "FAILED" in
# standard output.

usage() {
    echo "usage: $0"
    echo "   set SA_JAVA to be the java executable from JDK 1.5"
    exit 1   
}

if [ "$1" == "-help" ]; then
    usage
fi

if [ "x$SA_JAVA" = "x" ]; then
   SA_JAVA=java
fi

STARTDIR=`dirname $0`

# create java process with test case
tmp=/tmp/libproctest
rm -f $tmp
$SA_JAVA -d64 -classpath $STARTDIR LibprocTest > $tmp &
pid=$!
while [ ! -s $tmp ] ; do
  # Kludge alert!
  sleep 2
done

# dump core
gcore $pid
kill -9 $pid

OPTIONS="-Djava.library.path=$STARTDIR/../src/os/solaris/proc/sparcv9:$STARTDIR/../solaris/sparcv9"

# run libproc client
$SA_JAVA -d64 -showversion ${OPTIONS} -cp $STARTDIR/../../build/classes::$STARTDIR/../sa.jar:$STARTDIR LibprocClient x core.$pid

# delete core
rm -f core.$pid
