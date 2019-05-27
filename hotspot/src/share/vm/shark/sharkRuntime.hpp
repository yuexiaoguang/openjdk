#ifndef SHARE_VM_SHARK_SHARKRUNTIME_HPP
#define SHARE_VM_SHARK_SHARKRUNTIME_HPP

#include "memory/allocation.hpp"
#include "runtime/thread.hpp"
#include "shark/llvmHeaders.hpp"
#include "shark/llvmValue.hpp"

class SharkRuntime : public AllStatic {
  // VM calls
 public:
  static int find_exception_handler(JavaThread* thread,
                                    int*        indexes,
                                    int         num_indexes);

  static void monitorenter(JavaThread* thread, BasicObjectLock* lock);
  static void monitorexit(JavaThread* thread, BasicObjectLock* lock);

  static void new_instance(JavaThread* thread, int index);
  static void newarray(JavaThread* thread, BasicType type, int size);
  static void anewarray(JavaThread* thread, int index, int size);
  static void multianewarray(JavaThread* thread,
                             int         index,
                             int         ndims,
                             int*        dims);

  static void register_finalizer(JavaThread* thread, oop object);

  static void throw_ArithmeticException(JavaThread* thread,
                                        const char* file,
                                        int         line);
  static void throw_ArrayIndexOutOfBoundsException(JavaThread* thread,
                                                   const char* file,
                                                   int         line,
                                                   int         index);
  static void throw_ClassCastException(JavaThread* thread,
                                       const char* file,
                                       int         line);
  static void throw_NullPointerException(JavaThread* thread,
                                         const char* file,
                                         int         line);

  // Helpers for VM calls
 private:
  static const SharkFrame* last_frame(JavaThread *thread) {
    return thread->last_frame().zero_sharkframe();
  }
  static Method* method(JavaThread *thread) {
    return last_frame(thread)->method();
  }
  static address bcp(JavaThread *thread, int bci) {
    return method(thread)->code_base() + bci;
  }
  static int two_byte_index(JavaThread *thread, int bci) {
    return Bytes::get_Java_u2(bcp(thread, bci) + 1);
  }
  static intptr_t tos_at(JavaThread *thread, int offset) {
    return *(thread->zero_stack()->sp() + offset);
  }

  // Non-VM calls
 public:
  static void dump(const char *name, intptr_t value);
  static bool is_subtype_of(Klass* check_klass, Klass* object_klass);
  static int uncommon_trap(JavaThread* thread, int trap_request);
};

#endif // SHARE_VM_SHARK_SHARKRUNTIME_HPP
