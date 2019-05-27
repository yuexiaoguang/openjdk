
#include "prims/jvm.h"
#include "utilities/decoder_elf.hpp"

#include <cxxabi.h>

bool ElfDecoder::demangle(const char* symbol, char *buf, int buflen) {
  int   status;
  char* result;
  size_t size = (size_t)buflen;

  // Don't pass buf to __cxa_demangle. In case of the 'buf' is too small,
  // __cxa_demangle will call system "realloc" for additional memory, which
  // may use different malloc/realloc mechanism that allocates 'buf'.
  if ((result = abi::__cxa_demangle(symbol, NULL, NULL, &status)) != NULL) {
    jio_snprintf(buf, buflen, "%s", result);
      // call c library's free
      ::free(result);
      return true;
  }
  return false;
}

