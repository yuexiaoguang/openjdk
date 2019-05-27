#include "memory/allocation.inline.hpp"
#include "memory/padded.hpp"
#include "utilities/debug.hpp"
#include "utilities/globalDefinitions.hpp"

// Creates an aligned padded array.
// The memory can't be deleted since the raw memory chunk is not returned.
template <class T, MEMFLAGS flags, size_t alignment>
PaddedEnd<T>* PaddedArray<T, flags, alignment>::create_unfreeable(uint length) {
  // Check that the PaddedEnd class works as intended.
  STATIC_ASSERT(is_size_aligned_(sizeof(PaddedEnd<T>), alignment));

  // Allocate a chunk of memory large enough to allow for some alignment.
  void* chunk = AllocateHeap(length * sizeof(PaddedEnd<T, alignment>) + alignment, flags);

  // Make the initial alignment.
  PaddedEnd<T>* aligned_padded_array = (PaddedEnd<T>*)align_pointer_up(chunk, alignment);

  // Call the default constructor for each element.
  for (uint i = 0; i < length; i++) {
    ::new (&aligned_padded_array[i]) T();
  }

  return aligned_padded_array;
}
