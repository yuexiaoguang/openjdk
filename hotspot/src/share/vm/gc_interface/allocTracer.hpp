#ifndef SHARE_VM_GC_INTERFACE_ALLOCTRACER_HPP
#define SHARE_VM_GC_INTERFACE_ALLOCTRACER_HPP

#include "memory/allocation.hpp"
#include "runtime/handles.hpp"

class AllocTracer : AllStatic {
  public:
    static void send_allocation_outside_tlab_event(KlassHandle klass, size_t alloc_size);
    static void send_allocation_in_new_tlab_event(KlassHandle klass, size_t tlab_size, size_t alloc_size);
};

#endif /* SHARE_VM_GC_INTERFACE_ALLOCTRACER_HPP */
