
# Sets make macros for making core version of VM

# Select which files to use (in top.make)
TYPE=CORE

# There is no "core" directory in JDK. Install core build in server directory.
VM_SUBDIR = server

# Note:  macros.hpp defines CORE
