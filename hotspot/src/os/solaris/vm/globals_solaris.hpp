
#ifndef OS_SOLARIS_VM_GLOBALS_SOLARIS_HPP
#define OS_SOLARIS_VM_GLOBALS_SOLARIS_HPP

//
// Defines Solaris specific flags. They are not available on other platforms.
//
#define RUNTIME_OS_FLAGS(develop, develop_pd, product, product_pd, diagnostic, notproduct) \
                                                                               \
  product(bool, UseExtendedFileIO, true,                                       \
          "Enable workaround for limitations of stdio FILE structure")

//
// Defines Solaris-specific default values. The flags are available on all
// platforms, but they may have different default values on other platforms.
//
define_pd_global(bool, UseLargePages, true);
define_pd_global(bool, UseLargePagesIndividualAllocation, false);
define_pd_global(bool, UseOSErrorReporting, false);
define_pd_global(bool, UseThreadPriorities, false);

#endif // OS_SOLARIS_VM_GLOBALS_SOLARIS_HPP
