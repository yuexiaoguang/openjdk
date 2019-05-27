#include "classfile/classLoaderData.hpp"
#include "classfile/javaClasses.hpp"

inline ClassLoaderData* ClassLoaderData::class_loader_data_or_null(oop loader) {
  if (loader == NULL) {
    return ClassLoaderData::the_null_class_loader_data();
  }
  return java_lang_ClassLoader::loader_data(loader);
}

inline ClassLoaderData* ClassLoaderData::class_loader_data(oop loader) {
  ClassLoaderData* loader_data = class_loader_data_or_null(loader);
  assert(loader_data != NULL, "Must be");
  return loader_data;
}


inline ClassLoaderData *ClassLoaderDataGraph::find_or_create(Handle loader, TRAPS) {
  assert(loader() != NULL,"Must be a class loader");
  // Gets the class loader data out of the java/lang/ClassLoader object, if non-null
  // it's already in the loader_data, so no need to add
  ClassLoaderData* loader_data= java_lang_ClassLoader::loader_data(loader());
  if (loader_data) {
     return loader_data;
  }
  return ClassLoaderDataGraph::add(loader, false, THREAD);
}
