#include "precompiled.hpp"
#include "utilities/macros.hpp"
#if INCLUDE_ALL_GCS
#include "gc_implementation/shared/immutableSpace.hpp"
#include "memory/universe.hpp"
#include "oops/oop.inline.hpp"
#endif // INCLUDE_ALL_GCS

void ImmutableSpace::initialize(MemRegion mr) {
  HeapWord* bottom = mr.start();
  HeapWord* end    = mr.end();

  assert(Universe::on_page_boundary(bottom) && Universe::on_page_boundary(end),
         "invalid space boundaries");

  _bottom = bottom;
  _end = end;
}

void ImmutableSpace::oop_iterate(ExtendedOopClosure* cl) {
  HeapWord* obj_addr = bottom();
  HeapWord* t = end();
  // Could call objects iterate, but this is easier.
  while (obj_addr < t) {
    obj_addr += oop(obj_addr)->oop_iterate(cl);
  }
}

void ImmutableSpace::object_iterate(ObjectClosure* cl) {
  HeapWord* p = bottom();
  while (p < end()) {
    cl->do_object(oop(p));
    p += oop(p)->size();
  }
}

#ifndef PRODUCT

void ImmutableSpace::print_short() const {
  tty->print(" space " SIZE_FORMAT "K, 100%% used", capacity_in_bytes() / K);
}

void ImmutableSpace::print() const {
  print_short();
  tty->print_cr(" [%#-6lx,%#-6lx)", bottom(), end());
}

#endif

void ImmutableSpace::verify() {
  HeapWord* p = bottom();
  HeapWord* t = end();
  HeapWord* prev_p = NULL;
  while (p < t) {
    oop(p)->verify();
    prev_p = p;
    p += oop(p)->size();
  }
  guarantee(p == end(), "end of last object must match end of space");
}
