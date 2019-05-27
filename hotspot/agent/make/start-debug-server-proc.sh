#!/bin/sh

. `dirname $0`/saenv.sh

if [ -f $STARTDIR/../lib/sa-jdi.jar ] ; then
  CP=$STARTDIR/../lib/sa-jdi.jar
else
  CP=$STARTDIR/../build/classes
fi

$STARTDIR/java -classpath $CP ${OPTIONS} -Djava.rmi.server.codebase=file://$CP -Djava.security.policy=${STARTDIR}/grantAll.policy sun.jvm.hotspot.DebugServer $*

