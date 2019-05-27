#!/bin/sh

$JAVA_HOME/bin/java -d64 -showversion ${OPTIONS} -classpath $JAVA_HOME/lib/sa-jdi.jar sun.jvm.hotspot.tools.StackTrace $*
