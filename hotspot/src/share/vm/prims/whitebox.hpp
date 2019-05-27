#ifndef SHARE_VM_PRIMS_WHITEBOX_HPP
#define SHARE_VM_PRIMS_WHITEBOX_HPP

#include "prims/jni.h"

#include "memory/allocation.hpp"
#include "oops/oopsHierarchy.hpp"

// Entry macro to transition from JNI to VM state.

#define WB_ENTRY(result_type, header) JNI_ENTRY(result_type, header)
#define WB_END JNI_END
#define WB_METHOD_DECLARE(result_type) extern "C" result_type JNICALL

class WhiteBox : public AllStatic {
 private:
  static bool _used;
 public:
  static bool used()     { return _used; }
  static void set_used() { _used = true; }
  static int offset_for_field(const char* field_name, oop object,
    Symbol* signature_symbol);
  static const char* lookup_jstring(const char* field_name, oop object);
  static bool lookup_bool(const char* field_name, oop object);
};



#endif // SHARE_VM_PRIMS_WHITEBOX_HPP
