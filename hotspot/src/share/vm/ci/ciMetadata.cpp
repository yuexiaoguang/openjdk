#include "precompiled.hpp"
#include "ci/ciObject.hpp"
#include "ci/ciUtilities.hpp"
#include "gc_interface/collectedHeap.inline.hpp"
#include "oops/oop.inline2.hpp"

// ------------------------------------------------------------------
// ciMetadata::print
//
// Print debugging output about this ciMetadata.
//
// Implementation note: dispatch to the virtual print_impl behavior
// for this ciObject.
void ciMetadata::print(outputStream* st) {
  st->print("<%s", type_string());
  GUARDED_VM_ENTRY(print_impl(st);)
  st->print(" ident=%d address=0x%x>", ident(), (address)this);
}


// ------------------------------------------------------------------
// ciMetadata::print_oop
//
// Print debugging output about the metadata this ciMetadata represents.
void ciMetadata::print_metadata(outputStream* st) {
  if (!is_loaded()) {
    st->print_cr("UNLOADED");
  } else {
    GUARDED_VM_ENTRY(_metadata->print_on(st);)
  }
}
