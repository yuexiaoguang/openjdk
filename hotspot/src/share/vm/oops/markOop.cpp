#include "precompiled.hpp"
#include "oops/markOop.hpp"
#include "runtime/thread.inline.hpp"

void markOopDesc::print_on(outputStream* st) const {
  if (is_locked()) {
    st->print("locked(0x%lx)->", value());
    markOop(*(markOop*)value())->print_on(st);
  } else {
    assert(is_unlocked() || has_bias_pattern(), "just checking");
    st->print("mark(");
    if (has_bias_pattern())  st->print("biased,");
    st->print("hash %#lx,", hash());
    st->print("age %d)", age());
  }
}
