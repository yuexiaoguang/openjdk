#ifndef SHARE_VM_CLASSFILE_RESOLUTIONERRORS_HPP
#define SHARE_VM_CLASSFILE_RESOLUTIONERRORS_HPP

#include "oops/constantPool.hpp"
#include "utilities/hashtable.hpp"

class ResolutionErrorEntry;

// ResolutionError objects are used to record errors encountered during
// constant pool resolution (JVMS 5.4.3).

class ResolutionErrorTable : public Hashtable<ConstantPool*, mtClass> {

public:
  ResolutionErrorTable(int table_size);

  ResolutionErrorEntry* new_entry(int hash, ConstantPool* pool, int cp_index, Symbol* error);
  void free_entry(ResolutionErrorEntry *entry);

  ResolutionErrorEntry* bucket(int i) {
    return (ResolutionErrorEntry*)Hashtable<ConstantPool*, mtClass>::bucket(i);
  }

  ResolutionErrorEntry** bucket_addr(int i) {
    return (ResolutionErrorEntry**)Hashtable<ConstantPool*, mtClass>::bucket_addr(i);
  }

  void add_entry(int index, ResolutionErrorEntry* new_entry) {
    Hashtable<ConstantPool*, mtClass>::add_entry(index,
      (HashtableEntry<ConstantPool*, mtClass>*)new_entry);
  }

  void add_entry(int index, unsigned int hash,
                 constantPoolHandle pool, int which, Symbol* error);


  // find error given the constant pool and constant pool index
  ResolutionErrorEntry* find_entry(int index, unsigned int hash,
                                   constantPoolHandle pool, int cp_index);


  unsigned int compute_hash(constantPoolHandle pool, int cp_index) {
    return (unsigned int) pool->identity_hash() + cp_index;
  }

  // purges unloaded entries from the table
  void purge_resolution_errors();

  // RedefineClasses support - remove obsolete constant pool entry
  void delete_entry(ConstantPool* c);
};


class ResolutionErrorEntry : public HashtableEntry<ConstantPool*, mtClass> {
 private:
  int               _cp_index;
  Symbol*           _error;

 public:
  ConstantPool*      pool() const               { return (ConstantPool*)literal(); }
  ConstantPool**   pool_addr()                { return (ConstantPool**)literal_addr(); }

  int                cp_index() const           { return _cp_index; }
  void               set_cp_index(int cp_index) { _cp_index = cp_index; }

  Symbol*            error() const              { return _error; }
  void               set_error(Symbol* e);

  ResolutionErrorEntry* next() const {
    return (ResolutionErrorEntry*)HashtableEntry<ConstantPool*, mtClass>::next();
  }

  ResolutionErrorEntry** next_addr() {
    return (ResolutionErrorEntry**)HashtableEntry<ConstantPool*, mtClass>::next_addr();
  }
};

#endif // SHARE_VM_CLASSFILE_RESOLUTIONERRORS_HPP
