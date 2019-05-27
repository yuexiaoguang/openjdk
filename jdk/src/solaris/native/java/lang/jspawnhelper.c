#include <errno.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>

#include "childproc.h"

extern int errno;

#define ALLOC(X,Y) { \
    void *mptr; \
    mptr = malloc (Y); \
    if (mptr == 0) { \
        error (fdout, ERR_MALLOC); \
    } \
    X = mptr; \
}

#define ERR_MALLOC 1
#define ERR_PIPE 2
#define ERR_ARGS 3

void error (int fd, int err) {
    write (fd, &err, sizeof(err));
    exit (1);
}

void shutItDown() {
    fprintf(stdout, "This command is not for general use and should ");
    fprintf(stdout, "only be run as the result of a call to\n");
    fprintf(stdout, "ProcessBuilder.start() or Runtime.exec() in a java ");
    fprintf(stdout, "application\n");
    _exit(1);
}

/*
 * read the following off the pipefd
 * - the ChildStuff struct
 * - the SpawnInfo struct
 * - the data strings for fields in ChildStuff
 */
void initChildStuff (int fdin, int fdout, ChildStuff *c) {
    int n;
    int argvBytes, nargv, envvBytes, nenvv;
    int dirlen;
    char *buf;
    SpawnInfo sp;
    int bufsize, offset=0;
    int magic;
    int res;

    res = readFully (fdin, &magic, sizeof(magic));
    if (res != 4 || magic != magicNumber()) {
        error (fdout, ERR_PIPE);
    }

    if (readFully (fdin, c, sizeof(*c)) == -1) {
        error (fdout, ERR_PIPE);
    }

    if (readFully (fdin, &sp, sizeof(sp)) == -1) {
        error (fdout, ERR_PIPE);
    }

    bufsize = sp.argvBytes + sp.envvBytes +
              sp.dirlen + sp.parentPathvBytes;

    ALLOC(buf, bufsize);

    if (readFully (fdin, buf, bufsize) == -1) {
        error (fdout, ERR_PIPE);
    }

    /* Initialize argv[] */
    ALLOC(c->argv, sizeof(char *) * sp.nargv);
    initVectorFromBlock (c->argv, buf+offset, sp.nargv-1);
    offset += sp.argvBytes;

    /* Initialize envv[] */
    if (sp.nenvv == 0) {
        c->envv = 0;
    } else {
        ALLOC(c->envv, sizeof(char *) * sp.nenvv);
        initVectorFromBlock (c->envv, buf+offset, sp.nenvv-1);
        offset += sp.envvBytes;
    }

    /* Initialize pdir */
    if (sp.dirlen == 0) {
        c->pdir = 0;
    } else {
        c->pdir = buf+offset;
        offset += sp.dirlen;
    }

    /* Initialize parentPathv[] */
    ALLOC(parentPathv, sizeof (char *) * sp.nparentPathv)
    initVectorFromBlock ((const char**)parentPathv, buf+offset, sp.nparentPathv-1);
    offset += sp.parentPathvBytes;
}

int main(int argc, char *argv[]) {
    ChildStuff c;
    int t;
    struct stat buf;
    /* argv[0] contains the fd number to read all the child info */
    int r, fdin, fdout;

    r = sscanf (argv[argc-1], "%d:%d", &fdin, &fdout);
    if (r == 2 && fcntl(fdin, F_GETFD) != -1) {
        fstat(fdin, &buf);
        if (!S_ISFIFO(buf.st_mode))
            shutItDown();
    } else {
        shutItDown();
    }
    initChildStuff (fdin, fdout, &c);

    childProcess (&c);
    return 0; /* NOT REACHED */
}
