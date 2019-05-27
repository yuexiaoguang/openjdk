#include "precompiled.hpp"
#include "oops/objArrayKlass.hpp"
#include "oops/objArrayOop.hpp"
#include "oops/oop.inline.hpp"

#define ObjArrayOop_OOP_ITERATE_DEFN(OopClosureType, nv_suffix)                    \
                                                                                   \
int objArrayOopDesc::oop_iterate_range(OopClosureType* blk, int start, int end) {  \
  SpecializationStats::record_call();                                              \
  return ((ObjArrayKlass*)klass())->oop_oop_iterate_range##nv_suffix(this, blk, start, end); \
}

ALL_OOP_OOP_ITERATE_CLOSURES_1(ObjArrayOop_OOP_ITERATE_DEFN)
ALL_OOP_OOP_ITERATE_CLOSURES_2(ObjArrayOop_OOP_ITERATE_DEFN)
