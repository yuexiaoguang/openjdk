#include "precompiled.hpp"
#include "gc_interface/collectedHeap.hpp"
#include "memory/gcLocker.inline.hpp"
#include "memory/universe.hpp"
#include "oops/oop.inline.hpp"
#include "runtime/thread.hpp"
#include "runtime/unhandledOops.hpp"
#include "utilities/globalDefinitions.hpp"

#ifdef CHECK_UNHANDLED_OOPS
const int free_list_size = 256;


UnhandledOops::UnhandledOops(Thread* thread) {
  _thread = thread;
  _oop_list = new (ResourceObj::C_HEAP, mtInternal)
                    GrowableArray<UnhandledOopEntry>(free_list_size, true);
  _level = 0;
}

UnhandledOops::~UnhandledOops() {
  delete _oop_list;
}


void UnhandledOops::dump_oops(UnhandledOops *list) {
  for (int k = 0; k < list->_oop_list->length(); k++) {
    UnhandledOopEntry entry = list->_oop_list->at(k);
    tty->print(" " INTPTR_FORMAT, entry._oop_ptr);
  }
  tty->cr();
}

// For debugging unhandled oop detector _in the debugger_
// You don't want to turn it on in compiled code here.
static bool unhandled_oop_print=0;

void UnhandledOops::register_unhandled_oop(oop* op, address pc) {
  if (!_thread->is_in_stack((address)op))
    return;

  _level ++;
  if (unhandled_oop_print) {
    for (int i=0; i<_level; i++) tty->print(" ");
    tty->print_cr("r " INTPTR_FORMAT, op);
  }
  UnhandledOopEntry entry(op, pc);
  _oop_list->push(entry);
}


bool match_oop_entry(void *op, UnhandledOopEntry e) {
  return (e.oop_ptr() == op);
}

// Mark unhandled oop as okay for GC - the containing struct has an oops_do and
// for some reason the oop has to be on the stack.
// May not be called for the current thread, as in the case of
// VM_GetOrSetLocal in jvmti.
void UnhandledOops::allow_unhandled_oop(oop* op) {
  assert (CheckUnhandledOops, "should only be called with checking option");

  int i = _oop_list->find_from_end(op, match_oop_entry);
  assert(i!=-1, "safe for gc oop not in unhandled_oop_list");

  UnhandledOopEntry entry = _oop_list->at(i);
  assert(!entry._ok_for_gc, "duplicate entry");
  entry._ok_for_gc = true;
  _oop_list->at_put(i, entry);
}


// Called by the oop destructor to remove unhandled oop from the thread's
// oop list.  All oops given are assumed to be on the list.  If not,
// there's a bug in the unhandled oop detector.
void UnhandledOops::unregister_unhandled_oop(oop* op) {
  if (!_thread->is_in_stack((address)op)) return;

  _level --;
  if (unhandled_oop_print) {
    for (int i=0; i<_level; i++) tty->print(" ");
    tty->print_cr("u "INTPTR_FORMAT, op);
  }

  int i = _oop_list->find_from_end(op, match_oop_entry);
  assert(i!=-1, "oop not in unhandled_oop_list");
  _oop_list->remove_at(i);
}

void UnhandledOops::clear_unhandled_oops() {
  assert (CheckUnhandledOops, "should only be called with checking option");
  if (_thread->is_gc_locked_out()) {
    return;
  }
  for (int k = 0; k < _oop_list->length(); k++) {
    UnhandledOopEntry entry = _oop_list->at(k);
    // If an entry is on the unhandled oop list but isn't on the stack
    // anymore, it must not have gotten unregistered properly and it's a bug
    // in the unhandled oop generator.
    if(!_thread->is_in_stack((address)entry._oop_ptr)) {
      tty->print_cr("oop_ptr is " INTPTR_FORMAT, (address)entry._oop_ptr);
      tty->print_cr("thread is " INTPTR_FORMAT " from pc " INTPTR_FORMAT,
                     (address)_thread, (address)entry._pc);
      assert(false, "heap is corrupted by the unhandled oop detector");
    }
    // Set unhandled oops to a pattern that will crash distinctively
    if (!entry._ok_for_gc) *(intptr_t*)(entry._oop_ptr) = BAD_OOP_ADDR;
  }
}
#endif // CHECK_UNHANDLED_OOPS
