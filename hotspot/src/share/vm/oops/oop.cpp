#include "precompiled.hpp"
#include "classfile/altHashing.hpp"
#include "classfile/javaClasses.hpp"
#include "oops/oop.inline.hpp"
#include "runtime/handles.inline.hpp"
#include "runtime/thread.inline.hpp"
#include "utilities/copy.hpp"

bool always_do_update_barrier = false;

BarrierSet* oopDesc::_bs = NULL;

void oopDesc::print_on(outputStream* st) const {
  if (this == NULL) {
    st->print_cr("NULL");
  } else {
    klass()->oop_print_on(oop(this), st);
  }
}

void oopDesc::print_address_on(outputStream* st) const {
  if (PrintOopAddress) {
    st->print("{"INTPTR_FORMAT"}", this);
  }
}

void oopDesc::print()         { print_on(tty);         }

void oopDesc::print_address() { print_address_on(tty); }

char* oopDesc::print_string() {
  stringStream st;
  print_on(&st);
  return st.as_string();
}

void oopDesc::print_value() {
  print_value_on(tty);
}

char* oopDesc::print_value_string() {
  char buf[100];
  stringStream st(buf, sizeof(buf));
  print_value_on(&st);
  return st.as_string();
}

void oopDesc::print_value_on(outputStream* st) const {
  oop obj = oop(this);
  if (this == NULL) {
    st->print("NULL");
  } else if (java_lang_String::is_instance(obj)) {
    java_lang_String::print(obj, st);
    if (PrintOopAddress) print_address_on(st);
  } else {
    klass()->oop_print_value_on(obj, st);
  }
}


void oopDesc::verify_on(outputStream* st) {
  if (this != NULL) {
    klass()->oop_verify_on(this, st);
  }
}


void oopDesc::verify() {
  verify_on(tty);
}

intptr_t oopDesc::slow_identity_hash() {
  // slow case; we have to acquire the micro lock in order to locate the header
  ResetNoHandleMark rnm; // Might be called from LEAF/QUICK ENTRY
  HandleMark hm;
  Handle object(this);
  return ObjectSynchronizer::identity_hash_value_for(object);
}

// When String table needs to rehash
unsigned int oopDesc::new_hash(jint seed) {
  EXCEPTION_MARK;
  ResourceMark rm;
  int length;
  jchar* chars = java_lang_String::as_unicode_string(this, length, THREAD);
  if (chars != NULL) {
    // Use alternate hashing algorithm on the string
    return AltHashing::murmur3_32(seed, chars, length);
  } else {
    vm_exit_out_of_memory(length, OOM_MALLOC_ERROR, "unable to create Unicode strings for String table rehash");
    return 0;
  }
}

VerifyOopClosure VerifyOopClosure::verify_oop;

void VerifyOopClosure::do_oop(oop* p)       { VerifyOopClosure::do_oop_work(p); }
void VerifyOopClosure::do_oop(narrowOop* p) { VerifyOopClosure::do_oop_work(p); }
