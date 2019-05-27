#ifndef SHARE_VM_GC_IMPLEMENTATION_G1_G1LOG_HPP
#define SHARE_VM_GC_IMPLEMENTATION_G1_G1LOG_HPP

#include "memory/allocation.hpp"

class G1Log : public AllStatic {
  typedef enum {
    LevelNone,
    LevelFine,
    LevelFiner,
    LevelFinest
  } LogLevel;

  static LogLevel _level;

 public:
  inline static bool fine() {
    return _level >= LevelFine;
  }

  inline static bool finer() {
    return _level >= LevelFiner;
  }

  inline static bool finest() {
    return _level == LevelFinest;
  }

  static void init();
};

#endif // SHARE_VM_GC_IMPLEMENTATION_G1_G1LOG_HPP
