
#ifndef OS_CPU_WINDOWS_X86_VM_THREADLS_WINDOWS_X86_HPP
#define OS_CPU_WINDOWS_X86_VM_THREADLS_WINDOWS_X86_HPP

// Processor dependent parts of ThreadLocalStorage

protected:

  static int _thread_ptr_offset;

public:

  // Java Thread
  static inline Thread* thread() {
    return (Thread*)TlsGetValue(thread_index());
  }

  static inline Thread* get_thread() {
    return (Thread*)TlsGetValue(thread_index());
  }

  static inline void set_thread_ptr_offset( int offset ) { _thread_ptr_offset = offset; }

  static inline int get_thread_ptr_offset() { return _thread_ptr_offset; }

#endif // OS_CPU_WINDOWS_X86_VM_THREADLS_WINDOWS_X86_HPP
