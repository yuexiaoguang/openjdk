#ifndef SHARE_VM_RUNTIME_MEMPROFILER_HPP
#define SHARE_VM_RUNTIME_MEMPROFILER_HPP

// Prints periodic memory usage trace of HotSpot VM

class MemProfilerTask;

class MemProfiler : AllStatic {
 friend class MemProfilerTask;
 private:
  static MemProfilerTask* _task;
  static FILE* _log_fp;
  // Do trace (callback from MemProfilerTask and from disengage)
  static void do_trace()      PRODUCT_RETURN;
 public:
  // Start/stop the profiler
  static void engage()        PRODUCT_RETURN;
  static void disengage()     PRODUCT_RETURN;
  // Tester
  static bool is_active()     PRODUCT_RETURN0;
};

#endif // SHARE_VM_RUNTIME_MEMPROFILER_HPP
