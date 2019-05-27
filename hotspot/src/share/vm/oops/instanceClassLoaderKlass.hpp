#ifndef SHARE_VM_OOPS_INSTANCECLASSLOADERKLASS_HPP
#define SHARE_VM_OOPS_INSTANCECLASSLOADERKLASS_HPP

#include "oops/instanceKlass.hpp"
#include "utilities/macros.hpp"

// An InstanceClassLoaderKlass is a specialization of the InstanceKlass. It does
// not add any field.  It is added to walk the dependencies for the class loader
// key that this class loader points to.  This is how the loader_data graph is
// walked and dependant class loaders are kept alive.  I thought we walked
// the list later?

class InstanceClassLoaderKlass: public InstanceKlass {
  friend class VMStructs;
  friend class InstanceKlass;

  // Constructor
  InstanceClassLoaderKlass(int vtable_len, int itable_len, int static_field_size, int nonstatic_oop_map_size, ReferenceType rt, AccessFlags access_flags, bool is_anonymous)
    : InstanceKlass(vtable_len, itable_len, static_field_size, nonstatic_oop_map_size, rt, access_flags, is_anonymous) {}

public:
  virtual bool oop_is_instanceClassLoader() const { return true; }

  InstanceClassLoaderKlass() { assert(DumpSharedSpaces || UseSharedSpaces, "only for CDS"); }

  // Iterators
  int oop_oop_iterate(oop obj, ExtendedOopClosure* blk) {
    return oop_oop_iterate_v(obj, blk);
  }
  int oop_oop_iterate_m(oop obj, ExtendedOopClosure* blk, MemRegion mr) {
    return oop_oop_iterate_v_m(obj, blk, mr);
  }

#define InstanceClassLoaderKlass_OOP_OOP_ITERATE_DECL(OopClosureType, nv_suffix)                \
  int oop_oop_iterate##nv_suffix(oop obj, OopClosureType* blk);                         \
  int oop_oop_iterate##nv_suffix##_m(oop obj, OopClosureType* blk, MemRegion mr);

  ALL_OOP_OOP_ITERATE_CLOSURES_1(InstanceClassLoaderKlass_OOP_OOP_ITERATE_DECL)
  ALL_OOP_OOP_ITERATE_CLOSURES_2(InstanceClassLoaderKlass_OOP_OOP_ITERATE_DECL)

#if INCLUDE_ALL_GCS
#define InstanceClassLoaderKlass_OOP_OOP_ITERATE_BACKWARDS_DECL(OopClosureType, nv_suffix)      \
  int oop_oop_iterate_backwards##nv_suffix(oop obj, OopClosureType* blk);

  ALL_OOP_OOP_ITERATE_CLOSURES_1(InstanceClassLoaderKlass_OOP_OOP_ITERATE_BACKWARDS_DECL)
  ALL_OOP_OOP_ITERATE_CLOSURES_2(InstanceClassLoaderKlass_OOP_OOP_ITERATE_BACKWARDS_DECL)
#endif // INCLUDE_ALL_GCS

    // Garbage collection
  void oop_follow_contents(oop obj);

  // Parallel Scavenge and Parallel Old
  PARALLEL_GC_DECLS
};

#endif // SHARE_VM_OOPS_INSTANCECLASSLOADERKLASS_HPP
