
#ifndef OS_POSIX_VM_OS_POSIX_HPP
#define OS_POSIX_VM_OS_POSIX_HPP
class Posix {
  friend class os;

protected:
  static void print_distro_info(outputStream* st);
  static void print_rlimit_info(outputStream* st);
  static void print_uname_info(outputStream* st);
  static void print_libversion_info(outputStream* st);
  static void print_load_average(outputStream* st);


};

/*
 * Crash protection for the watcher thread. Wrap the callback
 * with a sigsetjmp and in case of a SIGSEGV/SIGBUS we siglongjmp
 * back.
 * To be able to use this - don't take locks, don't rely on destructors,
 * don't make OS library calls, don't allocate memory, don't print,
 * don't call code that could leave the heap / memory in an inconsistent state,
 * or anything else where we are not in control if we suddenly jump out.
 */
class WatcherThreadCrashProtection : public StackObj {
public:
  WatcherThreadCrashProtection();
  bool call(os::CrashProtectionCallback& cb);

  static void check_crash_protection(int signal, Thread* thread);
private:
  void restore();
  sigjmp_buf _jmpbuf;
};

#endif
