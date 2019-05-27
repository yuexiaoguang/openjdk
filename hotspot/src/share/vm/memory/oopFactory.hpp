#ifndef SHARE_VM_MEMORY_OOPFACTORY_HPP
#define SHARE_VM_MEMORY_OOPFACTORY_HPP

#include "classfile/symbolTable.hpp"
#include "classfile/systemDictionary.hpp"
#include "memory/referenceType.hpp"
#include "memory/universe.hpp"
#include "oops/objArrayKlass.hpp"
#include "oops/oop.hpp"
#include "oops/typeArrayKlass.hpp"
#include "utilities/growableArray.hpp"

// oopFactory is a class used for creating new objects.

class vframeArray;

class oopFactory: AllStatic {
 public:
  // Basic type leaf array allocation
  static typeArrayOop    new_boolArray  (int length, TRAPS) { return TypeArrayKlass::cast(Universe::boolArrayKlassObj  ())->allocate(length, CHECK_NULL); }
  static typeArrayOop    new_charArray  (int length, TRAPS) { return TypeArrayKlass::cast(Universe::charArrayKlassObj  ())->allocate(length, CHECK_NULL); }
  static typeArrayOop    new_singleArray(int length, TRAPS) { return TypeArrayKlass::cast(Universe::singleArrayKlassObj())->allocate(length, CHECK_NULL); }
  static typeArrayOop    new_doubleArray(int length, TRAPS) { return TypeArrayKlass::cast(Universe::doubleArrayKlassObj())->allocate(length, CHECK_NULL); }
  static typeArrayOop    new_byteArray  (int length, TRAPS) { return TypeArrayKlass::cast(Universe::byteArrayKlassObj  ())->allocate(length, CHECK_NULL); }
  static typeArrayOop    new_shortArray (int length, TRAPS) { return TypeArrayKlass::cast(Universe::shortArrayKlassObj ())->allocate(length, CHECK_NULL); }
  static typeArrayOop    new_intArray   (int length, TRAPS) { return TypeArrayKlass::cast(Universe::intArrayKlassObj   ())->allocate(length, CHECK_NULL); }
  static typeArrayOop    new_longArray  (int length, TRAPS) { return TypeArrayKlass::cast(Universe::longArrayKlassObj  ())->allocate(length, CHECK_NULL); }

  // create java.lang.Object[]
  static objArrayOop     new_objectArray(int length, TRAPS)  {
    assert(Universe::objectArrayKlassObj() != NULL, "Too early?");
    return ObjArrayKlass::
      cast(Universe::objectArrayKlassObj())->allocate(length, CHECK_NULL);
  }

  static typeArrayOop    new_charArray           (const char* utf8_str,  TRAPS);
  static typeArrayOop    new_tenured_charArray(int length, TRAPS);

  static typeArrayOop    new_typeArray(BasicType type, int length, TRAPS);
  static typeArrayOop    new_typeArray_nozero(BasicType type, int length, TRAPS);
  static typeArrayOop    new_metaDataArray(int length, TRAPS);

  // Regular object arrays
  static objArrayOop     new_objArray(Klass* klass, int length, TRAPS);
};

#endif // SHARE_VM_MEMORY_OOPFACTORY_HPP
