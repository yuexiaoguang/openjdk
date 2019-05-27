#include <windows.h>
#include "jdk_util.h"

#define JVM_DLL "jvm.dll"

static HMODULE jvm_handle = NULL;

int JDK_InitJvmHandle() {
    jvm_handle = GetModuleHandle(JVM_DLL);
    return (jvm_handle != NULL);
}

void* JDK_FindJvmEntry(const char* name) {
    return (void*) GetProcAddress(jvm_handle, name);
}

JNIEXPORT HMODULE JDK_LoadSystemLibrary(const char* name) {
    HMODULE handle = NULL;
    char path[MAX_PATH];
    int ret;

    if (GetSystemDirectory(path, sizeof(path)) != 0) {
        strcat(path, "\\");
        strcat(path, name);
        handle = LoadLibrary(path);
    }

    if (handle == NULL) {
        if (GetWindowsDirectory(path, sizeof(path)) != 0) {
            strcat(path, "\\");
            strcat(path, name);
            handle = LoadLibrary(path);
        }
    }
    return handle;
}

