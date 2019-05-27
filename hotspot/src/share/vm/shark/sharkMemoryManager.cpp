#include "precompiled.hpp"
#include "shark/llvmHeaders.hpp"
#include "shark/sharkEntry.hpp"
#include "shark/sharkMemoryManager.hpp"

using namespace llvm;

void SharkMemoryManager::AllocateGOT() {
  mm()->AllocateGOT();
}

unsigned char* SharkMemoryManager::getGOTBase() const {
  return mm()->getGOTBase();
}

unsigned char* SharkMemoryManager::allocateStub(const GlobalValue* F,
                                                unsigned StubSize,
                                                unsigned Alignment) {
  return mm()->allocateStub(F, StubSize, Alignment);
}

unsigned char* SharkMemoryManager::startFunctionBody(const Function* F,
                                                     uintptr_t& ActualSize) {
  return mm()->startFunctionBody(F, ActualSize);
}

void SharkMemoryManager::endFunctionBody(const Function* F,
                                         unsigned char* FunctionStart,
                                         unsigned char* FunctionEnd) {
  mm()->endFunctionBody(F, FunctionStart, FunctionEnd);

  SharkEntry *entry = get_entry_for_function(F);
  if (entry != NULL)
    entry->set_code_limit(FunctionEnd);
}

unsigned char* SharkMemoryManager::startExceptionTable(const Function* F,
                                                       uintptr_t& ActualSize) {
  return mm()->startExceptionTable(F, ActualSize);
}

void SharkMemoryManager::endExceptionTable(const Function* F,
                                           unsigned char* TableStart,
                                           unsigned char* TableEnd,
                                           unsigned char* FrameRegister) {
  mm()->endExceptionTable(F, TableStart, TableEnd, FrameRegister);
}

void SharkMemoryManager::setMemoryWritable() {
  mm()->setMemoryWritable();
}

void SharkMemoryManager::setMemoryExecutable() {
  mm()->setMemoryExecutable();
}

void SharkMemoryManager::deallocateExceptionTable(void *ptr) {
  mm()->deallocateExceptionTable(ptr);
}

void SharkMemoryManager::deallocateFunctionBody(void *ptr) {
  mm()->deallocateFunctionBody(ptr);
}

uint8_t* SharkMemoryManager::allocateGlobal(uintptr_t Size,
                                            unsigned int Alignment) {
  return mm()->allocateGlobal(Size, Alignment);
}

void* SharkMemoryManager::getPointerToNamedFunction(const std::string &Name, bool AbortOnFailure) {
  return mm()->getPointerToNamedFunction(Name, AbortOnFailure);
}

uint8_t* SharkMemoryManager::allocateCodeSection(uintptr_t Size, unsigned Alignment, unsigned SectionID) {
  return mm()->allocateCodeSection(Size, Alignment, SectionID);
}

uint8_t* SharkMemoryManager::allocateDataSection(uintptr_t Size, unsigned Alignment, unsigned SectionID) {
  return mm()->allocateDataSection(Size, Alignment, SectionID);
}

void SharkMemoryManager::setPoisonMemory(bool poison) {
  mm()->setPoisonMemory(poison);
}

unsigned char *SharkMemoryManager::allocateSpace(intptr_t Size,
                                                 unsigned int Alignment) {
  return mm()->allocateSpace(Size, Alignment);
}
