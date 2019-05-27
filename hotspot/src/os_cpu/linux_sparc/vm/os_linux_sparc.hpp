
#ifndef OS_CPU_LINUX_SPARC_VM_OS_LINUX_SPARC_HPP
#define OS_CPU_LINUX_SPARC_VM_OS_LINUX_SPARC_HPP

  //
  // NOTE: we are back in class os here, not Linux
  //
  static jint  (*atomic_xchg_func)        (jint,  volatile jint*);
  static jint  (*atomic_cmpxchg_func)     (jint,  volatile jint*,  jint);
  static jlong (*atomic_cmpxchg_long_func)(jlong, volatile jlong*, jlong);
  static jint  (*atomic_add_func)         (jint,  volatile jint*);

  static jint  atomic_xchg_bootstrap        (jint,  volatile jint*);
  static jint  atomic_cmpxchg_bootstrap     (jint,  volatile jint*,  jint);
  static jlong atomic_cmpxchg_long_bootstrap(jlong, volatile jlong*, jlong);
  static jint  atomic_add_bootstrap         (jint,  volatile jint*);

  static void setup_fpu() {}

  static bool is_allocatable(size_t bytes);

  // Used to register dynamic code cache area with the OS
  // Note: Currently only used in 64 bit Windows implementations
  static bool register_code_area(char *low, char *high) { return true; }

#endif // OS_CPU_LINUX_SPARC_VM_OS_LINUX_SPARC_HPP
