
#ifndef OS_WINDOWS_VM_DECODER_WINDOWS_HPP
#define OS_WINDOWS_VM_DECIDER_WINDOWS_HPP

#include <windows.h>
#include <imagehlp.h>

#include "utilities/decoder.hpp"

// functions needed for decoding symbols
typedef DWORD (WINAPI *pfn_SymSetOptions)(DWORD);
typedef BOOL  (WINAPI *pfn_SymInitialize)(HANDLE, PCTSTR, BOOL);
typedef BOOL  (WINAPI *pfn_SymGetSymFromAddr64)(HANDLE, DWORD64, PDWORD64, PIMAGEHLP_SYMBOL64);
typedef DWORD (WINAPI *pfn_UndecorateSymbolName)(const char*, char*, DWORD, DWORD);
typedef BOOL  (WINAPI *pfn_SymSetSearchPath)(HANDLE, PCTSTR);
typedef BOOL  (WINAPI *pfn_SymGetSearchPath)(HANDLE, PTSTR, int);

#ifdef AMD64
typedef BOOL  (WINAPI *pfn_StackWalk64)(DWORD MachineType,
                                        HANDLE hProcess,
                                        HANDLE hThread,
                                        LPSTACKFRAME64 StackFrame,
                                        PVOID ContextRecord,
                                        PREAD_PROCESS_MEMORY_ROUTINE64 ReadMemoryRoutine,
                                        PFUNCTION_TABLE_ACCESS_ROUTINE64 FunctionTableAccessRoutine,
                                        PGET_MODULE_BASE_ROUTINE64 GetModuleBaseRoutine,
                                        PTRANSLATE_ADDRESS_ROUTINE64 TranslateAddress);
typedef PVOID (WINAPI *pfn_SymFunctionTableAccess64)(HANDLE hProcess, DWORD64 AddrBase);
typedef DWORD64 (WINAPI *pfn_SymGetModuleBase64)(HANDLE hProcess, DWORD64 dwAddr);
#endif

class WindowsDecoder : public AbstractDecoder {

public:
  WindowsDecoder();
  ~WindowsDecoder() { uninitialize(); };

  bool can_decode_C_frame_in_vm() const;
  bool demangle(const char* symbol, char *buf, int buflen);
  bool decode(address addr, char *buf, int buflen, int* offset, const char* modulepath = NULL);
  bool decode(address addr, char *buf, int buflen, int* offset, const void* base) {
    ShouldNotReachHere();
    return false;
  }

private:
  void initialize();
  void uninitialize();

private:
  HMODULE                   _dbghelp_handle;
  bool                      _can_decode_in_vm;
  pfn_SymGetSymFromAddr64   _pfnSymGetSymFromAddr64;
  pfn_UndecorateSymbolName  _pfnUndecorateSymbolName;
#ifdef AMD64
  pfn_StackWalk64              _pfnStackWalk64;
  pfn_SymFunctionTableAccess64 _pfnSymFunctionTableAccess64;
  pfn_SymGetModuleBase64       _pfnSymGetModuleBase64;

  friend class WindowsDbgHelp;
#endif
};

#ifdef AMD64
// TODO: refactor and move the handling of dbghelp.dll outside of Decoder
class WindowsDbgHelp : public Decoder {
public:
  static BOOL StackWalk64(DWORD MachineType,
                          HANDLE hProcess,
                          HANDLE hThread,
                          LPSTACKFRAME64 StackFrame,
                          PVOID ContextRecord,
                          PREAD_PROCESS_MEMORY_ROUTINE64 ReadMemoryRoutine,
                          PFUNCTION_TABLE_ACCESS_ROUTINE64 FunctionTableAccessRoutine,
                          PGET_MODULE_BASE_ROUTINE64 GetModuleBaseRoutine,
                          PTRANSLATE_ADDRESS_ROUTINE64 TranslateAddress);
  static PVOID SymFunctionTableAccess64(HANDLE hProcess, DWORD64 AddrBase);

  static pfn_SymFunctionTableAccess64 pfnSymFunctionTableAccess64();
  static pfn_SymGetModuleBase64       pfnSymGetModuleBase64();
};
#endif

#endif // OS_WINDOWS_VM_DECODER_WINDOWS_HPP

