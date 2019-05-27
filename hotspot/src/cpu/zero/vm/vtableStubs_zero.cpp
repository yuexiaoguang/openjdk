
#include "precompiled.hpp"
#include "asm/assembler.hpp"
#include "assembler_zero.inline.hpp"
#include "code/vtableStubs.hpp"
#include "interp_masm_zero.hpp"
#include "memory/resourceArea.hpp"
#include "oops/instanceKlass.hpp"
#include "oops/klassVtable.hpp"
#include "runtime/sharedRuntime.hpp"
#include "vmreg_zero.inline.hpp"
#ifdef COMPILER2
#include "opto/runtime.hpp"
#endif

VtableStub* VtableStubs::create_vtable_stub(int vtable_index) {
  ShouldNotCallThis();
  return NULL;
}

VtableStub* VtableStubs::create_itable_stub(int vtable_index) {
  ShouldNotCallThis();
  return NULL;
}

int VtableStub::pd_code_size_limit(bool is_vtable_stub) {
  ShouldNotCallThis();
  return 0;
}

int VtableStub::pd_code_alignment() {
  ShouldNotCallThis();
  return 0;
}
