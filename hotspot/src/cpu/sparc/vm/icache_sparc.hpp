#ifndef CPU_SPARC_VM_ICACHE_SPARC_HPP
#define CPU_SPARC_VM_ICACHE_SPARC_HPP

// Interface for updating the instruction cache.  Whenever the VM modifies
// code, part of the processor instruction cache potentially has to be flushed.


class ICache : public AbstractICache {
 public:
  enum {
    stub_size      = 160, // Size of the icache flush stub in bytes
    line_size      = 8,   // flush instruction affects a dword
    log2_line_size = 3    // log2(line_size)
  };

  // Use default implementation
};

#endif // CPU_SPARC_VM_ICACHE_SPARC_HPP
