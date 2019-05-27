
#ifndef _ELFMACROS_H_
#define _ELFMACROS_H_

#define ELF_NHDR        Elf_Note

#if defined(_LP64)
#define ELF_EHDR        Elf64_Ehdr
#define ELF_SHDR        Elf64_Shdr
#define ELF_PHDR        Elf64_Phdr
#define ELF_SYM         Elf64_Sym
#define ELF_DYN         Elf64_Dyn
#define ELF_ADDR        Elf64_Addr

#ifndef ELF_ST_TYPE
#define ELF_ST_TYPE     ELF64_ST_TYPE
#endif

#else

#define ELF_EHDR        Elf32_Ehdr
#define ELF_SHDR        Elf32_Shdr
#define ELF_PHDR        Elf32_Phdr
#define ELF_SYM         Elf32_Sym
#define ELF_DYN         Elf32_Dyn
#define ELF_ADDR        Elf32_Addr

#ifndef ELF_ST_TYPE
#define ELF_ST_TYPE     ELF32_ST_TYPE
#endif

#endif


#endif /* _ELFMACROS_H_ */
