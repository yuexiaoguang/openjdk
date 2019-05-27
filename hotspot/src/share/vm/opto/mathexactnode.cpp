#include "precompiled.hpp"
#include "memory/allocation.inline.hpp"
#include "opto/addnode.hpp"
#include "opto/cfgnode.hpp"
#include "opto/machnode.hpp"
#include "opto/matcher.hpp"
#include "opto/mathexactnode.hpp"
#include "opto/subnode.hpp"

MathExactNode::MathExactNode(Node* ctrl, Node* in1) : MultiNode(2) {
  init_class_id(Class_MathExact);
  init_req(0, ctrl);
  init_req(1, in1);
}

MathExactNode::MathExactNode(Node* ctrl, Node* in1, Node* in2) : MultiNode(3) {
  init_class_id(Class_MathExact);
  init_req(0, ctrl);
  init_req(1, in1);
  init_req(2, in2);
}

BoolNode* MathExactNode::bool_node() const {
  Node* flags = flags_node();
  BoolNode* boolnode = flags->unique_out()->as_Bool();
  assert(boolnode != NULL, "must have BoolNode");
  return boolnode;
}

IfNode* MathExactNode::if_node() const {
  BoolNode* boolnode = bool_node();
  IfNode* ifnode = boolnode->unique_out()->as_If();
  assert(ifnode != NULL, "must have IfNode");
  return ifnode;
}

Node* MathExactNode::control_node() const {
  IfNode* ifnode = if_node();
  return ifnode->in(0);
}

Node* MathExactNode::non_throwing_branch() const {
  IfNode* ifnode = if_node();
  if (bool_node()->_test._test == BoolTest::overflow) {
    return ifnode->proj_out(0);
  }
  return ifnode->proj_out(1);
}

// If the MathExactNode won't overflow we have to replace the
// FlagsProjNode and ProjNode that is generated by the MathExactNode
Node* MathExactNode::no_overflow(PhaseGVN* phase, Node* new_result) {
  PhaseIterGVN* igvn = phase->is_IterGVN();
  if (igvn) {
    ProjNode* result = result_node();
    ProjNode* flags = flags_node();

    if (result != NULL) {
      igvn->replace_node(result, new_result);
    }

    if (flags != NULL) {
      BoolNode* boolnode = bool_node();
      switch (boolnode->_test._test) {
        case BoolTest::overflow:
          // if the check is for overflow - never taken
          igvn->replace_node(boolnode, phase->intcon(0));
          break;
        case BoolTest::no_overflow:
          // if the check is for no overflow - always taken
          igvn->replace_node(boolnode, phase->intcon(1));
          break;
        default:
          fatal("Unexpected value of BoolTest");
          break;
      }
      flags->del_req(0);
    }
  }
  return new_result;
}

Node* MathExactINode::match(const ProjNode* proj, const Matcher* m) {
  uint ideal_reg = proj->ideal_reg();
  RegMask rm;
  if (proj->_con == result_proj_node) {
    rm = m->mathExactI_result_proj_mask();
  } else {
    assert(proj->_con == flags_proj_node, "must be result or flags");
    assert(ideal_reg == Op_RegFlags, "sanity");
    rm = m->mathExactI_flags_proj_mask();
  }
  return new (m->C) MachProjNode(this, proj->_con, rm, ideal_reg);
}

Node* MathExactLNode::match(const ProjNode* proj, const Matcher* m) {
  uint ideal_reg = proj->ideal_reg();
  RegMask rm;
  if (proj->_con == result_proj_node) {
    rm = m->mathExactL_result_proj_mask();
  } else {
    assert(proj->_con == flags_proj_node, "must be result or flags");
    assert(ideal_reg == Op_RegFlags, "sanity");
    rm = m->mathExactI_flags_proj_mask();
  }
  return new (m->C) MachProjNode(this, proj->_con, rm, ideal_reg);
}

Node* AddExactINode::Ideal(PhaseGVN* phase, bool can_reshape) {
  Node* arg1 = in(1);
  Node* arg2 = in(2);

  const Type* type1 = phase->type(arg1);
  const Type* type2 = phase->type(arg2);

  if (type1 != Type::TOP && type1->singleton() &&
      type2 != Type::TOP && type2->singleton()) {
    jint val1 = arg1->get_int();
    jint val2 = arg2->get_int();
    jint result = val1 + val2;
    // Hacker's Delight 2-12 Overflow if both arguments have the opposite sign of the result
    if ( (((val1 ^ result) & (val2 ^ result)) >= 0)) {
      Node* con_result = ConINode::make(phase->C, result);
      return no_overflow(phase, con_result);
    }
    return NULL;
  }

  if (type1 == TypeInt::ZERO || type2 == TypeInt::ZERO) { // (Add 0 x) == x
    Node* add_result = new (phase->C) AddINode(arg1, arg2);
    return no_overflow(phase, add_result);
  }

  if (type2->singleton()) {
    return NULL; // no change - keep constant on the right
  }

  if (type1->singleton()) {
    // Make it x + Constant - move constant to the right
    swap_edges(1, 2);
    return this;
  }

  if (arg2->is_Load()) {
    return NULL; // no change - keep load on the right
  }

  if (arg1->is_Load()) {
    // Make it x + Load - move load to the right
    swap_edges(1, 2);
    return this;
  }

  if (arg1->_idx > arg2->_idx) {
    // Sort the edges
    swap_edges(1, 2);
    return this;
  }

  return NULL;
}

Node* AddExactLNode::Ideal(PhaseGVN* phase, bool can_reshape) {
  Node* arg1 = in(1);
  Node* arg2 = in(2);

  const Type* type1 = phase->type(arg1);
  const Type* type2 = phase->type(arg2);

  if (type1 != Type::TOP && type1->singleton() &&
      type2 != Type::TOP && type2->singleton()) {
    jlong val1 = arg1->get_long();
    jlong val2 = arg2->get_long();
    jlong result = val1 + val2;
    // Hacker's Delight 2-12 Overflow if both arguments have the opposite sign of the result
    if ( (((val1 ^ result) & (val2 ^ result)) >= 0)) {
      Node* con_result = ConLNode::make(phase->C, result);
      return no_overflow(phase, con_result);
    }
    return NULL;
  }

  if (type1 == TypeLong::ZERO || type2 == TypeLong::ZERO) { // (Add 0 x) == x
    Node* add_result = new (phase->C) AddLNode(arg1, arg2);
    return no_overflow(phase, add_result);
  }

  if (type2->singleton()) {
    return NULL; // no change - keep constant on the right
  }

  if (type1->singleton()) {
    // Make it x + Constant - move constant to the right
    swap_edges(1, 2);
    return this;
  }

  if (arg2->is_Load()) {
    return NULL; // no change - keep load on the right
  }

  if (arg1->is_Load()) {
    // Make it x + Load - move load to the right
    swap_edges(1, 2);
    return this;
  }

  if (arg1->_idx > arg2->_idx) {
    // Sort the edges
    swap_edges(1, 2);
    return this;
  }

  return NULL;
}

Node* SubExactINode::Ideal(PhaseGVN* phase, bool can_reshape) {
  Node* arg1 = in(1);
  Node* arg2 = in(2);

  const Type* type1 = phase->type(arg1);
  const Type* type2 = phase->type(arg2);

  if (type1 != Type::TOP && type1->singleton() &&
      type2 != Type::TOP && type2->singleton()) {
    jint val1 = arg1->get_int();
    jint val2 = arg2->get_int();
    jint result = val1 - val2;

    // Hacker's Delight 2-12 Overflow iff the arguments have different signs and
    // the sign of the result is different than the sign of arg1
    if (((val1 ^ val2) & (val1 ^ result)) >= 0) {
      Node* con_result = ConINode::make(phase->C, result);
      return no_overflow(phase, con_result);
    }
    return NULL;
  }

  if (type1 == TypeInt::ZERO || type2 == TypeInt::ZERO) {
    // Sub with zero is the same as add with zero
    Node* add_result = new (phase->C) AddINode(arg1, arg2);
    return no_overflow(phase, add_result);
  }

  return NULL;
}

Node* SubExactLNode::Ideal(PhaseGVN* phase, bool can_reshape) {
  Node* arg1 = in(1);
  Node* arg2 = in(2);

  const Type* type1 = phase->type(arg1);
  const Type* type2 = phase->type(arg2);

  if (type1 != Type::TOP && type1->singleton() &&
      type2 != Type::TOP && type2->singleton()) {
    jlong val1 = arg1->get_long();
    jlong val2 = arg2->get_long();
    jlong result = val1 - val2;

    // Hacker's Delight 2-12 Overflow iff the arguments have different signs and
    // the sign of the result is different than the sign of arg1
    if (((val1 ^ val2) & (val1 ^ result)) >= 0) {
      Node* con_result = ConLNode::make(phase->C, result);
      return no_overflow(phase, con_result);
    }
    return NULL;
  }

  if (type1 == TypeLong::ZERO || type2 == TypeLong::ZERO) {
    // Sub with zero is the same as add with zero
    Node* add_result = new (phase->C) AddLNode(arg1, arg2);
    return no_overflow(phase, add_result);
  }

  return NULL;
}

Node* NegExactINode::Ideal(PhaseGVN* phase, bool can_reshape) {
  Node *arg = in(1);

  const Type* type = phase->type(arg);
  if (type != Type::TOP && type->singleton()) {
    jint value = arg->get_int();
    if (value != min_jint) {
      Node* neg_result = ConINode::make(phase->C, -value);
      return no_overflow(phase, neg_result);
    }
  }
  return NULL;
}

Node* NegExactLNode::Ideal(PhaseGVN* phase, bool can_reshape) {
  Node *arg = in(1);

  const Type* type = phase->type(arg);
  if (type != Type::TOP && type->singleton()) {
    jlong value = arg->get_long();
    if (value != min_jlong) {
      Node* neg_result = ConLNode::make(phase->C, -value);
      return no_overflow(phase, neg_result);
    }
  }
  return NULL;
}

Node* MulExactINode::Ideal(PhaseGVN* phase, bool can_reshape) {
  Node* arg1 = in(1);
  Node* arg2 = in(2);

  const Type* type1 = phase->type(arg1);
  const Type* type2 = phase->type(arg2);

  if (type1 != Type::TOP && type1->singleton() &&
      type2 != Type::TOP && type2->singleton()) {
    jint val1 = arg1->get_int();
    jint val2 = arg2->get_int();
    jlong result = (jlong) val1 * (jlong) val2;
    if ((jint) result == result) {
      // no overflow
      Node* mul_result = ConINode::make(phase->C, result);
      return no_overflow(phase, mul_result);
    }
  }

  if (type1 == TypeInt::ZERO || type2 == TypeInt::ZERO) {
    return no_overflow(phase, ConINode::make(phase->C, 0));
  }

  if (type1 == TypeInt::ONE) {
    Node* mul_result = new (phase->C) AddINode(arg2, phase->intcon(0));
    return no_overflow(phase, mul_result);
  }
  if (type2 == TypeInt::ONE) {
    Node* mul_result = new (phase->C) AddINode(arg1, phase->intcon(0));
    return no_overflow(phase, mul_result);
  }

  if (type1 == TypeInt::MINUS_1) {
    return new (phase->C) NegExactINode(NULL, arg2);
  }

  if (type2 == TypeInt::MINUS_1) {
    return new (phase->C) NegExactINode(NULL, arg1);
  }

  return NULL;
}

Node* MulExactLNode::Ideal(PhaseGVN* phase, bool can_reshape) {
  Node* arg1 = in(1);
  Node* arg2 = in(2);

  const Type* type1 = phase->type(arg1);
  const Type* type2 = phase->type(arg2);

  if (type1 != Type::TOP && type1->singleton() &&
      type2 != Type::TOP && type2->singleton()) {
    jlong val1 = arg1->get_long();
    jlong val2 = arg2->get_long();

    jlong result = val1 * val2;
    jlong ax = (val1 < 0 ? -val1 : val1);
    jlong ay = (val2 < 0 ? -val2 : val2);

    bool overflow = false;
    if ((ax | ay) & CONST64(0xFFFFFFFF00000000)) {
      // potential overflow if any bit in upper 32 bits are set
      if ((val1 == min_jlong && val2 == -1) || (val2 == min_jlong && val1 == -1)) {
        // -1 * Long.MIN_VALUE will overflow
        overflow = true;
      } else if (val2 != 0 && (result / val2 != val1)) {
        overflow = true;
      }
    }

    if (!overflow) {
      Node* mul_result = ConLNode::make(phase->C, result);
      return no_overflow(phase, mul_result);
    }
  }

  if (type1 == TypeLong::ZERO || type2 == TypeLong::ZERO) {
    return no_overflow(phase, ConLNode::make(phase->C, 0));
  }

  if (type1 == TypeLong::ONE) {
    Node* mul_result = new (phase->C) AddLNode(arg2, phase->longcon(0));
    return no_overflow(phase, mul_result);
  }
  if (type2 == TypeLong::ONE) {
    Node* mul_result = new (phase->C) AddLNode(arg1, phase->longcon(0));
    return no_overflow(phase, mul_result);
  }

  if (type1 == TypeLong::MINUS_1) {
    return new (phase->C) NegExactLNode(NULL, arg2);
  }

  if (type2 == TypeLong::MINUS_1) {
    return new (phase->C) NegExactLNode(NULL, arg1);
  }

  return NULL;
}

