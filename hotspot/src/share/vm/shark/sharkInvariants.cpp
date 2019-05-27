#include "precompiled.hpp"
#include "shark/sharkInvariants.hpp"

int SharkTargetInvariants::count_monitors() {
  int result = 0;
  if (is_synchronized() || target()->has_monitor_bytecodes()) {
    for (int i = 0; i < flow()->block_count(); i++) {
      result = MAX2(result, flow()->pre_order_at(i)->monitor_count());
    }
  }
  return result;
}
