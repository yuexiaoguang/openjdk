
// no precompiled headers
#include "runtime/atomic.hpp"
#include "runtime/handles.inline.hpp"
#include "runtime/mutexLocker.hpp"
#include "runtime/os.hpp"
#include "runtime/osThread.hpp"
#include "runtime/safepoint.hpp"
#include "runtime/vmThread.hpp"

void OSThread::pd_initialize() {
  set_thread_handle(NULL);
  set_thread_id(NULL);
  set_interrupt_event(NULL);
}

// TODO: this is not well encapsulated; creation and deletion of the
// interrupt_event are done in os_win32.cpp, create_thread and
// free_thread. Should follow pattern of Linux/Solaris code here.
void OSThread::pd_destroy() {
}
