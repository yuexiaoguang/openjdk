#!/bin/sh

. `dirname $0`/saenv.sh

$SA_JAVA_CMD sun.jvm.hotspot.tools.soql.SOQL $*
