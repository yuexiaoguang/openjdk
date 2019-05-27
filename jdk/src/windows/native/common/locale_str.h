#define DllExport __declspec(dllexport)

#ifdef __cplusplus
extern "C" {
#endif

DllExport const char * getEncodingFromLangID(LANGID langID);
DllExport const char * getJavaIDFromLangID(LANGID langID);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */
