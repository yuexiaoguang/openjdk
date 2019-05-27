/* Native Platform Toolkit */

#ifndef  _NPT_MD_H
#define _NPT_MD_H

#include <windows.h>
#include <stdio.h>
#include <string.h>
#include <errno.h>

#define NPT_LIBNAME "npt"

#define NPT_INITIALIZE(path,pnpt,version,options)                       \
    {                                                                   \
        void   *_handle;                                                \
        void   *_sym;                                                   \
                                                                        \
        if ( (pnpt) == NULL ) NPT_ERROR("NptEnv* is NULL");             \
        *(pnpt) = NULL;                                                 \
        _handle =  LoadLibrary(path);                                   \
        if ( _handle == NULL ) NPT_ERROR("Cannot open library");        \
        _sym = GetProcAddress(_handle, "nptInitialize");                \
        if ( _sym == NULL ) NPT_ERROR("Cannot find nptInitialize");     \
        ((NptInitialize)_sym)((pnpt), version, (options));              \
        if ( *(pnpt) == NULL ) NPT_ERROR("Cannot initialize NptEnv");   \
        (*(pnpt))->libhandle = _handle;                                 \
    }

#define NPT_TERMINATE(npt,options)                                      \
    {                                                                   \
        void *_handle;                                                  \
        void *_sym;                                                     \
                                                                        \
        if ( (npt) == NULL ) NPT_ERROR("NptEnv* is NULL");              \
        _handle = (npt)->libhandle;                                     \
        if ( _handle == NULL ) NPT_ERROR("npt->libhandle is NULL");     \
        _sym = GetProcAddress(_handle, "nptTerminate");                 \
        if ( _sym == NULL ) NPT_ERROR("Cannot find nptTerminate");      \
        ((NptTerminate)_sym)((npt), (options));                         \
        (void)FreeLibrary(_handle);                                     \
    }

#endif
