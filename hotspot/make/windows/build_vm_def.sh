
# This shell script builds a vm.def file for the current VM variant.
# The .def file exports vtbl symbols which allow the Serviceability
# Agent to run on Windows. See make/windows/projectfiles/*/vm.def
# for more information.
#
# The script expects to be executed in the directory containing all of
# the object files.

# Note that we currently do not have a way to set HotSpotMksHome in
# the batch build, but so far this has not seemed to be a problem. The
# reason this environment variable is necessary is that it seems that
# Windows truncates very long PATHs when executing shells like MKS's
# sh, and it has been found that sometimes `which sh` fails.
if [ "x$HOTSPOTMKSHOME" != "x" ]; then
 MKS_HOME="$HOTSPOTMKSHOME"
else
 SH=`which sh`
 MKS_HOME=`dirname "$SH"`
fi

AWK="$MKS_HOME/awk.exe"
if [ ! -e $AWK ]; then
    AWK="$MKS_HOME/gawk.exe"
fi
GREP="$MKS_HOME/grep.exe"
SORT="$MKS_HOME/sort.exe"
UNIQ="$MKS_HOME/uniq.exe"
CAT="$MKS_HOME/cat.exe"
RM="$MKS_HOME/rm.exe"
DUMPBIN="link.exe /dump"

if [ "$1" = "-nosa" ]; then
    echo EXPORTS > vm.def
    echo ""
    echo "***"
    echo "*** Not building SA: BUILD_WIN_SA != 1"
    echo "*** C++ Vtables NOT included in vm.def"
    echo "*** This jvm.dll will NOT work properly with SA."
    echo "***"
    echo "*** When in doubt, set BUILD_WIN_SA=1, clean and rebuild."
    echo "***"
    echo ""
    exit
fi

echo "EXPORTS" > vm1.def

# When called from IDE the first param should contain the link version, otherwise may be nill
if [ "x$1" != "x" ]; then
LD_VER="$1"
fi

if [ "x$LD_VER" != "x800" -a  "x$LD_VER" != "x900" -a "x$LD_VER" != "x1000" ]; then
$DUMPBIN /symbols *.obj | "$GREP" "??_7.*@@6B@" | "$GREP" -v "type_info" | "$AWK" '{print $7}' | "$SORT" | "$UNIQ" > vm2.def
else
# Can't use pipes when calling cl.exe or link.exe from IDE. Using transit file vm3.def
$DUMPBIN /OUT:vm3.def /symbols *.obj 
"$CAT" vm3.def | "$GREP" "??_7.*@@6B@" | "$GREP" -v "type_info" | "$AWK" '{print $7}' | "$SORT" | "$UNIQ" > vm2.def
"$RM" -f vm3.def
fi

"$CAT" vm1.def vm2.def > vm.def
"$RM" -f vm1.def vm2.def
