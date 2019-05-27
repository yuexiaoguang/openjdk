#include "precompiled.hpp"

/////////////// Unit tests ///////////////

#ifndef PRODUCT

#include "oops/arrayOop.hpp"
#include "oops/oop.inline.hpp"
#include "utilities/globalDefinitions.hpp"

bool arrayOopDesc::check_max_length_overflow(BasicType type) {
  julong length = max_array_length(type);
  julong bytes_per_element = type2aelembytes(type);
  julong bytes = length * bytes_per_element + header_size_in_bytes();
  return (julong)(size_t)bytes == bytes;
}

void arrayOopDesc::test_max_array_length() {
  assert(check_max_length_overflow(T_BOOLEAN), "size_t overflow for boolean array");
  assert(check_max_length_overflow(T_CHAR), "size_t overflow for char array");
  assert(check_max_length_overflow(T_FLOAT), "size_t overflow for float array");
  assert(check_max_length_overflow(T_DOUBLE), "size_t overflow for double array");
  assert(check_max_length_overflow(T_BYTE), "size_t overflow for byte array");
  assert(check_max_length_overflow(T_SHORT), "size_t overflow for short array");
  assert(check_max_length_overflow(T_INT), "size_t overflow for int array");
  assert(check_max_length_overflow(T_LONG), "size_t overflow for long array");
  assert(check_max_length_overflow(T_OBJECT), "size_t overflow for object array");
  assert(check_max_length_overflow(T_ARRAY), "size_t overflow for array array");
  assert(check_max_length_overflow(T_NARROWOOP), "size_t overflow for narrowOop array");

  // T_VOID and T_ADDRESS are not supported by max_array_length()
}


#endif //PRODUCT
