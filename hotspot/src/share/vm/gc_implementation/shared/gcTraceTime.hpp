#ifndef SHARE_VM_GC_IMPLEMENTATION_SHARED_GCTRACETIME_HPP
#define SHARE_VM_GC_IMPLEMENTATION_SHARED_GCTRACETIME_HPP

#include "prims/jni_md.h"
#include "utilities/ticks.hpp"

class GCTimer;

class GCTraceTime {
  const char* _title;
  bool _doit;
  bool _print_cr;
  GCTimer* _timer;
  Ticks _start_counter;

 public:
  GCTraceTime(const char* title, bool doit, bool print_cr, GCTimer* timer);
  ~GCTraceTime();
};

#endif // SHARE_VM_GC_IMPLEMENTATION_SHARED_GCTRACETIME_HPP
