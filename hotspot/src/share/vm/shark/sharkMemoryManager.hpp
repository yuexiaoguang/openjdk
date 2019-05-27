#ifndef SHARE_VM_SHARK_SHARKMEMORYMANAGER_HPP
#define SHARE_VM_SHARK_SHARKMEMORYMANAGER_HPP

#include "shark/llvmHeaders.hpp"
#include "shark/sharkEntry.hpp"

// SharkMemoryManager wraps the LLVM JIT Memory Manager.  We could use
// this to run our own memory allocation policies, but for now all we
// use it for is figuring out where the resulting native code ended up.

class SharkMemoryManager : public llvm::JITMemoryManager {
 public:
  SharkMemoryManager()
    : _mm(llvm::JITMemoryManager::CreateDefaultMemManager()) {}

 private:
  llvm::JITMemoryManager* _mm;

 private:
  llvm::JITMemoryManager* mm() const {
    return _mm;
  }

 private:
  std::map<const llvm::Function*, SharkEntry*> _entry_map;

 public:
  void set_entry_for_function(const llvm::Function* function,
                              SharkEntry*           entry) {
    _entry_map[function] = entry;
  }
  SharkEntry* get_entry_for_function(const llvm::Function* function) {
    return _entry_map[function];
  }

 public:
  void AllocateGOT();
  unsigned char* getGOTBase() const;
  unsigned char* allocateStub(const llvm::GlobalValue* F,
                              unsigned StubSize,
                              unsigned Alignment);
  unsigned char* startFunctionBody(const llvm::Function* F,
                                   uintptr_t& ActualSize);
  void endFunctionBody(const llvm::Function* F,
                       unsigned char* FunctionStart,
                       unsigned char* FunctionEnd);
  unsigned char* startExceptionTable(const llvm::Function* F,
                                     uintptr_t& ActualSize);
  void endExceptionTable(const llvm::Function* F,
                         unsigned char* TableStart,
                         unsigned char* TableEnd,
                         unsigned char* FrameRegister);
  void *getPointerToNamedFunction(const std::string &Name, bool AbortOnFailure = true);
  uint8_t *allocateCodeSection(uintptr_t Size, unsigned Alignment, unsigned SectionID);
  uint8_t *allocateDataSection(uintptr_t Size, unsigned Alignment, unsigned SectionID);
  void setPoisonMemory(bool);
  uint8_t* allocateGlobal(uintptr_t, unsigned int);
  void setMemoryWritable();
  void setMemoryExecutable();
  void deallocateExceptionTable(void *ptr);
  void deallocateFunctionBody(void *ptr);
  unsigned char *allocateSpace(intptr_t Size,
                               unsigned int Alignment);
};

#endif // SHARE_VM_SHARK_SHARKMEMORYMANAGER_HPP
