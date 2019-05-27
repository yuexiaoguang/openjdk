#ifndef SHARE_VM_SERVICES_NMT_DCMD_HPP
#define SHARE_VM_SERVICES_NMT_DCMD_HPP

#include "services/diagnosticArgument.hpp"
#include "services/diagnosticFramework.hpp"

/**
 * Native memory tracking DCmd implementation
 */
class NMTDCmd: public DCmdWithParser {
 protected:
  DCmdArgument<bool>  _summary;
  DCmdArgument<bool>  _detail;
  DCmdArgument<bool>  _baseline;
  DCmdArgument<bool>  _summary_diff;
  DCmdArgument<bool>  _detail_diff;
  DCmdArgument<bool>  _shutdown;
  DCmdArgument<bool>  _auto_shutdown;
#ifndef PRODUCT
  DCmdArgument<bool>  _debug;
#endif
  DCmdArgument<char*> _scale;

 public:
  NMTDCmd(outputStream* output, bool heap);
  static const char* name() { return "VM.native_memory"; }
  static const char* description() {
    return "Print native memory usage";
  }
  static const char* impact() {
    return "Medium";
  }
  static const JavaPermission permission() {
    JavaPermission p = {"java.lang.management.ManagementPermission",
                        "monitor", NULL};
    return p;
  }
  static int num_arguments();
  virtual void execute(DCmdSource source, TRAPS);
};

#endif // SHARE_VM_SERVICES_NMT_DCMD_HPP
