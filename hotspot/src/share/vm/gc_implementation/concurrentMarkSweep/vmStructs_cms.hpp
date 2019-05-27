#ifndef SHARE_VM_GC_IMPLEMENTATION_CONCURRENTMARKSWEEP_VMSTRUCTS_CMS_HPP
#define SHARE_VM_GC_IMPLEMENTATION_CONCURRENTMARKSWEEP_VMSTRUCTS_CMS_HPP

#define VM_STRUCTS_CMS(nonstatic_field, \
                   volatile_nonstatic_field, \
                   static_field) \
  nonstatic_field(CompactibleFreeListSpace,    _collector,                                    CMSCollector*)                         \
  nonstatic_field(CompactibleFreeListSpace,    _bt,                                           BlockOffsetArrayNonContigSpace)        \
                                                                                                                                     \
  nonstatic_field(CMSBitMap,                   _bmWordSize,                                   size_t)                                \
  nonstatic_field(CMSBitMap,                   _shifter,                                      const int)                             \
  nonstatic_field(CMSBitMap,                      _bm,                                           BitMap)                             \
  nonstatic_field(CMSBitMap,                   _virtual_space,                                VirtualSpace)                          \
  nonstatic_field(CMSCollector,                _markBitMap,                                   CMSBitMap)                             \
  nonstatic_field(ConcurrentMarkSweepGeneration, _cmsSpace,                                   CompactibleFreeListSpace*)             \
     static_field(ConcurrentMarkSweepThread,   _collector,                                    CMSCollector*)                         \
  nonstatic_field(LinearAllocBlock,            _word_size,                                    size_t)                                \
  nonstatic_field(AFLBinaryTreeDictionary,     _total_size,                                   size_t)                                \
  nonstatic_field(CompactibleFreeListSpace,    _dictionary,                                   AFLBinaryTreeDictionary*)              \
  nonstatic_field(CompactibleFreeListSpace,    _indexedFreeList[0],                           FreeList<FreeChunk>)                   \
  nonstatic_field(CompactibleFreeListSpace,    _smallLinearAllocBlock,                        LinearAllocBlock)


#define VM_TYPES_CMS(declare_type,                                        \
                     declare_toplevel_type)                               \
                                                                          \
           declare_type(ConcurrentMarkSweepGeneration,CardGeneration)     \
           declare_type(CompactibleFreeListSpace,     CompactibleSpace)   \
           declare_type(ConcurrentMarkSweepThread,    NamedThread)        \
           declare_type(SurrogateLockerThread, JavaThread)                \
  declare_toplevel_type(CMSCollector)                                     \
  declare_toplevel_type(CMSBitMap)                                        \
  declare_toplevel_type(FreeChunk)                                        \
  declare_toplevel_type(Metablock)                                        \
  declare_toplevel_type(ConcurrentMarkSweepThread*)                       \
  declare_toplevel_type(ConcurrentMarkSweepGeneration*)                   \
  declare_toplevel_type(SurrogateLockerThread*)                           \
  declare_toplevel_type(CompactibleFreeListSpace*)                        \
  declare_toplevel_type(CMSCollector*)                                    \
  declare_toplevel_type(AFLBinaryTreeDictionary)                          \
  declare_toplevel_type(LinearAllocBlock)                                 \
  declare_toplevel_type(FreeBlockDictionary<FreeChunk>)

#define VM_INT_CONSTANTS_CMS(declare_constant)                            \
  declare_constant(Generation::ConcurrentMarkSweep)                       \

#endif // SHARE_VM_GC_IMPLEMENTATION_CONCURRENTMARKSWEEP_VMSTRUCTS_CMS_HPP
