#!/bin/bash

# Usage: sh shell-tracer.sh <TIME_CMD> <OUTPUT_FILE> <OLD_SHELL> <shell command line>
#
# This shell script is supposed to be set as a replacement for SHELL in make,
# causing it to be called whenever make wants to execute shell commands.
# The <shell command line> is suitable for passing on to the old shell, 
# typically beginning with -c.
#
# This script will make sure the shell command line is executed with 
# OLD_SHELL -x, and it will also store a simple log of the the time it takes to
# execute the command in the OUTPUT_FILE, using the "time" utility as pointed 
# to by TIME_CMD. If TIME_CMD is "-", no timestamp will be stored.

TIME_CMD="$1"
OUTPUT_FILE="$2"
OLD_SHELL="$3"
shift
shift
shift
if [ "$TIME_CMD" != "-" ]; then
"$TIME_CMD" -f "[TIME:%E] $*" -a -o "$OUTPUT_FILE" "$OLD_SHELL" -x "$@"
else
"$OLD_SHELL" -x "$@"
fi
