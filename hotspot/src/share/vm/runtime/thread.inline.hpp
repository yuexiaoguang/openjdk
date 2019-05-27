#ifndef SHARE_VM_RUNTIME_THREAD_INLINE_HPP
#define SHARE_VM_RUNTIME_THREAD_INLINE_HPP

#define SHARE_VM_RUNTIME_THREAD_INLINE_HPP_SCOPE

#include "runtime/thread.hpp"
#ifdef TARGET_OS_FAMILY_linux
# include "thread_linux.inline.hpp"
#endif
#ifdef TARGET_OS_FAMILY_solaris
# include "thread_solaris.inline.hpp"
#endif
#ifdef TARGET_OS_FAMILY_windows
# include "thread_windows.inline.hpp"
#endif
#ifdef TARGET_OS_FAMILY_bsd
# include "thread_bsd.inline.hpp"
#endif

#undef SHARE_VM_RUNTIME_THREAD_INLINE_HPP_SCOPE

#endif // SHARE_VM_RUNTIME_THREAD_INLINE_HPP
