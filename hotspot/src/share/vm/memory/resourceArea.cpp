#include "precompiled.hpp"
#include "memory/allocation.inline.hpp"
#include "memory/resourceArea.hpp"
#include "runtime/mutexLocker.hpp"
#include "runtime/thread.inline.hpp"

//------------------------------ResourceMark-----------------------------------
debug_only(int ResourceArea::_warned;)      // to suppress multiple warnings

// The following routines are declared in allocation.hpp and used everywhere:

// Allocation in thread-local resource area
extern char* resource_allocate_bytes(size_t size, AllocFailType alloc_failmode) {
  return Thread::current()->resource_area()->allocate_bytes(size, alloc_failmode);
}
extern char* resource_allocate_bytes(Thread* thread, size_t size, AllocFailType alloc_failmode) {
  return thread->resource_area()->allocate_bytes(size, alloc_failmode);
}

extern char* resource_reallocate_bytes( char *old, size_t old_size, size_t new_size, AllocFailType alloc_failmode){
  return (char*)Thread::current()->resource_area()->Arealloc(old, old_size, new_size, alloc_failmode);
}

extern void resource_free_bytes( char *old, size_t size ) {
  Thread::current()->resource_area()->Afree(old, size);
}

#ifdef ASSERT
ResourceMark::ResourceMark(Thread *thread) {
  assert(thread == Thread::current(), "not the current thread");
  initialize(thread);
}

DeoptResourceMark::DeoptResourceMark(Thread *thread) {
  assert(thread == Thread::current(), "not the current thread");
  initialize(thread);
}
#endif


//-------------------------------------------------------------------------------
// Non-product code
#ifndef PRODUCT

void ResourceMark::free_malloced_objects() {
  Arena::free_malloced_objects(_chunk, _hwm, _max, _area->_hwm);
}

void DeoptResourceMark::free_malloced_objects() {
  Arena::free_malloced_objects(_chunk, _hwm, _max, _area->_hwm);
}

#endif
