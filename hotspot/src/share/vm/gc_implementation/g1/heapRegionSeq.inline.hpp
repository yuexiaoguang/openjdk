#ifndef SHARE_VM_GC_IMPLEMENTATION_G1_HEAPREGIONSEQ_INLINE_HPP
#define SHARE_VM_GC_IMPLEMENTATION_G1_HEAPREGIONSEQ_INLINE_HPP

#include "gc_implementation/g1/heapRegion.hpp"
#include "gc_implementation/g1/heapRegionSeq.hpp"

inline HeapRegion* HeapRegionSeq::addr_to_region_unsafe(HeapWord* addr) const {
  HeapRegion* hr = _regions.get_by_address(addr);
  assert(hr != NULL, "invariant");
  return hr;
}

inline HeapRegion* HeapRegionSeq::addr_to_region(HeapWord* addr) const {
  if (addr != NULL && addr < heap_end()) {
    assert(addr >= heap_bottom(),
          err_msg("addr: "PTR_FORMAT" bottom: "PTR_FORMAT, addr, heap_bottom()));
    return addr_to_region_unsafe(addr);
  }
  return NULL;
}

inline HeapRegion* HeapRegionSeq::at(uint index) const {
  assert(index < length(), "pre-condition");
  HeapRegion* hr = _regions.get_by_index(index);
  assert(hr != NULL, "sanity");
  assert(hr->hrs_index() == index, "sanity");
  return hr;
}

#endif // SHARE_VM_GC_IMPLEMENTATION_G1_HEAPREGIONSEQ_INLINE_HPP
