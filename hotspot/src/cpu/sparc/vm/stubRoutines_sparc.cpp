#include "precompiled.hpp"
#include "runtime/deoptimization.hpp"
#include "runtime/frame.inline.hpp"
#include "runtime/stubRoutines.hpp"
#include "runtime/thread.inline.hpp"

// Implementation of the platform-specific part of StubRoutines - for
// a description of how to extend it, see the stubRoutines.hpp file.

extern "C" {
  address _flush_reg_windows();   // in .s file.
  // Flush registers to stack. In case of error we will need to stack walk.
  address bootstrap_flush_windows(void) {
    Thread* thread = ThreadLocalStorage::get_thread_slow();
    // Very early in process there is no thread.
    if (thread != NULL) {
      guarantee(thread->is_Java_thread(), "Not a Java thread.");
      JavaThread* jt = (JavaThread*)thread;
      guarantee(!jt->has_last_Java_frame(), "Must be able to flush registers!");
    }
    return (address)_flush_reg_windows();
  };
};

address StubRoutines::Sparc::_test_stop_entry = NULL;
address StubRoutines::Sparc::_stop_subroutine_entry = NULL;
address StubRoutines::Sparc::_flush_callers_register_windows_entry = CAST_FROM_FN_PTR(address, bootstrap_flush_windows);

address StubRoutines::Sparc::_partial_subtype_check = NULL;
