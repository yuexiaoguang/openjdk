#ifndef SHARE_VM_UTILITIES_ELF_STRING_TABLE_HPP
#define SHARE_VM_UTILITIES_ELF_STRING_TABLE_HPP

#if !defined(_WINDOWS) && !defined(__APPLE__)

#include "memory/allocation.hpp"
#include "utilities/decoder.hpp"
#include "utilities/elfFile.hpp"


// The string table represents a string table section in an elf file.
// Whenever there is enough memory, it will load whole string table as
// one blob. Otherwise, it will load string from file when requested.
class ElfStringTable: CHeapObj<mtInternal> {
  friend class ElfFile;
 public:
  ElfStringTable(FILE* file, Elf_Shdr shdr, int index);
  ~ElfStringTable();

  // section index
  int index() { return m_index; };

  // get string at specified offset
  bool string_at(int offset, char* buf, int buflen);

  // get status code
  NullDecoder::decoder_status get_status() { return m_status; };

 protected:
  ElfStringTable*        m_next;

  // section index
  int                      m_index;

  // holds complete string table if can
  // allocate enough memory
  const char*              m_table;

  // file contains string table
  FILE*                    m_file;

  // section header
  Elf_Shdr                 m_shdr;

  // error code
  NullDecoder::decoder_status  m_status;
};

#endif // _WINDOWS and _APPLE

#endif // SHARE_VM_UTILITIES_ELF_STRING_TABLE_HPP
