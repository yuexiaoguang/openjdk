#include "precompiled.hpp"
#include "memory/resourceArea.hpp"
#include "runtime/thread.inline.hpp"
#include "utilities/growableArray.hpp"

#ifdef ASSERT
void GenericGrowableArray::set_nesting() {
  if (on_stack()) {
    _nesting = Thread::current()->resource_area()->nesting();
  }
}

void GenericGrowableArray::check_nesting() {
  // Check for insidious allocation bug: if a GrowableArray overflows, the
  // grown array must be allocated under the same ResourceMark as the original.
  // Otherwise, the _data array will be deallocated too early.
  if (on_stack() &&
      _nesting != Thread::current()->resource_area()->nesting()) {
    fatal("allocation bug: GrowableArray could grow within nested ResourceMark");
  }
}
#endif

void* GenericGrowableArray::raw_allocate(int elementSize) {
  assert(_max >= 0, "integer overflow");
  size_t byte_size = elementSize * (size_t) _max;
  if (on_stack()) {
    return (void*)resource_allocate_bytes(byte_size);
  } else if (on_C_heap()) {
    return (void*)AllocateHeap(byte_size, _memflags);
  } else {
    return _arena->Amalloc(byte_size);
  }
}
