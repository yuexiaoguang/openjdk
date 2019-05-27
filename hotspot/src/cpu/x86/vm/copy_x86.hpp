
#ifndef CPU_X86_VM_COPY_X86_HPP
#define CPU_X86_VM_COPY_X86_HPP

// Inline functions for memory copy and fill.

// Contains inline asm implementations
#ifdef TARGET_OS_ARCH_linux_x86
# include "copy_linux_x86.inline.hpp"
#endif
#ifdef TARGET_OS_ARCH_solaris_x86
# include "copy_solaris_x86.inline.hpp"
#endif
#ifdef TARGET_OS_ARCH_windows_x86
# include "copy_windows_x86.inline.hpp"
#endif
#ifdef TARGET_OS_ARCH_bsd_x86
# include "copy_bsd_x86.inline.hpp"
#endif


static void pd_fill_to_words(HeapWord* tohw, size_t count, juint value) {
#ifdef AMD64
  julong* to = (julong*) tohw;
  julong  v  = ((julong) value << 32) | value;
  while (count-- > 0) {
    *to++ = v;
  }
#else
  juint* to = (juint*)tohw;
  count *= HeapWordSize / BytesPerInt;
  while (count-- > 0) {
    *to++ = value;
  }
#endif // AMD64
}

static void pd_fill_to_aligned_words(HeapWord* tohw, size_t count, juint value) {
  pd_fill_to_words(tohw, count, value);
}

static void pd_fill_to_bytes(void* to, size_t count, jubyte value) {
  (void)memset(to, value, count);
}

static void pd_zero_to_words(HeapWord* tohw, size_t count) {
  pd_fill_to_words(tohw, count, 0);
}

static void pd_zero_to_bytes(void* to, size_t count) {
  (void)memset(to, 0, count);
}

#endif // CPU_X86_VM_COPY_X86_HPP
