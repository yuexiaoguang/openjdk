
#ifndef OS_CPU_WINDOWS_X86_VM_OS_WINDOWS_X86_HPP
#define OS_CPU_WINDOWS_X86_VM_OS_WINDOWS_X86_HPP

  //
  // NOTE: we are back in class os here, not win32
  //
#ifdef AMD64
  static jint      (*atomic_xchg_func)          (jint,      volatile jint*);
  static intptr_t  (*atomic_xchg_ptr_func)      (intptr_t,  volatile intptr_t*);

  static jint      (*atomic_cmpxchg_func)       (jint,      volatile jint*,  jint);
  static jlong     (*atomic_cmpxchg_long_func)  (jlong,     volatile jlong*, jlong);

  static jint      (*atomic_add_func)           (jint,      volatile jint*);
  static intptr_t  (*atomic_add_ptr_func)       (intptr_t,  volatile intptr_t*);

  static jint      atomic_xchg_bootstrap        (jint,      volatile jint*);
  static intptr_t  atomic_xchg_ptr_bootstrap    (intptr_t,  volatile intptr_t*);

  static jint      atomic_cmpxchg_bootstrap     (jint,      volatile jint*,  jint);
#else

  static jlong (*atomic_cmpxchg_long_func)  (jlong, volatile jlong*, jlong);

#endif // AMD64

  static jlong atomic_cmpxchg_long_bootstrap(jlong, volatile jlong*, jlong);

#ifdef AMD64
  static jint      atomic_add_bootstrap         (jint,      volatile jint*);
  static intptr_t  atomic_add_ptr_bootstrap     (intptr_t,  volatile intptr_t*);
#endif // AMD64

  static void setup_fpu();
  static bool supports_sse() { return true; }

  static jlong rdtsc();

  static bool      register_code_area(char *low, char *high);

#ifdef AMD64
#define PLATFORM_PRINT_NATIVE_STACK 1
static bool platform_print_native_stack(outputStream* st, void* context,
                                        char *buf, int buf_size);
#endif

#endif // OS_CPU_WINDOWS_X86_VM_OS_WINDOWS_X86_HPP
