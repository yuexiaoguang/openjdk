#ifndef SHARE_VM_UTILITIES_ELF_SYMBOL_TABLE_HPP
#define SHARE_VM_UTILITIES_ELF_SYMBOL_TABLE_HPP

#if !defined(_WINDOWS) && !defined(__APPLE__)


#include "memory/allocation.hpp"
#include "utilities/decoder.hpp"
#include "utilities/elfFile.hpp"

/*
 * symbol table object represents a symbol section in an elf file.
 * Whenever possible, it will load all symbols from the corresponding section
 * of the elf file into memory. Otherwise, it will walk the section in file
 * to look up the symbol that nearest the given address.
 */
class ElfSymbolTable: public CHeapObj<mtInternal> {
  friend class ElfFile;
 public:
  ElfSymbolTable(FILE* file, Elf_Shdr shdr);
  ~ElfSymbolTable();

  // search the symbol that is nearest to the specified address.
  bool lookup(address addr, int* stringtableIndex, int* posIndex, int* offset);

  NullDecoder::decoder_status get_status() { return m_status; };

 protected:
  ElfSymbolTable*  m_next;

  // holds a complete symbol table section if
  // can allocate enough memory
  Elf_Sym*            m_symbols;

  // file contains string table
  FILE*               m_file;

  // section header
  Elf_Shdr            m_shdr;

  NullDecoder::decoder_status  m_status;
};

#endif // _WINDOWS and _APPLE

#endif // SHARE_VM_UTILITIES_ELF_SYMBOL_TABLE_HPP
