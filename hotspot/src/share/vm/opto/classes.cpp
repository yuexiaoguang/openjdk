#include "precompiled.hpp"
#include "opto/addnode.hpp"
#include "opto/callnode.hpp"
#include "opto/cfgnode.hpp"
#include "opto/connode.hpp"
#include "opto/divnode.hpp"
#include "opto/locknode.hpp"
#include "opto/loopnode.hpp"
#include "opto/machnode.hpp"
#include "opto/memnode.hpp"
#include "opto/mathexactnode.hpp"
#include "opto/mulnode.hpp"
#include "opto/multnode.hpp"
#include "opto/node.hpp"
#include "opto/rootnode.hpp"
#include "opto/subnode.hpp"
#include "opto/vectornode.hpp"

// ----------------------------------------------------------------------------
// Build a table of virtual functions to map from Nodes to dense integer
// opcode names.
int Node::Opcode() const { return Op_Node; }
#define macro(x) int x##Node::Opcode() const { return Op_##x; }
#include "classes.hpp"
#undef macro
