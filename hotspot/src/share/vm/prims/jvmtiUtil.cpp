#include "precompiled.hpp"
#include "prims/jvmtiUtil.hpp"
#include "runtime/handles.hpp"
#include "runtime/handles.inline.hpp"
#include "runtime/interfaceSupport.hpp"
#include "runtime/vm_operations.hpp"
#include "utilities/exceptions.hpp"

//
// class JvmtiUtil
//

ResourceArea* JvmtiUtil::_single_threaded_resource_area = NULL;

ResourceArea* JvmtiUtil::single_threaded_resource_area() {
  if (_single_threaded_resource_area == NULL) {
    // lazily create the single threaded resource area
    // pick a size which is not a standard since the pools don't exist yet
    _single_threaded_resource_area = new (mtInternal) ResourceArea(Chunk::non_pool_size);
  }
  return _single_threaded_resource_area;
}
