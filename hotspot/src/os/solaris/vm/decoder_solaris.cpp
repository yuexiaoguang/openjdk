
#include "utilities/decoder_elf.hpp"

#include <demangle.h>

bool ElfDecoder::demangle(const char* symbol, char *buf, int buflen) {
  return !cplus_demangle(symbol, buf, (size_t)buflen);
}

