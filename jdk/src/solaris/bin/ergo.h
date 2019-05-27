#ifndef _ERGO_H
#define _ERGO_H

#include <inttypes.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <sys/stat.h>
#include <unistd.h>
#include <sys/types.h>

#include "java.h"

jboolean ServerClassMachineImpl(void);
uint64_t physical_memory(void);

#endif /* _ERGO_H */
