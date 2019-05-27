#ifndef SHARE_VM_CLASSFILE_METADATAONSTACKMARK_HPP
#define SHARE_VM_CLASSFILE_METADATAONSTACKMARK_HPP

#include "memory/allocation.hpp"

class Metadata;

// Helper class to mark and unmark metadata used on the stack as either handles
// or executing methods, so that it can't be deleted during class redefinition
// and class unloading.
// This is also used for other things that can be deallocated, like class
// metadata during parsing, relocated methods, and methods in backtraces.
class MetadataOnStackMark : public StackObj {
  NOT_PRODUCT(static bool _is_active;)
 public:
  MetadataOnStackMark();
  ~MetadataOnStackMark();
  static void record(Metadata* m);
};

#endif // SHARE_VM_CLASSFILE_METADATAONSTACKMARK_HPP
