#ifndef SHARE_VM_COMPILER_DISASSEMBLER_HPP
#define SHARE_VM_COMPILER_DISASSEMBLER_HPP

#include "asm/codeBuffer.hpp"
#include "runtime/globals.hpp"
#ifdef TARGET_OS_FAMILY_linux
# include "os_linux.inline.hpp"
#endif
#ifdef TARGET_OS_FAMILY_solaris
# include "os_solaris.inline.hpp"
#endif
#ifdef TARGET_OS_FAMILY_windows
# include "os_windows.inline.hpp"
#endif
#ifdef TARGET_OS_FAMILY_bsd
# include "os_bsd.inline.hpp"
#endif

class decode_env;

// The disassembler prints out assembly code annotated
// with Java specific information.

class Disassembler {
  friend class decode_env;
 private:
  // this is the type of the dll entry point:
  typedef void* (*decode_func_virtual)(uintptr_t start_va, uintptr_t end_va,
                               unsigned char* buffer, uintptr_t length,
                               void* (*event_callback)(void*, const char*, void*),
                               void* event_stream,
                               int (*printf_callback)(void*, const char*, ...),
                               void* printf_stream,
                               const char* options,
                               int newline);
  // this is the type of the dll entry point for old version:
  typedef void* (*decode_func)(void* start_va, void* end_va,
                               void* (*event_callback)(void*, const char*, void*),
                               void* event_stream,
                               int (*printf_callback)(void*, const char*, ...),
                               void* printf_stream,
                               const char* options);
  // points to the library.
  static void*    _library;
  // bailout
  static bool     _tried_to_load_library;
  // points to the decode function.
  static decode_func_virtual _decode_instructions_virtual;
  static decode_func _decode_instructions;
  // tries to load library and return whether it succedded.
  static bool load_library();

  // Machine dependent stuff
#ifdef TARGET_ARCH_x86
# include "disassembler_x86.hpp"
#endif
#ifdef TARGET_ARCH_sparc
# include "disassembler_sparc.hpp"
#endif
#ifdef TARGET_ARCH_zero
# include "disassembler_zero.hpp"
#endif
#ifdef TARGET_ARCH_arm
# include "disassembler_arm.hpp"
#endif
#ifdef TARGET_ARCH_ppc
# include "disassembler_ppc.hpp"
#endif


 public:
  static bool can_decode() {
    return (_decode_instructions_virtual != NULL) ||
           (_decode_instructions != NULL) ||
           load_library();
  }
  static void decode(CodeBlob *cb,               outputStream* st = NULL);
  static void decode(nmethod* nm,                outputStream* st = NULL);
  static void decode(address begin, address end, outputStream* st = NULL, CodeStrings c = CodeStrings());
};

#endif // SHARE_VM_COMPILER_DISASSEMBLER_HPP