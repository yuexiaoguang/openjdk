#include "precompiled.hpp"
#include "classfile/resolutionErrors.hpp"
#include "memory/resourceArea.hpp"
#include "oops/oop.inline.hpp"
#include "runtime/handles.inline.hpp"
#include "runtime/safepoint.hpp"
#include "utilities/hashtable.inline.hpp"

// add new entry to the table
void ResolutionErrorTable::add_entry(int index, unsigned int hash,
                                     constantPoolHandle pool, int cp_index, Symbol* error)
{
  assert_locked_or_safepoint(SystemDictionary_lock);
  assert(!pool.is_null() && error != NULL, "adding NULL obj");

  ResolutionErrorEntry* entry = new_entry(hash, pool(), cp_index, error);
  add_entry(index, entry);
}

// find entry in the table
ResolutionErrorEntry* ResolutionErrorTable::find_entry(int index, unsigned int hash,
                                                       constantPoolHandle pool, int cp_index)
{
  assert_locked_or_safepoint(SystemDictionary_lock);

  for (ResolutionErrorEntry *error_probe = bucket(index);
                         error_probe != NULL;
                         error_probe = error_probe->next()) {
  if (error_probe->hash() == hash && error_probe->pool() == pool()) {
      return error_probe;;
    }
  }
  return NULL;
}

void ResolutionErrorEntry::set_error(Symbol* e) {
  assert(e == NULL || _error == NULL, "cannot reset error");
  _error = e;
  if (_error != NULL) _error->increment_refcount();
}

// create new error entry
ResolutionErrorEntry* ResolutionErrorTable::new_entry(int hash, ConstantPool* pool,
                                                      int cp_index, Symbol* error)
{
  ResolutionErrorEntry* entry = (ResolutionErrorEntry*)Hashtable<ConstantPool*, mtClass>::new_entry(hash, pool);
  entry->set_cp_index(cp_index);
  NOT_PRODUCT(entry->set_error(NULL);)
  entry->set_error(error);

  return entry;
}

void ResolutionErrorTable::free_entry(ResolutionErrorEntry *entry) {
  // decrement error refcount
  assert(entry->error() != NULL, "error should be set");
  entry->error()->decrement_refcount();
  Hashtable<ConstantPool*, mtClass>::free_entry(entry);
}


// create resolution error table
ResolutionErrorTable::ResolutionErrorTable(int table_size)
    : Hashtable<ConstantPool*, mtClass>(table_size, sizeof(ResolutionErrorEntry)) {
}

// RedefineClasses support - remove matching entry of a
// constant pool that is going away
void ResolutionErrorTable::delete_entry(ConstantPool* c) {
  assert(SafepointSynchronize::is_at_safepoint(), "must be at safepoint");
  for (int i = 0; i < table_size(); i++) {
    for (ResolutionErrorEntry** p = bucket_addr(i); *p != NULL; ) {
      ResolutionErrorEntry* entry = *p;
      assert(entry->pool() != NULL, "resolution error table is corrupt");
      if (entry->pool() == c) {
        *p = entry->next();
        free_entry(entry);
      } else {
        p = entry->next_addr();
      }
    }
  }
}


// Remove unloaded entries from the table
void ResolutionErrorTable::purge_resolution_errors() {
  assert(SafepointSynchronize::is_at_safepoint(), "must be at safepoint");
  for (int i = 0; i < table_size(); i++) {
    for (ResolutionErrorEntry** p = bucket_addr(i); *p != NULL; ) {
      ResolutionErrorEntry* entry = *p;
      assert(entry->pool() != (ConstantPool*)NULL, "resolution error table is corrupt");
      ConstantPool* pool = entry->pool();
      assert(pool->pool_holder() != NULL, "Constant pool without a class?");
      ClassLoaderData* loader_data =
              pool->pool_holder()->class_loader_data();
      if (!loader_data->is_unloading()) {
        p = entry->next_addr();
      } else {
        *p = entry->next();
        free_entry(entry);
      }
    }
  }
}
