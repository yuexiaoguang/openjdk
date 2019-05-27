#ifndef SHARE_VM_RUNTIME_VFRAME_HP_HPP
#define SHARE_VM_RUNTIME_VFRAME_HP_HPP

#include "runtime/vframe.hpp"

class compiledVFrame: public javaVFrame {
 public:
  // JVM state
  Method*                      method()             const;
  int                          bci()                const;
  bool                         should_reexecute()   const;
  StackValueCollection*        locals()             const;
  StackValueCollection*        expressions()        const;
  GrowableArray<MonitorInfo*>* monitors()           const;

  void set_locals(StackValueCollection* values) const;

  // Virtuals defined in vframe
  bool is_compiled_frame() const { return true; }
  vframe* sender() const;
  bool is_top() const;

  // Casting
  static compiledVFrame* cast(vframe* vf) {
    assert(vf == NULL || vf->is_compiled_frame(), "must be compiled frame");
    return (compiledVFrame*) vf;
  }

 public:
  // Constructors
  compiledVFrame(const frame* fr, const RegisterMap* reg_map, JavaThread* thread, nmethod* nm);

  // Update a local in a compiled frame. Update happens when deopt occurs
  void update_local(BasicType type, int index, jvalue value);

  // Returns the active nmethod
  nmethod*  code() const;

  // Returns the scopeDesc
  ScopeDesc* scope() const { return _scope; }

  // Returns SynchronizationEntryBCI or bci() (used for synchronization)
  int raw_bci() const;

 protected:
  ScopeDesc* _scope;


  //StackValue resolve(ScopeValue* sv) const;
  BasicLock* resolve_monitor_lock(Location location) const;
  StackValue *create_stack_value(ScopeValue *sv) const;

 private:
  compiledVFrame(const frame* fr, const RegisterMap* reg_map, JavaThread* thread, ScopeDesc* scope);

#ifndef PRODUCT
 public:
  void verify() const;
#endif
};

// In order to implement set_locals for compiled vframes we must
// store updated locals in a data structure that contains enough
// information to recognize equality with a vframe and to store
// any updated locals.

class jvmtiDeferredLocalVariable;
class jvmtiDeferredLocalVariableSet : public CHeapObj<mtCompiler> {
private:

  Method* _method;
  int       _bci;
  intptr_t* _id;
  GrowableArray<jvmtiDeferredLocalVariable*>* _locals;

 public:
  // JVM state
  Method*                           method()         const  { return _method; }
  int                               bci()            const  { return _bci; }
  intptr_t*                         id()             const  { return _id; }
  GrowableArray<jvmtiDeferredLocalVariable*>* locals()         const  { return _locals; }
  void                              set_local_at(int idx, BasicType typ, jvalue val);

  // Does the vframe match this jvmtiDeferredLocalVariableSet
  bool                              matches(vframe* vf);
  // GC
  void                              oops_do(OopClosure* f);

  // constructor
  jvmtiDeferredLocalVariableSet(Method* method, int bci, intptr_t* id);

  // destructor
  ~jvmtiDeferredLocalVariableSet();


};

class jvmtiDeferredLocalVariable : public CHeapObj<mtCompiler> {
  public:

    jvmtiDeferredLocalVariable(int index, BasicType type, jvalue value);

    BasicType type(void)                   { return _type; }
    int index(void)                        { return _index; }
    jvalue value(void)                     { return _value; }
    // Only mutator is for value as only it can change
    void set_value(jvalue value)           { _value = value; }
    // For gc
    oop* oop_addr(void)                    { return (oop*) &_value.l; }

  private:

    BasicType         _type;
    jvalue            _value;
    int               _index;

};

#endif // SHARE_VM_RUNTIME_VFRAME_HP_HPP
