#include "precompiled.hpp"
#include "ci/ciUtilities.hpp"

// ciUtilities
//
// Miscellaneous internal compiler interface routines.

// ------------------------------------------------------------------
// basictype_to_str
const char* basictype_to_str(BasicType t) {
  const char* str = type2name(t);
  if (str == NULL) return "illegal";
  return str;
}

// ------------------------------------------------------------------
// basictype_to_char
const char basictype_to_char(BasicType t) {
  char c = type2char(t);
  return c ? c : 'X';
}
