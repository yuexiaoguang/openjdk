#ifndef SHARE_VM_CI_CIREPLAY_HPP
#define SHARE_VM_CI_CIREPLAY_HPP

#include "ci/ciMethod.hpp"

// ciReplay

class ciReplay {
  CI_PACKAGE_ACCESS

#ifndef PRODUCT
 private:
  static int replay_impl(TRAPS);

 public:
  static void replay(TRAPS);

  // These are used by the CI to fill in the cached data from the
  // replay file when replaying compiles.
  static void initialize(ciMethodData* method);
  static void initialize(ciMethod* method);

  static bool is_loaded(Method* method);
  static bool is_loaded(Klass* klass);

  static bool should_not_inline(ciMethod* method);

#endif
};

#endif // SHARE_VM_CI_CIREPLAY_HPP
