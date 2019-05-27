#ifndef SHARE_VM_RUNTIME_DTRACEJSDT_HPP
#define SHARE_VM_RUNTIME_DTRACEJSDT_HPP

#include "code/nmethod.hpp"
#ifdef TARGET_ARCH_x86
# include "nativeInst_x86.hpp"
#endif
#ifdef TARGET_ARCH_sparc
# include "nativeInst_sparc.hpp"
#endif
#ifdef TARGET_ARCH_zero
# include "nativeInst_zero.hpp"
#endif
#ifdef TARGET_ARCH_arm
# include "nativeInst_arm.hpp"
#endif
#ifdef TARGET_ARCH_ppc
# include "nativeInst_ppc.hpp"
#endif

class RegisteredProbes;
typedef jlong OpaqueProbes;

class DTraceJSDT : AllStatic {
 private:

  static int pd_activate(void* moduleBaseAddress, jstring module,
      jint providers_count, JVM_DTraceProvider* providers);
  static void pd_dispose(int handle);
  static jboolean pd_is_supported();

 public:

  static OpaqueProbes activate(
      jint version, jstring module_name, jint providers_count,
      JVM_DTraceProvider* providers, TRAPS);
  static jboolean is_probe_enabled(jmethodID method);
  static void dispose(OpaqueProbes handle);
  static jboolean is_supported();
};

class RegisteredProbes : public CHeapObj<mtInternal> {
 private:
  nmethod** _nmethods;      // all the probe methods
  size_t    _count;         // number of probe methods
  int       _helper_handle; // DTrace-assigned identifier

 public:
  RegisteredProbes(size_t count) {
    _count = count;
    _nmethods = NEW_C_HEAP_ARRAY(nmethod*, count, mtInternal);
  }

  ~RegisteredProbes() {
    for (size_t i = 0; i < _count; ++i) {
      // Let the sweeper reclaim it
      _nmethods[i]->make_not_entrant();
      _nmethods[i]->method()->clear_code();
    }
    FREE_C_HEAP_ARRAY(nmethod*, _nmethods, mtInternal);
    _nmethods = NULL;
    _count = 0;
  }

  static RegisteredProbes* toRegisteredProbes(OpaqueProbes p) {
    return (RegisteredProbes*)(intptr_t)p;
  }

  static OpaqueProbes toOpaqueProbes(RegisteredProbes* p) {
    return (OpaqueProbes)(intptr_t)p;
  }

  void set_helper_handle(int handle) { _helper_handle = handle; }
  int helper_handle() const { return _helper_handle; }

  nmethod* nmethod_at(size_t i) {
    assert(i >= 0 && i < _count, "bad nmethod index");
    return _nmethods[i];
  }

  void nmethod_at_put(size_t i, nmethod* nm) {
    assert(i >= 0 && i < _count, "bad nmethod index");
    _nmethods[i] = nm;
  }
};

#endif // SHARE_VM_RUNTIME_DTRACEJSDT_HPP
