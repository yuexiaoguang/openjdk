#include "precompiled.hpp"
#include "opto/compile.hpp"
#include "opto/node.hpp"

// processor dependent initialization for sparc

void Compile::pd_compiler2_init() {
  guarantee(CodeEntryAlignment >= InteriorEntryAlignment, "" );
}
