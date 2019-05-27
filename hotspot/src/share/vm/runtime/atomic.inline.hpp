#ifndef SHARE_VM_RUNTIME_ATOMIC_INLINE_HPP
#define SHARE_VM_RUNTIME_ATOMIC_INLINE_HPP

#include "runtime/atomic.hpp"

// Linux
#ifdef TARGET_OS_ARCH_linux_x86
# include "atomic_linux_x86.inline.hpp"
#endif
#ifdef TARGET_OS_ARCH_linux_sparc
# include "atomic_linux_sparc.inline.hpp"
#endif
#ifdef TARGET_OS_ARCH_linux_zero
# include "atomic_linux_zero.inline.hpp"
#endif
#ifdef TARGET_OS_ARCH_linux_arm
# include "atomic_linux_arm.inline.hpp"
#endif
#ifdef TARGET_OS_ARCH_linux_ppc
# include "atomic_linux_ppc.inline.hpp"
#endif

// Solaris
#ifdef TARGET_OS_ARCH_solaris_x86
# include "atomic_solaris_x86.inline.hpp"
#endif
#ifdef TARGET_OS_ARCH_solaris_sparc
# include "atomic_solaris_sparc.inline.hpp"
#endif

// Windows
#ifdef TARGET_OS_ARCH_windows_x86
# include "atomic_windows_x86.inline.hpp"
#endif

// BSD
#ifdef TARGET_OS_ARCH_bsd_x86
# include "atomic_bsd_x86.inline.hpp"
#endif
#ifdef TARGET_OS_ARCH_bsd_zero
# include "atomic_bsd_zero.inline.hpp"
#endif

#endif // SHARE_VM_RUNTIME_ATOMIC_INLINE_HPP
