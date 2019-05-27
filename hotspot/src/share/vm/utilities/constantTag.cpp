#include "precompiled.hpp"
#include "utilities/constantTag.hpp"

#ifndef PRODUCT

void constantTag::print_on(outputStream* st) const {
  st->print(internal_name());
}

#endif // PRODUCT

BasicType constantTag::basic_type() const {
  switch (_tag) {
    case JVM_CONSTANT_Integer :
      return T_INT;
    case JVM_CONSTANT_Float :
      return T_FLOAT;
    case JVM_CONSTANT_Long :
      return T_LONG;
    case JVM_CONSTANT_Double :
      return T_DOUBLE;

    case JVM_CONSTANT_Class :
    case JVM_CONSTANT_String :
    case JVM_CONSTANT_UnresolvedClass :
    case JVM_CONSTANT_UnresolvedClassInError :
    case JVM_CONSTANT_ClassIndex :
    case JVM_CONSTANT_StringIndex :
    case JVM_CONSTANT_MethodHandle :
    case JVM_CONSTANT_MethodHandleInError :
    case JVM_CONSTANT_MethodType :
    case JVM_CONSTANT_MethodTypeInError :
      return T_OBJECT;
    default:
      ShouldNotReachHere();
      return T_ILLEGAL;
  }
}


jbyte constantTag::non_error_value() const {
  switch (_tag) {
  case JVM_CONSTANT_UnresolvedClassInError:
    return JVM_CONSTANT_UnresolvedClass;
  case JVM_CONSTANT_MethodHandleInError:
    return JVM_CONSTANT_MethodHandle;
  case JVM_CONSTANT_MethodTypeInError:
    return JVM_CONSTANT_MethodType;
  default:
    return _tag;
  }
}


const char* constantTag::internal_name() const {
  switch (_tag) {
    case JVM_CONSTANT_Invalid :
      return "Invalid index";
    case JVM_CONSTANT_Class :
      return "Class";
    case JVM_CONSTANT_Fieldref :
      return "Field";
    case JVM_CONSTANT_Methodref :
      return "Method";
    case JVM_CONSTANT_InterfaceMethodref :
      return "InterfaceMethod";
    case JVM_CONSTANT_String :
      return "String";
    case JVM_CONSTANT_Integer :
      return "Integer";
    case JVM_CONSTANT_Float :
      return "Float";
    case JVM_CONSTANT_Long :
      return "Long";
    case JVM_CONSTANT_Double :
      return "Double";
    case JVM_CONSTANT_NameAndType :
      return "NameAndType";
    case JVM_CONSTANT_MethodHandle :
      return "MethodHandle";
    case JVM_CONSTANT_MethodHandleInError :
      return "MethodHandle Error";
    case JVM_CONSTANT_MethodType :
      return "MethodType";
    case JVM_CONSTANT_MethodTypeInError :
      return "MethodType Error";
    case JVM_CONSTANT_InvokeDynamic :
      return "InvokeDynamic";
    case JVM_CONSTANT_Utf8 :
      return "Utf8";
    case JVM_CONSTANT_UnresolvedClass :
      return "Unresolved Class";
    case JVM_CONSTANT_UnresolvedClassInError :
      return "Unresolved Class Error";
    case JVM_CONSTANT_ClassIndex :
      return "Unresolved Class Index";
    case JVM_CONSTANT_StringIndex :
      return "Unresolved String Index";
    default:
      ShouldNotReachHere();
      return "Illegal";
  }
}
