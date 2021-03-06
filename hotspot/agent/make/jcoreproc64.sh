#!/bin/sh

. `dirname $0`/saenv64.sh

# set the environment variable JCORE_PACKAGES to a comma separated list of
# packages whose classes have to be retrieved from the core file.

$SA_JAVA_CMD -Dsun.jvm.hotspot.tools.jcore.filter=sun.jvm.hotspot.tools.jcore.PackageNameFilter -Dsun.jvm.hotspot.tools.jcore.PackageNameFilter.pkgList=$JCORE_PACKAGES sun.jvm.hotspot.tools.jcore.ClassDump $*
