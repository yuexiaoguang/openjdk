#ifndef SHARE_VM_SHARK_SHARK_GLOBALS_HPP
#define SHARE_VM_SHARK_SHARK_GLOBALS_HPP

#include "runtime/globals.hpp"
#ifdef TARGET_ARCH_zero
# include "shark_globals_zero.hpp"
#endif

#define SHARK_FLAGS(develop, develop_pd, product, product_pd, diagnostic, notproduct) \
                                                                              \
  product(intx, MaxNodeLimit, 65000,                                          \
          "Maximum number of nodes")                                          \
                                                                              \
  /* inlining */                                                              \
  product(intx, SharkMaxInlineSize, 32,                                       \
          "Maximum bytecode size of methods to inline when using Shark")      \
                                                                              \
  product(bool, EliminateNestedLocks, true,                                   \
          "Eliminate nested locks of the same object when possible")          \
                                                                              \
  product(ccstr, SharkOptimizationLevel, "Default",                           \
          "The optimization level passed to LLVM, possible values: None, Less, Default and Agressive") \
                                                                              \
  /* compiler debugging */                                                    \
  develop(ccstr, SharkPrintTypeflowOf, NULL,                                  \
          "Print the typeflow of the specified method")                       \
                                                                              \
  diagnostic(ccstr, SharkPrintBitcodeOf, NULL,                                \
          "Print the LLVM bitcode of the specified method")                   \
                                                                              \
  diagnostic(ccstr, SharkPrintAsmOf, NULL,                                    \
          "Print the asm of the specified method")                            \
                                                                              \
  develop(bool, SharkTraceBytecodes, false,                                   \
          "Trace bytecode compilation")                                       \
                                                                              \
  diagnostic(bool, SharkTraceInstalls, false,                                 \
          "Trace method installation")                                        \
                                                                              \
  diagnostic(bool, SharkPerformanceWarnings, false,                           \
          "Warn about things that could be made faster")                      \
                                                                              \
  develop(ccstr, SharkVerifyFunction, NULL,                                   \
          "Runs LLVM verify over LLVM IR")                                    \


SHARK_FLAGS(DECLARE_DEVELOPER_FLAG, DECLARE_PD_DEVELOPER_FLAG, DECLARE_PRODUCT_FLAG, DECLARE_PD_PRODUCT_FLAG, DECLARE_DIAGNOSTIC_FLAG, DECLARE_NOTPRODUCT_FLAG)

#endif // SHARE_VM_SHARK_SHARK_GLOBALS_HPP
