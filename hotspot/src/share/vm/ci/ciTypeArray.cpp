#include "precompiled.hpp"
#include "ci/ciTypeArray.hpp"
#include "ci/ciUtilities.hpp"

// ciTypeArray
//
// This class represents an typeArrayOop in the HotSpot virtual
// machine.


// ------------------------------------------------------------------
// ciTypeArray::char_at
//
// Implementation of the char_at method.
jchar ciTypeArray::char_at(int index) {
  VM_ENTRY_MARK;
  assert(index >= 0 && index < length(), "out of range");
  jchar c = get_typeArrayOop()->char_at(index);
#ifdef ASSERT
  jchar d = element_value(index).as_char();
  assert(c == d, "");
#endif //ASSERT
  return c;
}
