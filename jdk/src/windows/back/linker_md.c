/*
 * Maintains a list of currently loaded DLLs (Dynamic Link Libraries)
 * and their associated handles. Library names are case-insensitive.
 */
#include <windows.h>
#include <stdio.h>
#include <string.h>
#include <errno.h>
#include <io.h>

#include "sys.h"

#include "path_md.h"

static void dll_build_name(char* buffer, size_t buflen,
                           const char* paths, const char* fname) {
    char *path, *paths_copy, *next_token;

    paths_copy = strdup(paths);
    if (paths_copy == NULL) {
        return;
    }

    next_token = NULL;
    path = strtok_s(paths_copy, PATH_SEPARATOR, &next_token);

    while (path != NULL) {
        _snprintf(buffer, buflen, "%s\\%s.dll", path, fname);
        if (_access(buffer, 0) == 0) {
            break;
        }
        *buffer = '\0';
        path = strtok_s(NULL, PATH_SEPARATOR, &next_token);
    }

    free(paths_copy);
}

/*
 * From system_md.c v1.54
 */
int
dbgsysGetLastErrorString(char *buf, int len)
{
    long errval;

    if ((errval = GetLastError()) != 0) {
        /* DOS error */
        int n = FormatMessage(FORMAT_MESSAGE_FROM_SYSTEM|FORMAT_MESSAGE_IGNORE_INSERTS,
                              NULL, errval,
                              0, buf, len, NULL);
        if (n > 3) {
            /* Drop final '.', CR, LF */
            if (buf[n - 1] == '\n') n--;
            if (buf[n - 1] == '\r') n--;
            if (buf[n - 1] == '.') n--;
            buf[n] = '\0';
        }
        return n;
    }

    if (errno != 0) {
        /* C runtime error that has no corresponding DOS error code */
        const char *s = strerror(errno);
        int n = (int)strlen(s);
        if (n >= len) n = len - 1;
        strncpy(buf, s, n);
        buf[n] = '\0';
        return n;
    }

    return 0;
}

/*
 * Build a machine dependent library name out of a path and file name.
 */
void
dbgsysBuildLibName(char *holder, int holderlen, const char *pname, const char *fname)
{
    const int pnamelen = pname ? (int)strlen(pname) : 0;

    *holder = '\0';
    /* Quietly truncates on buffer overflow. Should be an error. */
    if (pnamelen + (int)strlen(fname) + 10 > holderlen) {
        return;
    }

    if (pnamelen == 0) {
        sprintf(holder, "%s.dll", fname);
    } else {
      dll_build_name(holder, holderlen, pname, fname);
    }
}

void *
dbgsysLoadLibrary(const char * name, char *err_buf, int err_buflen)
{
    void *result = LoadLibrary(name);
    if (result == NULL) {
        /* Error message is pretty lame, try to make a better guess. */
        long errcode = GetLastError();
        if (errcode == ERROR_MOD_NOT_FOUND) {
            strncpy(err_buf, "Can't find dependent libraries", err_buflen-2);
            err_buf[err_buflen-1] = '\0';
        } else {
            dbgsysGetLastErrorString(err_buf, err_buflen);
        }
    }
    return result;
}

void dbgsysUnloadLibrary(void *handle)
{
    FreeLibrary(handle);
}

void * dbgsysFindLibraryEntry(void *handle, const char *name)
{
    return GetProcAddress(handle, name);
}
