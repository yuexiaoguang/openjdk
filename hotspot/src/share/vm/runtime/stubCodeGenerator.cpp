#include "precompiled.hpp"
#include "asm/macroAssembler.hpp"
#include "asm/macroAssembler.inline.hpp"
#include "code/codeCache.hpp"
#include "compiler/disassembler.hpp"
#include "oops/oop.inline.hpp"
#include "prims/forte.hpp"
#include "runtime/stubCodeGenerator.hpp"


// Implementation of StubCodeDesc

StubCodeDesc* StubCodeDesc::_list = NULL;
int           StubCodeDesc::_count = 0;


StubCodeDesc* StubCodeDesc::desc_for(address pc) {
  StubCodeDesc* p = _list;
  while (p != NULL && !p->contains(pc)) p = p->_next;
  // p == NULL || p->contains(pc)
  return p;
}


StubCodeDesc* StubCodeDesc::desc_for_index(int index) {
  StubCodeDesc* p = _list;
  while (p != NULL && p->index() != index) p = p->_next;
  return p;
}


const char* StubCodeDesc::name_for(address pc) {
  StubCodeDesc* p = desc_for(pc);
  return p == NULL ? NULL : p->name();
}


void StubCodeDesc::print_on(outputStream* st) const {
  st->print(group());
  st->print("::");
  st->print(name());
  st->print(" [" INTPTR_FORMAT ", " INTPTR_FORMAT "[ (%d bytes)", begin(), end(), size_in_bytes());
}

// Implementation of StubCodeGenerator

StubCodeGenerator::StubCodeGenerator(CodeBuffer* code, bool print_code) {
  _masm = new MacroAssembler(code);
  _first_stub = _last_stub = NULL;
  _print_code = print_code;
}

extern "C" {
  static int compare_cdesc(const void* void_a, const void* void_b) {
    int ai = (*((StubCodeDesc**) void_a))->index();
    int bi = (*((StubCodeDesc**) void_b))->index();
    return ai - bi;
  }
}

StubCodeGenerator::~StubCodeGenerator() {
  if (PrintStubCode || _print_code) {
    CodeBuffer* cbuf = _masm->code();
    CodeBlob*   blob = CodeCache::find_blob_unsafe(cbuf->insts()->start());
    if (blob != NULL) {
      blob->set_strings(cbuf->strings());
    }
    bool saw_first = false;
    StubCodeDesc* toprint[1000];
    int toprint_len = 0;
    for (StubCodeDesc* cdesc = _last_stub; cdesc != NULL; cdesc = cdesc->_next) {
      toprint[toprint_len++] = cdesc;
      if (cdesc == _first_stub) { saw_first = true; break; }
    }
    assert(saw_first, "must get both first & last");
    // Print in reverse order:
    qsort(toprint, toprint_len, sizeof(toprint[0]), compare_cdesc);
    for (int i = 0; i < toprint_len; i++) {
      StubCodeDesc* cdesc = toprint[i];
      cdesc->print();
      tty->cr();
      Disassembler::decode(cdesc->begin(), cdesc->end());
      tty->cr();
    }
  }
}


void StubCodeGenerator::stub_prolog(StubCodeDesc* cdesc) {
  // default implementation - do nothing
}


void StubCodeGenerator::stub_epilog(StubCodeDesc* cdesc) {
  // default implementation - record the cdesc
  if (_first_stub == NULL)  _first_stub = cdesc;
  _last_stub = cdesc;
}


// Implementation of CodeMark

StubCodeMark::StubCodeMark(StubCodeGenerator* cgen, const char* group, const char* name) {
  _cgen  = cgen;
  _cdesc = new StubCodeDesc(group, name, _cgen->assembler()->pc());
  _cgen->stub_prolog(_cdesc);
  // define the stub's beginning (= entry point) to be after the prolog:
  _cdesc->set_begin(_cgen->assembler()->pc());
}

StubCodeMark::~StubCodeMark() {
  _cgen->assembler()->flush();
  _cdesc->set_end(_cgen->assembler()->pc());
  assert(StubCodeDesc::_list == _cdesc, "expected order on list");
  _cgen->stub_epilog(_cdesc);
  Forte::register_stub(_cdesc->name(), _cdesc->begin(), _cdesc->end());

  if (JvmtiExport::should_post_dynamic_code_generated()) {
    JvmtiExport::post_dynamic_code_generated(_cdesc->name(), _cdesc->begin(), _cdesc->end());
  }
}
