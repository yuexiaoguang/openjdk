#ifndef SHARE_VM_GC_IMPLEMENTATION_G1_VMSTRUCTS_G1_HPP
#define SHARE_VM_GC_IMPLEMENTATION_G1_VMSTRUCTS_G1_HPP

#include "gc_implementation/g1/heapRegion.hpp"
#include "gc_implementation/g1/heapRegionSeq.inline.hpp"
#include "gc_implementation/g1/g1CollectedHeap.inline.hpp"

#define VM_STRUCTS_G1(nonstatic_field, static_field)                          \
                                                                              \
  static_field(HeapRegion, GrainBytes,        size_t)                         \
  static_field(HeapRegion, LogOfHRGrainBytes, int)                            \
                                                                              \
  nonstatic_field(G1HeapRegionTable, _base,             address)              \
  nonstatic_field(G1HeapRegionTable, _length,           size_t)               \
  nonstatic_field(G1HeapRegionTable, _biased_base,      address)              \
  nonstatic_field(G1HeapRegionTable, _bias,             size_t)               \
  nonstatic_field(G1HeapRegionTable, _shift_by,         uint)                 \
                                                                              \
  nonstatic_field(HeapRegionSeq,   _regions,            G1HeapRegionTable)    \
  nonstatic_field(HeapRegionSeq,   _committed_length,   uint)                 \
                                                                              \
  nonstatic_field(G1CollectedHeap, _hrs,                HeapRegionSeq)        \
  nonstatic_field(G1CollectedHeap, _g1_committed,       MemRegion)            \
  nonstatic_field(G1CollectedHeap, _summary_bytes_used, size_t)               \
  nonstatic_field(G1CollectedHeap, _g1mm,               G1MonitoringSupport*) \
  nonstatic_field(G1CollectedHeap, _old_set,            HeapRegionSetBase)    \
  nonstatic_field(G1CollectedHeap, _humongous_set,      HeapRegionSetBase)    \
                                                                              \
  nonstatic_field(G1MonitoringSupport, _eden_committed,     size_t)           \
  nonstatic_field(G1MonitoringSupport, _eden_used,          size_t)           \
  nonstatic_field(G1MonitoringSupport, _survivor_committed, size_t)           \
  nonstatic_field(G1MonitoringSupport, _survivor_used,      size_t)           \
  nonstatic_field(G1MonitoringSupport, _old_committed,      size_t)           \
  nonstatic_field(G1MonitoringSupport, _old_used,           size_t)           \
                                                                              \
  nonstatic_field(HeapRegionSetBase,   _length,             uint)             \
  nonstatic_field(HeapRegionSetBase,   _region_num,         uint)             \
  nonstatic_field(HeapRegionSetBase,   _total_used_bytes,   size_t)           \


#define VM_TYPES_G1(declare_type, declare_toplevel_type)                      \
                                                                              \
  declare_toplevel_type(G1HeapRegionTable)                                    \
                                                                              \
  declare_type(G1CollectedHeap, SharedHeap)                                   \
                                                                              \
  declare_type(HeapRegion, ContiguousSpace)                                   \
  declare_toplevel_type(HeapRegionSeq)                                        \
  declare_toplevel_type(HeapRegionSetBase)                                    \
  declare_toplevel_type(G1MonitoringSupport)                                  \
                                                                              \
  declare_toplevel_type(G1CollectedHeap*)                                     \
  declare_toplevel_type(HeapRegion*)                                          \
  declare_toplevel_type(G1MonitoringSupport*)                                 \


#endif // SHARE_VM_GC_IMPLEMENTATION_G1_VMSTRUCTS_G1_HPP
