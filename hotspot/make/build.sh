#! /bin/sh

# Make sure the variable JAVA_HOME is set before running this script.

set -u


if [ $# -lt 1 ]; then 
    echo "Usage : $0 BuildTarget [LP64=1] [BuildOptions]"
    echo "               Server VM | Client VM"
    echo "BuildTarget :  debug     | debug1"
    echo "               fastdebug | fastdebug1"
    echo "               jvmg      | jvmg1"
    echo "               optimized | optimized1"
    echo "               profiled  | profiled1"
    echo "               product   | product1"
    exit 1
fi

if [ "${JAVA_HOME-}" = ""  -o  ! -d "${JAVA_HOME-}" -o ! -d ${JAVA_HOME-}/jre/lib/ ]; then
    echo "JAVA_HOME needs to be set to a valid JDK path"
    echo "JAVA_HOME: ${JAVA_HOME-}"
    exit 1
fi

# Just in case:
JAVA_HOME=`( cd $JAVA_HOME; pwd )`

if [ "${ALT_BOOTDIR-}" = ""  -o  ! -d "${ALT_BOOTDIR-}" -o ! -d ${ALT_BOOTDIR-}/jre/lib/ ]; then
    ALT_BOOTDIR=${JAVA_HOME}
fi

# build in current directory by default
if [ "${ALT_OUTPUTDIR-}" = ""  -o  ! -d "${ALT_OUTPUTDIR-}" ]; then
    ALT_OUTPUTDIR=`(pwd)`
fi

HOTSPOT_SRC=`(dirname $0)`/..
HOTSPOT_SRC=`(cd ${HOTSPOT_SRC}; pwd)`

for gm in gmake gnumake
do
  if [ "${GNUMAKE-}" != "" ]; then break; fi
  ($gm --version >/dev/null) 2>/dev/null && GNUMAKE=$gm
done
: ${GNUMAKE:?'Cannot locate the gnumake program.  Stop.'}

# quiet build by default
Quiet="MAKE_VERBOSE="

# no debug info by default
NoDebugInfo="ENABLE_FULL_DEBUG_SYMBOLS="

LANG=C

echo "### ENVIRONMENT SETTINGS:"
export HOTSPOT_SRC		; echo "HOTSPOT_SRC=$HOTSPOT_SRC"
export JAVA_HOME		; echo "JAVA_HOME=$JAVA_HOME"
export ALT_BOOTDIR		; echo "ALT_BOOTDIR=$ALT_BOOTDIR"
export ALT_OUTPUTDIR		; echo "ALT_OUTPUTDIR=$ALT_OUTPUTDIR"
export GNUMAKE			; echo "GNUMAKE=$GNUMAKE"
export LANG			; echo "LANG=$LANG"
echo "###"

BuildOptions="$Quiet $NoDebugInfo $*"

echo \
${GNUMAKE} -f ${HOTSPOT_SRC}/make/Makefile $BuildOptions GAMMADIR=${HOTSPOT_SRC}
${GNUMAKE} -f ${HOTSPOT_SRC}/make/Makefile $BuildOptions GAMMADIR=${HOTSPOT_SRC}
