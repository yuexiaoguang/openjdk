
#include "precompiled.hpp"
#include "asm/macroAssembler.hpp"
#include "asm/macroAssembler.inline.hpp"
#include "runtime/os.hpp"
#include "runtime/threadLocalStorage.hpp"

#ifndef _LP64
void MacroAssembler::int3() {
  call(RuntimeAddress(CAST_FROM_FN_PTR(address, os::breakpoint)));
}

#ifdef MINIMIZE_RAM_USAGE

void MacroAssembler::get_thread(Register thread) {
  // call pthread_getspecific
  // void * pthread_getspecific(pthread_key_t key);
  if (thread != rax) push(rax);
  push(rcx);
  push(rdx);

  push(ThreadLocalStorage::thread_index());
  call(RuntimeAddress(CAST_FROM_FN_PTR(address, pthread_getspecific)));
  increment(rsp, wordSize);

  pop(rdx);
  pop(rcx);
  if (thread != rax) {
    mov(thread, rax);
    pop(rax);
  }
}

#else
void MacroAssembler::get_thread(Register thread) {
  movl(thread, rsp);
  shrl(thread, PAGE_SHIFT);

  ExternalAddress tls_base((address)ThreadLocalStorage::sp_map_addr());
  Address index(noreg, thread, Address::times_4);
  ArrayAddress tls(tls_base, index);

  movptr(thread, tls);
}
#endif // MINIMIZE_RAM_USAGE
#else
void MacroAssembler::int3() {
  call(RuntimeAddress(CAST_FROM_FN_PTR(address, os::breakpoint)));
}

void MacroAssembler::get_thread(Register thread) {
  // call pthread_getspecific
  // void * pthread_getspecific(pthread_key_t key);
   if (thread != rax) {
     push(rax);
   }
   push(rdi);
   push(rsi);
   push(rdx);
   push(rcx);
   push(r8);
   push(r9);
   push(r10);
   // XXX
   mov(r10, rsp);
   andq(rsp, -16);
   push(r10);
   push(r11);

   movl(rdi, ThreadLocalStorage::thread_index());
   call(RuntimeAddress(CAST_FROM_FN_PTR(address, pthread_getspecific)));

   pop(r11);
   pop(rsp);
   pop(r10);
   pop(r9);
   pop(r8);
   pop(rcx);
   pop(rdx);
   pop(rsi);
   pop(rdi);
   if (thread != rax) {
       mov(thread, rax);
       pop(rax);
   }
}
#endif
