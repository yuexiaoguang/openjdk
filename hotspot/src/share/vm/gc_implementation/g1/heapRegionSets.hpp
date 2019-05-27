#ifndef SHARE_VM_GC_IMPLEMENTATION_G1_HEAPREGIONSETS_HPP
#define SHARE_VM_GC_IMPLEMENTATION_G1_HEAPREGIONSETS_HPP

#include "gc_implementation/g1/heapRegionSet.inline.hpp"

//////////////////// FreeRegionList ////////////////////

class FreeRegionList : public HeapRegionLinkedList {
protected:
  virtual const char* verify_region_extra(HeapRegion* hr);

  virtual bool regions_humongous() { return false; }
  virtual bool regions_empty()     { return true;  }

public:
  FreeRegionList(const char* name) : HeapRegionLinkedList(name) { }
};

//////////////////// MasterFreeRegionList ////////////////////

class MasterFreeRegionList : public FreeRegionList {
protected:
  virtual const char* verify_region_extra(HeapRegion* hr);
  virtual bool check_mt_safety();

public:
  MasterFreeRegionList(const char* name) : FreeRegionList(name) { }
};

//////////////////// SecondaryFreeRegionList ////////////////////

class SecondaryFreeRegionList : public FreeRegionList {
protected:
  virtual bool check_mt_safety();

public:
  SecondaryFreeRegionList(const char* name) : FreeRegionList(name) { }
};

//////////////////// OldRegionSet ////////////////////

class OldRegionSet : public HeapRegionSet {
protected:
  virtual const char* verify_region_extra(HeapRegion* hr);

  virtual bool regions_humongous() { return false; }
  virtual bool regions_empty()     { return false; }

public:
  OldRegionSet(const char* name) : HeapRegionSet(name) { }
};

//////////////////// MasterOldRegionSet ////////////////////

class MasterOldRegionSet : public OldRegionSet {
private:
protected:
  virtual bool check_mt_safety();

public:
  MasterOldRegionSet(const char* name) : OldRegionSet(name) { }
};

//////////////////// HumongousRegionSet ////////////////////

class HumongousRegionSet : public HeapRegionSet {
protected:
  virtual const char* verify_region_extra(HeapRegion* hr);

  virtual bool regions_humongous() { return true;  }
  virtual bool regions_empty()     { return false; }

public:
  HumongousRegionSet(const char* name) : HeapRegionSet(name) { }
};

//////////////////// MasterHumongousRegionSet ////////////////////

class MasterHumongousRegionSet : public HumongousRegionSet {
protected:
  virtual bool check_mt_safety();

public:
  MasterHumongousRegionSet(const char* name) : HumongousRegionSet(name) { }
};

#endif // SHARE_VM_GC_IMPLEMENTATION_G1_HEAPREGIONSETS_HPP
