#!/bin/bash

# Usage: ./logger.sh theloggfile acommand arg1 arg2 
#
# Execute acommand with args, in such a way that
# both stdout and stderr from acommand are appended to 
# theloggfile.
#
# Preserve stdout and stderr, so that the stdout
# from logger.sh is the same from acommand and equally
# for stderr.
#
# Propagate the result code from acommand so that
# ./logger.sh exits with the same result code.

# Create a temporary directory to store the result code from
# the wrapped command.
RCDIR=`mktemp -dt jdk-build-logger.tmp.XXXXXX` || exit $?
trap "rm -rf \"$RCDIR\"" EXIT
LOGFILE=$1
shift
(exec 3>&1 ; ("$@" 2>&1 1>&3; echo $? > "$RCDIR/rc") | tee -a $LOGFILE 1>&2 ; exec 3>&-) | tee -a $LOGFILE
exit `cat "$RCDIR/rc"`
