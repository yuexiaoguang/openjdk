#include "precompiled.hpp"
#include "classfile/classLoaderData.hpp"
#include "memory/heapInspection.hpp"
#include "memory/metadataFactory.hpp"
#include "memory/oopFactory.hpp"
#include "oops/annotations.hpp"
#include "oops/instanceKlass.hpp"
#include "utilities/ostream.hpp"

// Allocate annotations in metadata area
Annotations* Annotations::allocate(ClassLoaderData* loader_data, TRAPS) {
  return new (loader_data, size(), true, MetaspaceObj::AnnotationType, THREAD) Annotations();
}

// helper
void Annotations::free_contents(ClassLoaderData* loader_data, Array<AnnotationArray*>* p) {
  if (p != NULL) {
    for (int i = 0; i < p->length(); i++) {
      MetadataFactory::free_array<u1>(loader_data, p->at(i));
    }
    MetadataFactory::free_array<AnnotationArray*>(loader_data, p);
  }
}

void Annotations::deallocate_contents(ClassLoaderData* loader_data) {
  if (class_annotations() != NULL) {
    MetadataFactory::free_array<u1>(loader_data, class_annotations());
  }
  free_contents(loader_data, fields_annotations());

  if (class_type_annotations() != NULL) {
    MetadataFactory::free_array<u1>(loader_data, class_type_annotations());
  }
  free_contents(loader_data, fields_type_annotations());
}

// Copy annotations to JVM call or reflection to the java heap.
// The alternative to creating this array and adding to Java heap pressure
// is to have a hashtable of the already created typeArrayOops
typeArrayOop Annotations::make_java_array(AnnotationArray* annotations, TRAPS) {
  if (annotations != NULL) {
    int length = annotations->length();
    typeArrayOop copy = oopFactory::new_byteArray(length, CHECK_NULL);
    for (int i = 0; i< length; i++) {
      copy->byte_at_put(i, annotations->at(i));
    }
    return copy;
  } else {
    return NULL;
  }
}


void Annotations::print_value_on(outputStream* st) const {
  st->print("Anotations(" INTPTR_FORMAT ")", this);
}

#if INCLUDE_SERVICES
// Size Statistics

julong Annotations::count_bytes(Array<AnnotationArray*>* p) {
  julong bytes = 0;
  if (p != NULL) {
    for (int i = 0; i < p->length(); i++) {
      bytes += KlassSizeStats::count_array(p->at(i));
    }
    bytes += KlassSizeStats::count_array(p);
  }
  return bytes;
}

void Annotations::collect_statistics(KlassSizeStats *sz) const {
  sz->_annotations_bytes = sz->count(this);
  sz->_class_annotations_bytes = sz->count(class_annotations());
  sz->_class_type_annotations_bytes = sz->count(class_type_annotations());
  sz->_fields_annotations_bytes = count_bytes(fields_annotations());
  sz->_fields_type_annotations_bytes = count_bytes(fields_type_annotations());

  sz->_annotations_bytes +=
      sz->_class_annotations_bytes +
      sz->_class_type_annotations_bytes +
      sz->_fields_annotations_bytes +
      sz->_fields_type_annotations_bytes;

  sz->_ro_bytes += sz->_annotations_bytes;
}
#endif // INCLUDE_SERVICES

#define BULLET  " - "

#ifndef PRODUCT
void Annotations::print_on(outputStream* st) const {
  st->print(BULLET"class_annotations            "); class_annotations()->print_value_on(st);
  st->print(BULLET"fields_annotations           "); fields_annotations()->print_value_on(st);
  st->print(BULLET"class_type_annotations       "); class_type_annotations()->print_value_on(st);
  st->print(BULLET"fields_type_annotations      "); fields_type_annotations()->print_value_on(st);
}
#endif // PRODUCT
