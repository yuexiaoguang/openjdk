#ifndef SHARE_VM_GC_IMPLEMENTATION_G1_G1REMSETSUMMARY_HPP
#define SHARE_VM_GC_IMPLEMENTATION_G1_G1REMSETSUMMARY_HPP

#include "utilities/ostream.hpp"

class G1RemSet;

// A G1RemSetSummary manages statistical information about the G1RemSet

class G1RemSetSummary VALUE_OBJ_CLASS_SPEC {
private:
  friend class GetRSThreadVTimeClosure;

  G1RemSet* _remset;

  G1RemSet* remset() const {
    return _remset;
  }

  size_t _num_refined_cards;
  size_t _num_processed_buf_mutator;
  size_t _num_processed_buf_rs_threads;

  size_t _num_coarsenings;

  double* _rs_threads_vtimes;
  size_t _num_vtimes;

  double _sampling_thread_vtime;

  void set_rs_thread_vtime(uint thread, double value);
  void set_sampling_thread_vtime(double value) {
    _sampling_thread_vtime = value;
  }

  void free_and_null() {
    if (_rs_threads_vtimes) {
      FREE_C_HEAP_ARRAY(double, _rs_threads_vtimes, mtGC);
      _rs_threads_vtimes = NULL;
      _num_vtimes = 0;
    }
  }

  // update this summary with current data from various places
  void update();

public:
  G1RemSetSummary() : _remset(NULL), _num_refined_cards(0),
    _num_processed_buf_mutator(0), _num_processed_buf_rs_threads(0), _num_coarsenings(0),
    _rs_threads_vtimes(NULL), _num_vtimes(0), _sampling_thread_vtime(0.0f) {
  }

  ~G1RemSetSummary() {
    free_and_null();
  }

  // set the counters in this summary to the values of the others
  void set(G1RemSetSummary* other);
  // subtract all counters from the other summary, and set them in the current
  void subtract_from(G1RemSetSummary* other);

  // initialize and get the first sampling
  void initialize(G1RemSet* remset);

  void print_on(outputStream* out);

  double rs_thread_vtime(uint thread) const;

  double sampling_thread_vtime() const {
    return _sampling_thread_vtime;
  }

  size_t num_concurrent_refined_cards() const {
    return _num_refined_cards;
  }

  size_t num_processed_buf_mutator() const {
    return _num_processed_buf_mutator;
  }

  size_t num_processed_buf_rs_threads() const {
    return _num_processed_buf_rs_threads;
  }

  size_t num_processed_buf_total() const {
    return num_processed_buf_mutator() + num_processed_buf_rs_threads();
  }

  size_t num_coarsenings() const {
    return _num_coarsenings;
  }
};

#endif // SHARE_VM_GC_IMPLEMENTATION_G1_G1REMSETSUMMARY_HPP
