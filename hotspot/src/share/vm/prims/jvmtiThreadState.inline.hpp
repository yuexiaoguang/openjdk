#ifndef SHARE_VM_PRIMS_JVMTITHREADSTATE_INLINE_HPP
#define SHARE_VM_PRIMS_JVMTITHREADSTATE_INLINE_HPP

#include "prims/jvmtiEnvThreadState.hpp"
#include "prims/jvmtiThreadState.hpp"

// JvmtiEnvThreadStateIterator implementation

inline JvmtiEnvThreadStateIterator::JvmtiEnvThreadStateIterator(JvmtiThreadState* thread_state) {
  state = thread_state;
  Thread::current()->entering_jvmti_env_iteration();
}

inline JvmtiEnvThreadStateIterator::~JvmtiEnvThreadStateIterator() {
  Thread::current()->leaving_jvmti_env_iteration();
}

inline JvmtiEnvThreadState* JvmtiEnvThreadStateIterator::first() {
  return state->head_env_thread_state();
}

inline JvmtiEnvThreadState* JvmtiEnvThreadStateIterator::next(JvmtiEnvThreadState* ets) {
  return ets->next();
}

// JvmtiThreadState implementation

JvmtiEnvThreadState* JvmtiThreadState::env_thread_state(JvmtiEnvBase *env) {
  JvmtiEnvThreadStateIterator it(this);
  for (JvmtiEnvThreadState* ets = it.first(); ets != NULL; ets = it.next(ets)) {
    if ((JvmtiEnvBase*)(ets->get_env()) == env) {
      return ets;
    }
  }
  return NULL;
}

JvmtiEnvThreadState* JvmtiThreadState::head_env_thread_state() {
  return _head_env_thread_state;
}

void JvmtiThreadState::set_head_env_thread_state(JvmtiEnvThreadState* ets) {
  _head_env_thread_state = ets;
}

#endif // SHARE_VM_PRIMS_JVMTITHREADSTATE_INLINE_HPP
