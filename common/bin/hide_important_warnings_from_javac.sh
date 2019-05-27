#!/bin/bash

if [ -x /usr/bin/ggrep ] ; then
    # Gnu grep on Solaris
    # (reference configure and build/solaris-i586-clientANDserver-release/spec.gmk
    GREP=/usr/bin/ggrep
else
    GREP=grep
fi
#
EXP="Note: Some input files use or override a deprecated API."
EXP="${EXP}|Note: Recompile with -Xlint:deprecation for details."
EXP="${EXP}|Note: Some input files use unchecked or unsafe operations."
EXP="${EXP}|Note: Recompile with -Xlint:unchecked for details."
EXP="${EXP}| warning"
EXP="${EXP}|uses or overrides a deprecated API."
EXP="${EXP}|uses unchecked or unsafe operations."
#
${GREP} --line-buffered -v -E "${EXP}"
