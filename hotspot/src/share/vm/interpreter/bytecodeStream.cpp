#include "precompiled.hpp"
#include "interpreter/bytecodeStream.hpp"
#include "interpreter/bytecodes.hpp"

Bytecodes::Code RawBytecodeStream::raw_next_special(Bytecodes::Code code) {
  assert(!is_last_bytecode(), "should have been checked");
  // set next bytecode position
  address bcp = RawBytecodeStream::bcp();
  address end = method()->code_base() + end_bci();
  int l = Bytecodes::raw_special_length_at(bcp, end);
  if (l <= 0 || (_bci + l) > _end_bci) {
    code = Bytecodes::_illegal;
  } else {
    _next_bci += l;
    assert(_bci < _next_bci, "length must be > 0");
    // set attributes
    _is_wide = false;
    // check for special (uncommon) cases
    if (code == Bytecodes::_wide) {
      if (bcp + 1 >= end) {
        code = Bytecodes::_illegal;
      } else {
        code = (Bytecodes::Code)bcp[1];
        _is_wide = true;
      }
    }
  }
  _raw_code = code;
  return code;
}

#ifdef ASSERT
void BaseBytecodeStream::assert_raw_index_size(int size) const {
  if (raw_code() == Bytecodes::_invokedynamic && is_raw()) {
    // in raw mode, pretend indy is "bJJ__"
    assert(size == 2, "raw invokedynamic instruction has 2-byte index only");
  } else {
    bytecode().assert_index_size(size, raw_code(), is_wide());
  }
}

void BaseBytecodeStream::assert_raw_stream(bool want_raw) const {
  if (want_raw) {
    assert( is_raw(), "this function only works on raw streams");
  } else {
    assert(!is_raw(), "this function only works on non-raw streams");
  }
}
#endif //ASSERT
