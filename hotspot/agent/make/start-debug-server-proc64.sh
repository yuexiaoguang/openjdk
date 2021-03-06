#!/bin/sh

. `dirname $0`/saenv64.sh

if [ -f $STARTDIR/sa.jar ] ; then
  CP=$STARTDIR/sa.jar
else
  CP=$STARTDIR/../build/classes
fi

$SA_JAVA -d64 -classpath $CP ${OPTIONS} -Djava.rmi.server.codebase=file:/$CP -Djava.security.policy=$STARTDIR\/grantAll.policy sun.jvm.hotspot.DebugServer $*
