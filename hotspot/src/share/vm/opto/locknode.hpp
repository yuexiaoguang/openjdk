#ifndef SHARE_VM_OPTO_LOCKNODE_HPP
#define SHARE_VM_OPTO_LOCKNODE_HPP

#include "opto/node.hpp"
#include "opto/opcodes.hpp"
#include "opto/subnode.hpp"
#ifdef TARGET_ARCH_MODEL_x86_32
# include "adfiles/ad_x86_32.hpp"
#endif
#ifdef TARGET_ARCH_MODEL_x86_64
# include "adfiles/ad_x86_64.hpp"
#endif
#ifdef TARGET_ARCH_MODEL_sparc
# include "adfiles/ad_sparc.hpp"
#endif
#ifdef TARGET_ARCH_MODEL_zero
# include "adfiles/ad_zero.hpp"
#endif
#ifdef TARGET_ARCH_MODEL_arm
# include "adfiles/ad_arm.hpp"
#endif
#ifdef TARGET_ARCH_MODEL_ppc
# include "adfiles/ad_ppc.hpp"
#endif

//------------------------------BoxLockNode------------------------------------
class BoxLockNode : public Node {
  const int     _slot; // stack slot
  RegMask     _inmask; // OptoReg corresponding to stack slot
  bool _is_eliminated; // Associated locks were safely eliminated

public:
  BoxLockNode( int lock );
  virtual int Opcode() const;
  virtual void emit(CodeBuffer &cbuf, PhaseRegAlloc *ra_) const;
  virtual uint size(PhaseRegAlloc *ra_) const;
  virtual const RegMask &in_RegMask(uint) const;
  virtual const RegMask &out_RegMask() const;
  virtual uint size_of() const;
  virtual uint hash() const;
  virtual uint cmp( const Node &n ) const;
  virtual const class Type *bottom_type() const { return TypeRawPtr::BOTTOM; }
  virtual uint ideal_reg() const { return Op_RegP; }

  static OptoReg::Name reg(Node* box_node);
  static BoxLockNode* box_node(Node* box_node);
  static bool same_slot(Node* box1, Node* box2) {
    return box1->as_BoxLock()->_slot == box2->as_BoxLock()->_slot;
  }
  int stack_slot() const { return _slot; }

  bool is_eliminated() const { return _is_eliminated; }
  // mark lock as eliminated.
  void set_eliminated()      { _is_eliminated = true; }

  // Is BoxLock node used for one simple lock region?
  bool is_simple_lock_region(LockNode** unique_lock, Node* obj);

#ifndef PRODUCT
  virtual void format( PhaseRegAlloc *, outputStream *st ) const;
  virtual void dump_spec(outputStream *st) const { st->print("  Lock %d",_slot); }
#endif
};

//------------------------------FastLockNode-----------------------------------
class FastLockNode: public CmpNode {
private:
  BiasedLockingCounters* _counters;

public:
  FastLockNode(Node *ctrl, Node *oop, Node *box) : CmpNode(oop,box) {
    init_req(0,ctrl);
    init_class_id(Class_FastLock);
    _counters = NULL;
  }
  Node* obj_node() const { return in(1); }
  Node* box_node() const { return in(2); }
  void  set_box_node(Node* box) { set_req(2, box); }

  // FastLock and FastUnlockNode do not hash, we need one for each correspoding
  // LockNode/UnLockNode to avoid creating Phi's.
  virtual uint hash() const ;                  // { return NO_HASH; }
  virtual uint cmp( const Node &n ) const ;    // Always fail, except on self
  virtual int Opcode() const;
  virtual const Type *Value( PhaseTransform *phase ) const { return TypeInt::CC; }
  const Type *sub(const Type *t1, const Type *t2) const { return TypeInt::CC;}

  void create_lock_counter(JVMState* s);
  BiasedLockingCounters* counters() const { return _counters; }
};


//------------------------------FastUnlockNode---------------------------------
class FastUnlockNode: public CmpNode {
public:
  FastUnlockNode(Node *ctrl, Node *oop, Node *box) : CmpNode(oop,box) {
    init_req(0,ctrl);
    init_class_id(Class_FastUnlock);
  }
  Node* obj_node() const { return in(1); }
  Node* box_node() const { return in(2); }


  // FastLock and FastUnlockNode do not hash, we need one for each correspoding
  // LockNode/UnLockNode to avoid creating Phi's.
  virtual uint hash() const ;                  // { return NO_HASH; }
  virtual uint cmp( const Node &n ) const ;    // Always fail, except on self
  virtual int Opcode() const;
  virtual const Type *Value( PhaseTransform *phase ) const { return TypeInt::CC; }
  const Type *sub(const Type *t1, const Type *t2) const { return TypeInt::CC;}

};

#endif // SHARE_VM_OPTO_LOCKNODE_HPP
