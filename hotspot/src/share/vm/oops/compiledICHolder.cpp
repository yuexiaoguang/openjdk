#include "precompiled.hpp"
#include "oops/compiledICHolder.hpp"
#include "oops/klass.hpp"
#include "oops/method.hpp"
#include "oops/oop.inline2.hpp"

volatile int CompiledICHolder::_live_count;
volatile int CompiledICHolder::_live_not_claimed_count;


// Printing

void CompiledICHolder::print_on(outputStream* st) const {
  st->print("%s", internal_name());
  st->print(" - method: "); holder_method()->print_value_on(st); st->cr();
  st->print(" - klass:  "); holder_klass()->print_value_on(st); st->cr();
}

void CompiledICHolder::print_value_on(outputStream* st) const {
  st->print("%s", internal_name());
}


// Verification

void CompiledICHolder::verify_on(outputStream* st) {
  guarantee(holder_method()->is_method(), "should be method");
  guarantee(holder_klass()->is_klass(),   "should be klass");
}
