
#ifndef OS_CPU_LINUX_SPARC_VM_THREADLS_LINUX_SPARC_HPP
#define OS_CPU_LINUX_SPARC_VM_THREADLS_LINUX_SPARC_HPP

public:
  static Thread* thread() {
    return (Thread*) os::thread_local_storage_at(thread_index());
  }

#endif // OS_CPU_LINUX_SPARC_VM_THREADLS_LINUX_SPARC_HPP
