#!/bin/sh

. `dirname $0`/saenv64.sh

type c++filt 1>/dev/null 2>/dev/null
if [ $? -eq 0 ]; then
   $SA_JAVA_CMD sun.jvm.hotspot.tools.PStack $* | c++filt
else
   $SA_JAVA_CMD sun.jvm.hotspot.tools.PStack $*
fi

