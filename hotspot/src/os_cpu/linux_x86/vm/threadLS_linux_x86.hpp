
#ifndef OS_CPU_LINUX_X86_VM_THREADLS_LINUX_X86_HPP
#define OS_CPU_LINUX_X86_VM_THREADLS_LINUX_X86_HPP

  // Processor dependent parts of ThreadLocalStorage

#if !defined(AMD64) && !defined(MINIMIZE_RAM_USAGE)

  // map stack pointer to thread pointer - see notes in threadLS_linux_x86.cpp
  #define SP_BITLENGTH  32
  #define PAGE_SHIFT    12
  #define PAGE_SIZE     (1UL << PAGE_SHIFT)
  static Thread* _sp_map[1UL << (SP_BITLENGTH - PAGE_SHIFT)];

public:

  static Thread** sp_map_addr() { return _sp_map; }

  static Thread* thread() {
    uintptr_t sp;
    __asm__ volatile ("movl %%esp, %0" : "=r" (sp));
    return _sp_map[sp >> PAGE_SHIFT];
  }

#else

public:

   static Thread* thread() {
     return (Thread*) os::thread_local_storage_at(thread_index());
   }

#endif // AMD64 || MINIMIZE_RAM_USAGE

#endif // OS_CPU_LINUX_X86_VM_THREADLS_LINUX_X86_HPP
