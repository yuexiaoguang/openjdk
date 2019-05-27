#include "precompiled.hpp"
#include "shark/sharkState.hpp"
#include "shark/sharkStateScanner.hpp"

using namespace llvm;

void SharkStateScanner::scan(SharkState* state) {
  start_frame();

  // Expression stack
  stack_integrity_checks(state);
  start_stack(state->stack_depth());
  for (int i = state->stack_depth() - 1; i >= 0; i--) {
    process_stack_slot(
      i,
      state->stack_addr(i),
      stack()->stack_slots_offset() +
        i + max_stack() - state->stack_depth());
  }
  end_stack();

  // Monitors
  start_monitors(state->num_monitors());
  for (int i = 0; i < state->num_monitors(); i++) {
    process_monitor(
      i,
      stack()->monitor_offset(i),
      stack()->monitor_object_offset(i));
  }
  end_monitors();

  // Frame header
  start_frame_header();
  process_oop_tmp_slot(
    state->oop_tmp_addr(), stack()->oop_tmp_slot_offset());
  process_method_slot(state->method_addr(), stack()->method_slot_offset());
  process_pc_slot(stack()->pc_slot_offset());
  end_frame_header();

  // Local variables
  locals_integrity_checks(state);
  start_locals();
  for (int i = 0; i < max_locals(); i++) {
    process_local_slot(
      i,
      state->local_addr(i),
      stack()->locals_slots_offset() + max_locals() - 1 - i);
  }
  end_locals();

  end_frame();
}

#ifndef PRODUCT
void SharkStateScanner::stack_integrity_checks(SharkState* state) {
  for (int i = 0; i < state->stack_depth(); i++) {
    if (state->stack(i)) {
      if (state->stack(i)->is_two_word())
        assert(state->stack(i - 1) == NULL, "should be");
    }
    else {
      assert(state->stack(i + 1)->is_two_word(), "should be");
    }
  }
}

void SharkStateScanner::locals_integrity_checks(SharkState* state) {
  for (int i = 0; i < max_locals(); i++) {
    if (state->local(i)) {
      if (state->local(i)->is_two_word())
        assert(state->local(i + 1) == NULL, "should be");
    }
  }
}
#endif // !PRODUCT
