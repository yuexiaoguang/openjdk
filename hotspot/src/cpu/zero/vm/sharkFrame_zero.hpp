
#ifndef CPU_ZERO_VM_SHARKFRAME_ZERO_HPP
#define CPU_ZERO_VM_SHARKFRAME_ZERO_HPP

#include "oops/method.hpp"
#include "stack_zero.hpp"

// |  ...               |
// +--------------------+  ------------------
// | stack slot n-1     |       low addresses
// |  ...               |
// | stack slot 0       |
// | monitor m-1        |
// |  ...               |
// | monitor 0          |
// | oop_tmp            |
// | method             |
// | unextended_sp      |
// | pc                 |
// | frame_type         |
// | next_frame         |      high addresses
// +--------------------+  ------------------
// |  ...               |

class SharkFrame : public ZeroFrame {
  friend class SharkStack;

 private:
  SharkFrame() : ZeroFrame() {
    ShouldNotCallThis();
  }

 protected:
  enum Layout {
    pc_off = jf_header_words,
    unextended_sp_off,
    method_off,
    oop_tmp_off,
    header_words
  };

 public:
  address pc() const {
    return (address) value_of_word(pc_off);
  }

  void set_pc(address pc) const {
    *((address*) addr_of_word(pc_off)) = pc;
  }

  intptr_t* unextended_sp() const {
    return (intptr_t *) value_of_word(unextended_sp_off);
  }

  Method* method() const {
    return (Method*) value_of_word(method_off);
  }

 public:
  void identify_word(int   frame_index,
                     int   offset,
                     char* fieldbuf,
                     char* valuebuf,
                     int   buflen) const;
};

#endif // CPU_ZERO_VM_SHARKFRAME_ZERO_HPP
