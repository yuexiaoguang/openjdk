#ifndef SHARE_VM_SHARK_SHARKINVARIANTS_HPP
#define SHARE_VM_SHARK_SHARKINVARIANTS_HPP

#include "ci/ciEnv.hpp"
#include "ci/ciInstanceKlass.hpp"
#include "ci/ciMethod.hpp"
#include "ci/ciTypeFlow.hpp"
#include "code/debugInfoRec.hpp"
#include "code/dependencies.hpp"
#include "memory/allocation.hpp"
#include "shark/llvmHeaders.hpp"
#include "shark/sharkBuilder.hpp"

// Base classes used to track various values through the compilation.
// SharkCompileInvariants is used to track values which remain the
// same for the top-level method and any inlined methods it may have
// (ie for the whole compilation).  SharkTargetInvariants is used to
// track values which differ between methods.

class SharkCompileInvariants : public ResourceObj {
 protected:
  SharkCompileInvariants(ciEnv* env, SharkBuilder* builder)
    : _env(env),
      _builder(builder),
      _thread(NULL) {}

  SharkCompileInvariants(const SharkCompileInvariants* parent)
    : _env(parent->_env),
      _builder(parent->_builder),
      _thread(parent->_thread) {}

 private:
  ciEnv*        _env;
  SharkBuilder* _builder;
  llvm::Value*  _thread;

  // Top-level broker for HotSpot's Compiler Interface.
  //
  // Its main purpose is to allow the various CI classes to access
  // oops in the VM without having to worry about safepointing.  In
  // addition to this it acts as a holder for various recorders and
  // memory allocators.
  //
  // Accessing this directly is kind of ugly, so it's private.  Add
  // new accessors below if you need something from it.
 protected:
  ciEnv* env() const {
    assert(_env != NULL, "env not available");
    return _env;
  }

  // The SharkBuilder that is used to build LLVM IR.
 protected:
  SharkBuilder* builder() const {
    return _builder;
  }

  // Pointer to this thread's JavaThread object.  This is not
  // available until a short way into SharkFunction creation
  // so a setter is required.  Assertions are used to enforce
  // invariance.
 protected:
  llvm::Value* thread() const {
    assert(_thread != NULL, "thread not available");
    return _thread;
  }
  void set_thread(llvm::Value* thread) {
    assert(_thread == NULL, "thread already set");
    _thread = thread;
  }

  // Objects that handle various aspects of the compilation.
 protected:
  DebugInformationRecorder* debug_info() const {
    return env()->debug_info();
  }
  SharkCodeBuffer* code_buffer() const {
    return builder()->code_buffer();
  }

 public:
  Dependencies* dependencies() const {
    return env()->dependencies();
  }

  // Commonly used classes
 protected:
  ciInstanceKlass* java_lang_Object_klass() const {
    return env()->Object_klass();
  }
  ciInstanceKlass* java_lang_Throwable_klass() const {
    return env()->Throwable_klass();
  }
};

class SharkTargetInvariants : public SharkCompileInvariants {
 protected:
  SharkTargetInvariants(ciEnv* env, SharkBuilder* builder, ciTypeFlow* flow)
    : SharkCompileInvariants(env, builder),
      _target(flow->method()),
      _flow(flow),
      _max_monitors(count_monitors()) {}

  SharkTargetInvariants(const SharkCompileInvariants* parent, ciMethod* target)
    : SharkCompileInvariants(parent),
      _target(target),
      _flow(NULL),
      _max_monitors(count_monitors()) {}

  SharkTargetInvariants(const SharkTargetInvariants* parent)
    : SharkCompileInvariants(parent),
      _target(parent->_target),
      _flow(parent->_flow),
      _max_monitors(parent->_max_monitors) {}

 private:
  int count_monitors();

 private:
  ciMethod*   _target;
  ciTypeFlow* _flow;
  int         _max_monitors;

  // The method being compiled.
 protected:
  ciMethod* target() const {
    return _target;
  }

  // Typeflow analysis of the method being compiled.
 protected:
  ciTypeFlow* flow() const {
    assert(_flow != NULL, "typeflow not available");
    return _flow;
  }

  // Properties of the method.
 protected:
  int max_locals() const {
    return target()->max_locals();
  }
  int max_stack() const {
    return target()->max_stack();
  }
  int max_monitors() const {
    return _max_monitors;
  }
  int arg_size() const {
    return target()->arg_size();
  }
  bool is_static() const {
    return target()->is_static();
  }
  bool is_synchronized() const {
    return target()->is_synchronized();
  }
};

#endif // SHARE_VM_SHARK_SHARKINVARIANTS_HPP
