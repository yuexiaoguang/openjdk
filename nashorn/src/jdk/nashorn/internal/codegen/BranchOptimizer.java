package jdk.nashorn.internal.codegen;

import static jdk.nashorn.internal.codegen.Condition.EQ;
import static jdk.nashorn.internal.codegen.Condition.GE;
import static jdk.nashorn.internal.codegen.Condition.GT;
import static jdk.nashorn.internal.codegen.Condition.LE;
import static jdk.nashorn.internal.codegen.Condition.LT;
import static jdk.nashorn.internal.codegen.Condition.NE;

import jdk.nashorn.internal.codegen.types.Type;
import jdk.nashorn.internal.ir.BinaryNode;
import jdk.nashorn.internal.ir.Expression;
import jdk.nashorn.internal.ir.TernaryNode;
import jdk.nashorn.internal.ir.UnaryNode;

/**
 * Branch optimizer for CodeGenerator. Given a jump condition this helper
 * class attempts to simplify the control flow
 */
final class BranchOptimizer {

    private final CodeGenerator codegen;
    private final MethodEmitter method;

    BranchOptimizer(final CodeGenerator codegen, final MethodEmitter method) {
        this.codegen = codegen;
        this.method  = method;
    }

    void execute(final Expression node, final Label label, final boolean state) {
        branchOptimizer(node, label, state);
    }

    private void branchOptimizer(final UnaryNode unaryNode, final Label label, final boolean state) {
        final Expression rhs = unaryNode.rhs();

        switch (unaryNode.tokenType()) {
        case NOT:
            branchOptimizer(rhs, label, !state);
            return;
        default:
            if (unaryNode.getType().isBoolean()) {
                branchOptimizer(rhs, label, state);
                return;
            }
            break;
        }

        // convert to boolean
        codegen.load(unaryNode, Type.BOOLEAN);
        if (state) {
            method.ifne(label);
        } else {
            method.ifeq(label);
        }
    }

    private void branchOptimizer(final BinaryNode binaryNode, final Label label, final boolean state) {
        final Expression lhs = binaryNode.lhs();
        final Expression rhs = binaryNode.rhs();

        switch (binaryNode.tokenType()) {
        case AND:
            if (state) {
                final Label skip = new Label("skip");
                branchOptimizer(lhs, skip, false);
                branchOptimizer(rhs, label, true);
                method.label(skip);
            } else {
                branchOptimizer(lhs, label, false);
                branchOptimizer(rhs, label, false);
            }
            return;

        case OR:
            if (state) {
                branchOptimizer(lhs, label, true);
                branchOptimizer(rhs, label, true);
            } else {
                final Label skip = new Label("skip");
                branchOptimizer(lhs, skip, true);
                branchOptimizer(rhs, label, false);
                method.label(skip);
            }
            return;

        case EQ:
        case EQ_STRICT:
            codegen.loadBinaryOperands(lhs, rhs, Type.widest(lhs.getType(), rhs.getType()));
            method.conditionalJump(state ? EQ : NE, true, label);
            return;

        case NE:
        case NE_STRICT:
            codegen.loadBinaryOperands(lhs, rhs, Type.widest(lhs.getType(), rhs.getType()));
            method.conditionalJump(state ? NE : EQ, true, label);
            return;

        case GE:
            codegen.loadBinaryOperands(lhs, rhs, Type.widest(lhs.getType(), rhs.getType()));
            method.conditionalJump(state ? GE : LT, !state, label);
            return;

        case GT:
            codegen.loadBinaryOperands(lhs, rhs, Type.widest(lhs.getType(), rhs.getType()));
            method.conditionalJump(state ? GT : LE, !state, label);
            return;

        case LE:
            codegen.loadBinaryOperands(lhs, rhs, Type.widest(lhs.getType(), rhs.getType()));
            method.conditionalJump(state ? LE : GT, state, label);
            return;

        case LT:
            codegen.loadBinaryOperands(lhs, rhs, Type.widest(lhs.getType(), rhs.getType()));
            method.conditionalJump(state ? LT : GE, state, label);
            return;

        default:
            break;
        }

        codegen.load(binaryNode, Type.BOOLEAN);
        if (state) {
            method.ifne(label);
        } else {
            method.ifeq(label);
        }
    }

    private void branchOptimizer(final Expression node, final Label label, final boolean state) {
        if (!(node instanceof TernaryNode)) {

            if (node instanceof BinaryNode) {
                branchOptimizer((BinaryNode)node, label, state);
                return;
            }

            if (node instanceof UnaryNode) {
                branchOptimizer((UnaryNode)node, label, state);
                return;
            }
        }

        codegen.load(node, Type.BOOLEAN);
        if (state) {
            method.ifne(label);
        } else {
            method.ifeq(label);
        }
    }
}
