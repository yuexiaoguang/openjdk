#ifndef SHARE_VM_PRIMS_JVMTIRAWMONITOR_HPP
#define SHARE_VM_PRIMS_JVMTIRAWMONITOR_HPP

#include "runtime/objectMonitor.hpp"
#include "utilities/growableArray.hpp"

//
// class JvmtiRawMonitor
//
// Used by JVMTI methods: All RawMonitor methods (CreateRawMonitor, EnterRawMonitor, etc.)
//
// Wrapper for ObjectMonitor class that saves the Monitor's name
//

class JvmtiRawMonitor : public ObjectMonitor  {
private:
  int           _magic;
  char *        _name;
  // JVMTI_RM_MAGIC is set in contructor and unset in destructor.
  enum { JVMTI_RM_MAGIC = (int)(('T' << 24) | ('I' << 16) | ('R' << 8) | 'M') };

  int       SimpleEnter (Thread * Self) ;
  int       SimpleExit  (Thread * Self) ;
  int       SimpleWait  (Thread * Self, jlong millis) ;
  int       SimpleNotify (Thread * Self, bool All) ;

public:
  JvmtiRawMonitor(const char *name);
  ~JvmtiRawMonitor();
  int       raw_enter(TRAPS);
  int       raw_exit(TRAPS);
  int       raw_wait(jlong millis, bool interruptable, TRAPS);
  int       raw_notify(TRAPS);
  int       raw_notifyAll(TRAPS);
  int            magic()   { return _magic;  }
  const char *get_name()   { return _name; }
  bool        is_valid();
};

// Onload pending raw monitors
// Class is used to cache onload or onstart monitor enter
// which will transition into real monitor when
// VM is fully initialized.
class JvmtiPendingMonitors : public AllStatic {

private:
  static GrowableArray<JvmtiRawMonitor*> *_monitors; // Cache raw monitor enter

  inline static GrowableArray<JvmtiRawMonitor*>* monitors() { return _monitors; }

  static void dispose() {
    delete monitors();
  }

public:
  static void enter(JvmtiRawMonitor *monitor) {
    monitors()->append(monitor);
  }

  static int count() {
    return monitors()->length();
  }

  static void destroy(JvmtiRawMonitor *monitor) {
    while (monitors()->contains(monitor)) {
      monitors()->remove(monitor);
    }
  }

  // Return false if monitor is not found in the list.
  static bool exit(JvmtiRawMonitor *monitor) {
    if (monitors()->contains(monitor)) {
      monitors()->remove(monitor);
      return true;
    } else {
      return false;
    }
  }

  static void transition_raw_monitors();
};

#endif // SHARE_VM_PRIMS_JVMTIRAWMONITOR_HPP
