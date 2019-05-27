#ifndef SHARE_VM_UTILITIES_DECODER_ELF_HPP
#define SHARE_VM_UTILITIES_DECODER_ELF_HPP

#if !defined(_WINDOWS) && !defined(__APPLE__)

#include "utilities/decoder.hpp"
#include "utilities/elfFile.hpp"

class ElfDecoder : public AbstractDecoder {

public:
  ElfDecoder() {
    _opened_elf_files = NULL;
    _decoder_status = no_error;
  }
  ~ElfDecoder();

  bool can_decode_C_frame_in_vm() const { return true; }

  bool demangle(const char* symbol, char *buf, int buflen);
  bool decode(address addr, char *buf, int buflen, int* offset, const char* filepath = NULL);
  bool decode(address addr, char *buf, int buflen, int* offset, const void *base) {
    ShouldNotReachHere();
    return false;
  }

private:
  ElfFile*         get_elf_file(const char* filepath);

private:
  ElfFile*         _opened_elf_files;
};

#endif
#endif // SHARE_VM_UTILITIES_DECODER_ELF_HPP
