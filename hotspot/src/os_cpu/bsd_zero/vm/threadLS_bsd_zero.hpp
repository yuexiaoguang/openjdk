
#ifndef OS_CPU_BSD_ZERO_VM_THREADLS_BSD_ZERO_HPP
#define OS_CPU_BSD_ZERO_VM_THREADLS_BSD_ZERO_HPP

// Processor dependent parts of ThreadLocalStorage

 public:
  static Thread* thread() {
    return (Thread*) os::thread_local_storage_at(thread_index());
  }

#endif // OS_CPU_BSD_ZERO_VM_THREADLS_BSD_ZERO_HPP
