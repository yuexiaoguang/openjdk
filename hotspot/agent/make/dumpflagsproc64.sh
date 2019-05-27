#!/bin/sh

. `dirname $0`/saenv64.sh

$SA_JAVA_CMD sun.jvm.hotspot.tools.FlagDumper $*
