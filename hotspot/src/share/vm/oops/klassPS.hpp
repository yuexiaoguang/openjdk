#ifndef SHARE_VM_OOPS_KLASSPS_HPP
#define SHARE_VM_OOPS_KLASSPS_HPP

  // Expands to Parallel Scavenge and Parallel Old declarations

#include "utilities/macros.hpp"

#if INCLUDE_ALL_GCS
#define PARALLEL_GC_DECLS \
  virtual void oop_push_contents(PSPromotionManager* pm, oop obj);          \
  /* Parallel Old GC support                                                \
                                                                            \
   The 2-arg version of oop_update_pointers is for objects that are         \
   known not to cross chunk boundaries.  The 4-arg version is for           \
   objects that do (or may) cross chunk boundaries; it updates only those   \
   oops that are in the region [beg_addr, end_addr).  */                    \
  virtual void oop_follow_contents(ParCompactionManager* cm, oop obj);      \
  virtual int  oop_update_pointers(ParCompactionManager* cm, oop obj);

// Pure virtual version for klass.hpp
#define PARALLEL_GC_DECLS_PV \
  virtual void oop_push_contents(PSPromotionManager* pm, oop obj) = 0;      \
  virtual void oop_follow_contents(ParCompactionManager* cm, oop obj) = 0;  \
  virtual int  oop_update_pointers(ParCompactionManager* cm, oop obj) = 0;
#else  // INCLUDE_ALL_GCS
#define PARALLEL_GC_DECLS
#define PARALLEL_GC_DECLS_PV
#endif // INCLUDE_ALL_GCS

#endif // SHARE_VM_OOPS_KLASSPS_HPP
