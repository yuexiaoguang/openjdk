
#include "precompiled.hpp"
#include "mutex_solaris.inline.hpp"
#include "runtime/interfaceSupport.hpp"
#include "runtime/mutex.hpp"
#include "runtime/thread.inline.hpp"
#include "utilities/events.hpp"

// Solaris-specific include, therefore not in includeDB_*
# include "os_share_solaris.hpp"

// put OS-includes here
# include <signal.h>
