#ifndef SHARE_VM_SHARK_LLVMHEADERS_HPP
#define SHARE_VM_SHARK_LLVMHEADERS_HPP

#ifdef assert
  #undef assert
#endif

#ifdef DEBUG
  #define SHARK_DEBUG
  #undef DEBUG
#endif

#include <llvm/Analysis/Verifier.h>
#include <llvm/Argument.h>
#include <llvm/Constants.h>
#include <llvm/DerivedTypes.h>
#include <llvm/ExecutionEngine/ExecutionEngine.h>
#include <llvm/Instructions.h>
#include <llvm/LLVMContext.h>
#include <llvm/Module.h>
#if SHARK_LLVM_VERSION <= 31
#include <llvm/Support/IRBuilder.h>
#else
#include <llvm/IRBuilder.h>
#endif
#include <llvm/Support/Threading.h>
#include <llvm/Support/TargetSelect.h>
#include <llvm/Type.h>
#include <llvm/ExecutionEngine/JITMemoryManager.h>
#include <llvm/Support/CommandLine.h>
#include <llvm/ExecutionEngine/MCJIT.h>
#include <llvm/ExecutionEngine/JIT.h>
#include <llvm/ADT/StringMap.h>
#include <llvm/Support/Debug.h>
#include <llvm/Support/Host.h>

#include <map>

#ifdef assert
  #undef assert
#endif

// from hotspot/src/share/vm/utilities/debug.hpp
#ifdef ASSERT
#ifndef USE_REPEATED_ASSERTS
#define assert(p, msg)                                                       \
do {                                                                         \
  if (!(p)) {                                                                \
    report_vm_error(__FILE__, __LINE__, "assert(" #p ") failed", msg);       \
    BREAKPOINT;                                                              \
  }                                                                          \
} while (0)
#else // #ifndef USE_REPEATED_ASSERTS
#define assert(p, msg)
do {                                                                         \
  for (int __i = 0; __i < AssertRepeat; __i++) {                             \
    if (!(p)) {                                                              \
      report_vm_error(__FILE__, __LINE__, "assert(" #p ") failed", msg);     \
      BREAKPOINT;                                                            \
    }                                                                        \
  }                                                                          \
} while (0)
#endif // #ifndef USE_REPEATED_ASSERTS
#else
  #define assert(p, msg)
#endif

#ifdef DEBUG
  #undef DEBUG
#endif
#ifdef SHARK_DEBUG
  #define DEBUG
  #undef SHARK_DEBUG
#endif

#endif // SHARE_VM_SHARK_LLVMHEADERS_HPP
