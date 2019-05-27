
#ifndef OS_SOLARIS_DTRACE_GENERATEJVMOFFSETS_H
#define OS_SOLARIS_DTRACE_GENERATEJVMOFFSETS_H

#include <stdio.h>
#include <strings.h>

typedef enum GEN_variant {
        GEN_OFFSET = 0,
        GEN_INDEX  = 1,
        GEN_TABLE  = 2
} GEN_variant;

extern "C" {
        int generateJvmOffsets(GEN_variant gen_var);
        void gen_prologue(GEN_variant gen_var);
        void gen_epilogue(GEN_variant gen_var);
}

#endif // OS_SOLARIS_DTRACE_GENERATEJVMOFFSETS_H
