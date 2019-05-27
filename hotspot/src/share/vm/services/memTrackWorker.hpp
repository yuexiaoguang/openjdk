#ifndef SHARE_VM_SERVICES_MEM_TRACK_WORKER_HPP
#define SHARE_VM_SERVICES_MEM_TRACK_WORKER_HPP

#include "memory/allocation.hpp"
#include "runtime/thread.hpp"
#include "services/memRecorder.hpp"

// Maximum MAX_GENERATIONS generation data can be tracked.
#define MAX_GENERATIONS  512

class GenerationData VALUE_OBJ_CLASS_SPEC {
 private:
  int           _number_of_classes;
  MemRecorder*  _recorder_list;

 public:
  GenerationData(): _number_of_classes(0), _recorder_list(NULL) { }

  inline int  number_of_classes() const { return _number_of_classes; }
  inline void set_number_of_classes(long num) { _number_of_classes = num; }

  inline MemRecorder* next_recorder() {
    if (_recorder_list == NULL) {
      return NULL;
    } else {
      MemRecorder* tmp = _recorder_list;
      _recorder_list = _recorder_list->next();
      return tmp;
    }
  }

  inline bool has_more_recorder() const {
    return (_recorder_list != NULL);
  }

  // add recorders to this generation
  void add_recorders(MemRecorder* head) {
    if (head != NULL) {
      if (_recorder_list == NULL) {
        _recorder_list = head;
      } else {
        MemRecorder* tmp = _recorder_list;
        for (; tmp->next() != NULL; tmp = tmp->next());
        tmp->set_next(head);
      }
    }
  }

  void reset();

  NOT_PRODUCT(MemRecorder* peek() const { return _recorder_list; })
};

class MemTrackWorker : public NamedThread {
 private:
  // circular buffer. This buffer contains generation data to be merged into global
  // snaphsot.
  // Each slot holds a generation
  GenerationData  _gen[MAX_GENERATIONS];
  int             _head, _tail; // head and tail pointers to above circular buffer

  bool            _has_error;

  MemSnapshot*    _snapshot;

 public:
  MemTrackWorker(MemSnapshot* snapshot);
  ~MemTrackWorker();
  _NOINLINE_ void* operator new(size_t size) throw();
  _NOINLINE_ void* operator new(size_t size, const std::nothrow_t& nothrow_constant) throw();

  void start();
  void run();

  inline bool has_error() const { return _has_error; }

  // task at synchronization point
  void at_sync_point(MemRecorder* pending_recorders, int number_of_classes);

  // for debugging purpose, they are not thread safe.
  NOT_PRODUCT(static int count_recorder(const MemRecorder* head);)
  NOT_PRODUCT(int count_pending_recorders() const;)

  NOT_PRODUCT(int _sync_point_count;)
  NOT_PRODUCT(int _merge_count;)
  NOT_PRODUCT(int _last_gen_in_use;)

  // how many generations are queued
  inline int generations_in_use() const {
    return (_tail >= _head ? (_tail - _head + 1) : (MAX_GENERATIONS - (_head - _tail) + 1));
  }
};

#endif // SHARE_VM_SERVICES_MEM_TRACK_WORKER_HPP
