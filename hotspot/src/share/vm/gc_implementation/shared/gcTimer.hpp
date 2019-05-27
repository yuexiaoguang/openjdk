#ifndef SHARE_VM_GC_IMPLEMENTATION_SHARED_GCTIMER_HPP
#define SHARE_VM_GC_IMPLEMENTATION_SHARED_GCTIMER_HPP

#include "memory/allocation.hpp"
#include "prims/jni_md.h"
#include "utilities/macros.hpp"
#include "utilities/ticks.hpp"

class ConcurrentPhase;
class GCPhase;
class PausePhase;

template <class E> class GrowableArray;

class PhaseVisitor {
 public:
  virtual void visit(GCPhase* phase) = 0;
  virtual void visit(PausePhase* phase) { visit((GCPhase*)phase); }
  virtual void visit(ConcurrentPhase* phase) { visit((GCPhase*)phase); }
};

class GCPhase {
  const char* _name;
  int _level;
  Ticks _start;
  Ticks _end;

 public:
  void set_name(const char* name) { _name = name; }
  const char* name() const { return _name; }

  int level() const { return _level; }
  void set_level(int level) { _level = level; }

  const Ticks start() const { return _start; }
  void set_start(const Ticks& time) { _start = time; }

  const Ticks end() const { return _end; }
  void set_end(const Ticks& time) { _end = time; }

  virtual void accept(PhaseVisitor* visitor) = 0;
};

class PausePhase : public GCPhase {
 public:
  void accept(PhaseVisitor* visitor) {
    visitor->visit(this);
  }
};

class ConcurrentPhase : public GCPhase {
  void accept(PhaseVisitor* visitor) {
    visitor->visit(this);
  }
};

class PhasesStack {
 public:
  // FIXME: Temporary set to 5 (used to be 4), since Reference processing needs it.
  static const int PHASE_LEVELS = 5;

 private:
  int _phase_indices[PHASE_LEVELS];
  int _next_phase_level;

 public:
  PhasesStack() { clear(); }
  void clear();

  void push(int phase_index);
  int pop();
  int count() const;
};

class TimePartitions {
  static const int INITIAL_CAPACITY = 10;

  // Currently we only support pause phases.
  GrowableArray<PausePhase>* _phases;
  PhasesStack _active_phases;

  Tickspan _sum_of_pauses;
  Tickspan _longest_pause;

 public:
  TimePartitions();
  ~TimePartitions();
  void clear();

  void report_gc_phase_start(const char* name, const Ticks& time);
  void report_gc_phase_end(const Ticks& time);

  int num_phases() const;
  GCPhase* phase_at(int index) const;

  const Tickspan sum_of_pauses() const { return _sum_of_pauses; }
  const Tickspan longest_pause() const { return _longest_pause; }

  bool has_active_phases();
 private:
  void update_statistics(GCPhase* phase);
};

class PhasesIterator {
 public:
  virtual bool has_next() = 0;
  virtual GCPhase* next() = 0;
};

class GCTimer : public ResourceObj {
  NOT_PRODUCT(friend class GCTimerTest;)
 protected:
  Ticks _gc_start;
  Ticks _gc_end;
  TimePartitions _time_partitions;

 public:
  virtual void register_gc_start(const Ticks& time = Ticks::now());
  virtual void register_gc_end(const Ticks& time = Ticks::now());

  void register_gc_phase_start(const char* name, const Ticks& time);
  void register_gc_phase_end(const Ticks& time);

  const Ticks gc_start() const { return _gc_start; }
  const Ticks gc_end() const { return _gc_end; }

  TimePartitions* time_partitions() { return &_time_partitions; }

 protected:
  void register_gc_pause_start(const char* name, const Ticks& time = Ticks::now());
  void register_gc_pause_end(const Ticks& time = Ticks::now());
};

class STWGCTimer : public GCTimer {
 public:
  virtual void register_gc_start(const Ticks& time = Ticks::now());
  virtual void register_gc_end(const Ticks& time = Ticks::now());
};

class ConcurrentGCTimer : public GCTimer {
 public:
  void register_gc_pause_start(const char* name);
  void register_gc_pause_end();
};

class TimePartitionPhasesIterator {
  TimePartitions* _time_partitions;
  int _next;

 public:
  TimePartitionPhasesIterator(TimePartitions* time_partitions) : _time_partitions(time_partitions), _next(0) { }

  virtual bool has_next();
  virtual GCPhase* next();
};


/////////////// Unit tests ///////////////

#ifndef PRODUCT

class GCTimerAllTest {
 public:
  static void all();
};

#endif

#endif // SHARE_VM_GC_IMPLEMENTATION_SHARED_GCTIMER_HPP
