#!/bin/sh

STARTDIR=`dirname $0`

if [ -f $STARTDIR/sa.jar ] ; then
  CP=$STARTDIR/sa.jar
else
  CP=$STARTDIR/../build/classes
fi

rmiregistry -J-Xbootclasspath/p:$CP
