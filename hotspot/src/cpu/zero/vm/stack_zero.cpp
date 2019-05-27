
#include "precompiled.hpp"
#include "interpreter/interpreterRuntime.hpp"
#include "stack_zero.hpp"
#include "stack_zero.inline.hpp"

int ZeroStack::suggest_size(Thread *thread) const {
  assert(needs_setup(), "already set up");
  return align_size_down(abi_stack_available(thread) / 2, wordSize);
}

void ZeroStack::handle_overflow(TRAPS) {
  JavaThread *thread = (JavaThread *) THREAD;

  // Set up the frame anchor if it isn't already
  bool has_last_Java_frame = thread->has_last_Java_frame();
  if (!has_last_Java_frame) {
    intptr_t *sp = thread->zero_stack()->sp();
    ZeroFrame *frame = thread->top_zero_frame();
    while (frame) {
      if (frame->is_shark_frame())
        break;

      if (frame->is_interpreter_frame()) {
        interpreterState istate =
          frame->as_interpreter_frame()->interpreter_state();
        if (istate->self_link() == istate)
          break;
      }

      sp = ((intptr_t *) frame) + 1;
      frame = frame->next();
    }

    if (frame == NULL)
      fatal("unrecoverable stack overflow");

    thread->set_last_Java_frame(frame, sp);
  }

  // Throw the exception
  switch (thread->thread_state()) {
  case _thread_in_Java:
    InterpreterRuntime::throw_StackOverflowError(thread);
    break;

  case _thread_in_vm:
    Exceptions::throw_stack_overflow_exception(thread, __FILE__, __LINE__,
                                               methodHandle());
    break;

  default:
    ShouldNotReachHere();
  }

  // Reset the frame anchor if necessary
  if (!has_last_Java_frame)
    thread->reset_last_Java_frame();
}

#ifndef PRODUCT
void ZeroStack::zap(int c) {
  memset(_base, c, available_words() * wordSize);
}
#endif // PRODUCT
