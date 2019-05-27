#ifndef SHARE_VM_C1_C1_CFGPRINTER_HPP
#define SHARE_VM_C1_C1_CFGPRINTER_HPP

#include "c1/c1_Compilation.hpp"
#include "c1/c1_Instruction.hpp"

#ifndef PRODUCT

// This is a utility class used for recording the results of a
// compilation for later analysis.

class CFGPrinterOutput;
class IntervalList;

class CFGPrinter : public AllStatic {
private:
  static CFGPrinterOutput* _output;
public:
  static CFGPrinterOutput* output() { assert(_output != NULL, ""); return _output; }


  static void print_compilation(Compilation* compilation);
  static void print_cfg(BlockList* blocks, const char* name, bool do_print_HIR, bool do_print_LIR);
  static void print_cfg(IR* blocks, const char* name, bool do_print_HIR, bool do_print_LIR);
  static void print_intervals(IntervalList* intervals, const char* name);
};

#endif

#endif // SHARE_VM_C1_C1_CFGPRINTER_HPP
