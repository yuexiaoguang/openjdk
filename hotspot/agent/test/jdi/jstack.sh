#!/bin/sh

$JAVA_HOME/bin/java -showversion ${OPTIONS} -classpath $JAVA_HOME/lib/sa-jdi.jar sun.jvm.hotspot.tools.StackTrace $*
