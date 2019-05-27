#ifndef SHARE_VM_RUNTIME_SIMPLETHRESHOLDPOLICY_INLINE_HPP
#define SHARE_VM_RUNTIME_SIMPLETHRESHOLDPOLICY_INLINE_HPP

template<CompLevel level>
bool SimpleThresholdPolicy::call_predicate_helper(int i, int b, double scale) {
  switch(level) {
  case CompLevel_none:
  case CompLevel_limited_profile:
    return (i > Tier3InvocationThreshold * scale) ||
           (i > Tier3MinInvocationThreshold * scale && i + b > Tier3CompileThreshold * scale);
  case CompLevel_full_profile:
   return (i > Tier4InvocationThreshold * scale) ||
          (i > Tier4MinInvocationThreshold * scale && i + b > Tier4CompileThreshold * scale);
  }
  return true;
}

template<CompLevel level>
bool SimpleThresholdPolicy::loop_predicate_helper(int i, int b, double scale) {
  switch(level) {
  case CompLevel_none:
  case CompLevel_limited_profile:
    return b > Tier3BackEdgeThreshold * scale;
  case CompLevel_full_profile:
    return b > Tier4BackEdgeThreshold * scale;
  }
  return true;
}

// Simple methods are as good being compiled with C1 as C2.
// Determine if a given method is such a case.
bool SimpleThresholdPolicy::is_trivial(Method* method) {
  if (method->is_accessor()) return true;
  if (method->code() != NULL) {
    MethodData* mdo = method->method_data();
    if (mdo != NULL && mdo->num_loops() == 0 &&
        (method->code_size() < 5  || (mdo->num_blocks() < 4) && (method->code_size() < 15))) {
      return !mdo->would_profile();
    }
  }
  return false;
}

#endif // SHARE_VM_RUNTIME_SIMPLETHRESHOLDPOLICY_INLINE_HPP
