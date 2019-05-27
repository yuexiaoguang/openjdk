
#ifndef OS_LINUX_VM_OS_SHARE_LINUX_HPP
#define OS_LINUX_VM_OS_SHARE_LINUX_HPP

// misc
void signalHandler(int, siginfo_t*, ucontext_t*);
void handle_unexpected_exception(Thread* thread, int sig, siginfo_t* info, address pc, address adjusted_pc);
#ifndef PRODUCT
void continue_with_dump(void);
#endif

#define PROCFILE_LENGTH 128

#endif // OS_LINUX_VM_OS_SHARE_LINUX_HPP
