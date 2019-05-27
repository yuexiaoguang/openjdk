
#include "precompiled.hpp"
#include "opto/compile.hpp"
#include "opto/node.hpp"

// processor dependent initialization for i486

void Compile::pd_compiler2_init() {
  guarantee(CodeEntryAlignment >= InteriorEntryAlignment, "" );
  // QQQ presumably all 64bit cpu's support this. Seems like the ifdef could
  // simply be left out.
#ifndef AMD64
  if (!VM_Version::supports_cmov()) {
    ConditionalMoveLimit = 0;
  }
#endif // AMD64
}
