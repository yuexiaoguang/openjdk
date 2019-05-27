#include "precompiled.hpp"
#include "services/memPtr.hpp"
#include "services/memTracker.hpp"

volatile jint SequenceGenerator::_seq_number = 1;
volatile unsigned long SequenceGenerator::_generation = 1;
NOT_PRODUCT(jint SequenceGenerator::_max_seq_number = 1;)

jint SequenceGenerator::next() {
  jint seq = Atomic::add(1, &_seq_number);
  if (seq < 0) {
    MemTracker::shutdown(MemTracker::NMT_sequence_overflow);
  } else {
    NOT_PRODUCT(_max_seq_number = (seq > _max_seq_number) ? seq : _max_seq_number;)
  }
  return seq;
}

