
#ifndef OS_SOLARIS_VM_OS_SHARE_SOLARIS_HPP
#define OS_SOLARIS_VM_OS_SHARE_SOLARIS_HPP

// Defines the interfaces to Solaris operating systems that vary across platforms

// misc
extern "C" {
  void signalHandler(int, siginfo_t*, void*);
}
void resolve_lwp_exit_calls(void);
void handle_unexpected_exception(Thread* thread, int sig, siginfo_t* info, address pc, address adjusted_pc);
#ifndef PRODUCT
void continue_with_dump(void);
#endif

#if defined(__sparc) && defined(COMPILER2)
// For Sun Studio compiler implementation is in  file
// src/os_cpu/solaris_sparc/vm/solaris_sparc.il
// For gcc implementation is in  file
// src/os_cpu/solaris_sparc/vm/os_solaris_sparc.cpp
extern "C" void _mark_fpu_nosave() ;
#endif

#define PROCFILE_LENGTH 128

#endif // OS_SOLARIS_VM_OS_SHARE_SOLARIS_HPP
