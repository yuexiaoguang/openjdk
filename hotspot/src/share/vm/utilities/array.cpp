#include "precompiled.hpp"
#include "memory/resourceArea.hpp"
#include "runtime/thread.inline.hpp"
#include "utilities/array.hpp"


#ifdef ASSERT
void ResourceArray::init_nesting() {
  _nesting = Thread::current()->resource_area()->nesting();
}
#endif


void ResourceArray::sort(size_t esize, ftype f) {
  if (!is_empty()) qsort(_data, length(), esize, f);
}
template <MEMFLAGS F> void CHeapArray<F>::sort(size_t esize, ftype f) {
  if (!is_empty()) qsort(_data, length(), esize, f);
}


void ResourceArray::expand(size_t esize, int i, int& size) {
  // make sure we are expanding within the original resource mark
  assert(
    _nesting == Thread::current()->resource_area()->nesting(),
    "allocating outside original resource mark"
  );
  // determine new size
  if (size == 0) size = 4; // prevent endless loop
  while (i >= size) size *= 2;
  // allocate and initialize new data section
  void* data = resource_allocate_bytes(esize * size);
  memcpy(data, _data, esize * length());
  _data = data;
}


template <MEMFLAGS F> void CHeapArray<F>::expand(size_t esize, int i, int& size) {
  // determine new size
  if (size == 0) size = 4; // prevent endless loop
  while (i >= size) size *= 2;
  // allocate and initialize new data section
  void* data = NEW_C_HEAP_ARRAY(char*, esize * size, F);
  memcpy(data, _data, esize * length());
  FREE_C_HEAP_ARRAY(char*, _data, F);
  _data = data;
}


void ResourceArray::remove_at(size_t esize, int i) {
  assert(0 <= i && i < length(), "index out of bounds");
  _length--;
  void* dst = (char*)_data + i*esize;
  void* src = (char*)dst + esize;
  size_t cnt = (length() - i)*esize;
  memmove(dst, src, cnt);
}

template <MEMFLAGS F> void CHeapArray<F>::remove_at(size_t esize, int i) {
  assert(0 <= i && i < length(), "index out of bounds");
  _length--;
  void* dst = (char*)_data + i*esize;
  void* src = (char*)dst + esize;
  size_t cnt = (length() - i)*esize;
  memmove(dst, src, cnt);
}
