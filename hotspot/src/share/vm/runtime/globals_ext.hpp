#ifndef SHARE_VM_RUNTIME_GLOBALS_EXT_HPP
#define SHARE_VM_RUNTIME_GLOBALS_EXT_HPP

// globals_extension.hpp extension

// Additional CommandLineFlags enum values
#define COMMANDLINEFLAG_EXT

// Additional CommandLineFlagsWithType enum values
#define COMMANDLINEFLAGWITHTYPE_EXT


// globals.cpp extension

// Additional flag definitions
#define MATERIALIZE_FLAGS_EXT

// Additional flag descriptors: see flagTable definition
#define FLAGTABLE_EXT


// Default method implementations

inline bool Flag::is_unlocker_ext() const {
  return false;
}

inline bool Flag::is_unlocked_ext() const {
  return true;
}

inline bool Flag::is_writeable_ext() const {
  return false;
}

inline bool Flag::is_external_ext() const {
  return false;
}

inline void Flag::get_locked_message_ext(char* buf, int buflen) const {
  assert(buf != NULL, "Buffer cannot be NULL");
  buf[0] = '\0';
}

#endif // SHARE_VM_RUNTIME_GLOBALS_EXT_HPP
