#ifndef SHARE_VM_GC_IMPLEMENTATION_SHARED_LIVERANGE_HPP
#define SHARE_VM_GC_IMPLEMENTATION_SHARED_LIVERANGE_HPP

#include "memory/memRegion.hpp"
#include "utilities/copy.hpp"

// This is a shared helper class used during phase 3 and 4 to move all the objects
// Dead regions in a Space are linked together to keep track of the live regions
// so that the live data can be traversed quickly without having to look at each
// object.

class LiveRange: public MemRegion {
public:
  LiveRange(HeapWord* bottom, HeapWord* top): MemRegion(bottom, top) {}

  void set_end(HeapWord* e) {
    assert(e >= start(), "should be a non-zero range");
    MemRegion::set_end(e);
  }
  void set_word_size(size_t ws) {
    assert(ws >= 0, "should be a non-zero range");
    MemRegion::set_word_size(ws);
  }

  LiveRange * next() { return (LiveRange *) end(); }

  void move_to(HeapWord* destination) {
    Copy::aligned_conjoint_words(start(), destination, word_size());
  }
};

#endif // SHARE_VM_GC_IMPLEMENTATION_SHARED_LIVERANGE_HPP
