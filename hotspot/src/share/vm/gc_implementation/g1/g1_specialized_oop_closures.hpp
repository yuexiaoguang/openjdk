#ifndef SHARE_VM_GC_IMPLEMENTATION_G1_G1_SPECIALIZED_OOP_CLOSURES_HPP
#define SHARE_VM_GC_IMPLEMENTATION_G1_G1_SPECIALIZED_OOP_CLOSURES_HPP

// The following OopClosure types get specialized versions of
// "oop_oop_iterate" that invoke the closures' do_oop methods
// non-virtually, using a mechanism defined in this file.  Extend these
// macros in the obvious way to add specializations for new closures.

// Forward declarations.
enum G1Barrier {
  G1BarrierNone,
  G1BarrierRS,
  G1BarrierEvac,
  G1BarrierKlass
};

template<bool do_gen_barrier, G1Barrier barrier, bool do_mark_object>
class G1ParCopyClosure;

class G1ParScanClosure;
class G1ParPushHeapRSClosure;

typedef G1ParCopyClosure<false, G1BarrierEvac, false> G1ParScanHeapEvacClosure;

class FilterIntoCSClosure;
class FilterOutOfRegionClosure;
class G1CMOopClosure;
class G1RootRegionScanClosure;

// Specialized oop closures from g1RemSet.cpp
class G1Mux2Closure;
class G1TriggerClosure;
class G1InvokeIfNotTriggeredClosure;
class G1UpdateRSOrPushRefOopClosure;

#ifdef FURTHER_SPECIALIZED_OOP_OOP_ITERATE_CLOSURES
#error "FURTHER_SPECIALIZED_OOP_OOP_ITERATE_CLOSURES already defined."
#endif

#define FURTHER_SPECIALIZED_OOP_OOP_ITERATE_CLOSURES(f) \
      f(G1ParScanHeapEvacClosure,_nv)                   \
      f(G1ParScanClosure,_nv)                           \
      f(G1ParPushHeapRSClosure,_nv)                     \
      f(FilterIntoCSClosure,_nv)                        \
      f(FilterOutOfRegionClosure,_nv)                   \
      f(G1CMOopClosure,_nv)                             \
      f(G1RootRegionScanClosure,_nv)                    \
      f(G1Mux2Closure,_nv)                              \
      f(G1TriggerClosure,_nv)                           \
      f(G1InvokeIfNotTriggeredClosure,_nv)              \
      f(G1UpdateRSOrPushRefOopClosure,_nv)

#ifdef FURTHER_SPECIALIZED_SINCE_SAVE_MARKS_CLOSURES
#error "FURTHER_SPECIALIZED_SINCE_SAVE_MARKS_CLOSURES already defined."
#endif

#define FURTHER_SPECIALIZED_SINCE_SAVE_MARKS_CLOSURES(f)

#endif // SHARE_VM_GC_IMPLEMENTATION_G1_G1_SPECIALIZED_OOP_CLOSURES_HPP
