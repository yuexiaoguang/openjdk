#include <windows.h>

// in nss.h:
// extern PRBool NSS_VersionCheck(const char *importedVersion);
// extern SECStatus NSS_Initialize(const char *configdir,
//      const char *certPrefix, const char *keyPrefix,
//      const char *secmodName, PRUint32 flags);

typedef int __declspec(dllimport) (*FPTR_VersionCheck)(const char *importedVersion);
typedef int __declspec(dllimport) (*FPTR_Initialize)(const char *configdir,
        const char *certPrefix, const char *keyPrefix,
        const char *secmodName, unsigned int flags);

// in secmod.h
//extern SECMODModule *SECMOD_LoadModule(char *moduleSpec,SECMODModule *parent,
//                                                      PRBool recurse);
//char **SECMOD_GetModuleSpecList(SECMODModule *module);
//extern SECMODModuleList *SECMOD_GetDBModuleList(void);

typedef void __declspec(dllimport) *(*FPTR_LoadModule)(char *moduleSpec, void *parent, int recurse);
typedef char __declspec(dllimport) **(*FPTR_GetModuleSpecList)(void *module);
typedef void __declspec(dllimport) *(*FPTR_GetDBModuleList)(void);
