package jdk.nashorn.internal.codegen;

import static jdk.nashorn.internal.codegen.CompilerConstants.CALLEE;
import static jdk.nashorn.internal.codegen.CompilerConstants.SCOPE;

import jdk.nashorn.internal.ir.BinaryNode;
import jdk.nashorn.internal.ir.Block;
import jdk.nashorn.internal.ir.Expression;
import jdk.nashorn.internal.ir.ExpressionStatement;
import jdk.nashorn.internal.ir.ForNode;
import jdk.nashorn.internal.ir.FunctionNode;
import jdk.nashorn.internal.ir.FunctionNode.CompilationState;
import jdk.nashorn.internal.ir.LexicalContext;
import jdk.nashorn.internal.ir.Node;
import jdk.nashorn.internal.ir.Symbol;
import jdk.nashorn.internal.ir.TemporarySymbols;
import jdk.nashorn.internal.ir.UnaryNode;
import jdk.nashorn.internal.ir.visitor.NodeOperatorVisitor;
import jdk.nashorn.internal.parser.Token;
import jdk.nashorn.internal.parser.TokenType;
import jdk.nashorn.internal.runtime.DebugLogger;

/**
 * Lower to more primitive operations. After lowering, an AST has symbols and
 * types. Lowering may also add specialized versions of methods to the script if
 * the optimizer is turned on.
 *
 * Any expression that requires temporary storage as part of computation will
 * also be detected here and give a temporary symbol
 *
 * For any op that we process in FinalizeTypes it is an absolute guarantee
 * that scope and slot information is correct. This enables e.g. AccessSpecialization
 * and frame optimizations
 */
final class FinalizeTypes extends NodeOperatorVisitor<LexicalContext> {

    private static final DebugLogger LOG = new DebugLogger("finalize");

    private final TemporarySymbols temporarySymbols;

    FinalizeTypes(final TemporarySymbols temporarySymbols) {
        super(new LexicalContext());
        this.temporarySymbols = temporarySymbols;
    }

    @Override
    public Node leaveForNode(final ForNode forNode) {
        if (forNode.isForIn()) {
            return forNode;
        }

        final Expression init   = forNode.getInit();
        final Expression test   = forNode.getTest();
        final Expression modify = forNode.getModify();

        assert test != null || forNode.hasGoto() : "forNode " + forNode + " needs goto and is missing it in " + lc.getCurrentFunction();

        return forNode.
            setInit(lc, init == null ? null : discard(init)).
            setModify(lc, modify == null ? null : discard(modify));
    }

    @Override
    public Node leaveCOMMALEFT(final BinaryNode binaryNode) {
        assert binaryNode.getSymbol() != null;
        return binaryNode.setRHS(discard(binaryNode.rhs()));
    }

    @Override
    public Node leaveCOMMARIGHT(final BinaryNode binaryNode) {
        assert binaryNode.getSymbol() != null;
        return binaryNode.setLHS(discard(binaryNode.lhs()));
    }

    @Override
    public boolean enterBlock(final Block block) {
        updateSymbols(block);
        return true;
    }

    @Override
    public Node leaveExpressionStatement(final ExpressionStatement expressionStatement) {
        temporarySymbols.reuse();
        return expressionStatement.setExpression(discard(expressionStatement.getExpression()));
    }

    @Override
    public boolean enterFunctionNode(final FunctionNode functionNode) {
        if (functionNode.isLazy()) {
            return false;
        }

        // If the function doesn't need a callee, we ensure its __callee__ symbol doesn't get a slot. We can't do
        // this earlier, as access to scoped variables, self symbol, etc. in previous phases can all trigger the
        // need for the callee.
        if (!functionNode.needsCallee()) {
            functionNode.compilerConstant(CALLEE).setNeedsSlot(false);
        }
        // Similar reasoning applies to __scope__ symbol: if the function doesn't need either parent scope and none of
        // its blocks create a scope, we ensure it doesn't get a slot, but we can't determine whether it needs a scope
        // earlier than this phase.
        if (!(functionNode.hasScopeBlock() || functionNode.needsParentScope())) {
            functionNode.compilerConstant(SCOPE).setNeedsSlot(false);
        }

        return true;
    }

    @Override
    public Node leaveFunctionNode(final FunctionNode functionNode) {
        return functionNode.setState(lc, CompilationState.FINALIZED);
    }

    private static void updateSymbolsLog(final FunctionNode functionNode, final Symbol symbol, final boolean loseSlot) {
        if (LOG.isEnabled()) {
            if (!symbol.isScope()) {
                LOG.finest("updateSymbols: ", symbol, " => scope, because all vars in ", functionNode.getName(), " are in scope");
            }
            if (loseSlot && symbol.hasSlot()) {
                LOG.finest("updateSymbols: ", symbol, " => no slot, because all vars in ", functionNode.getName(), " are in scope");
            }
        }
    }

    /**
     * Called after a block or function node (subclass of block) is finished. Guarantees
     * that scope and slot information is correct for every symbol
     * @param block block for which to to finalize type info.
     */
    private void updateSymbols(final Block block) {
        if (!block.needsScope()) {
            return; // nothing to do
        }

        final FunctionNode   functionNode   = lc.getFunction(block);
        final boolean        allVarsInScope = functionNode.allVarsInScope();
        final boolean        isVarArg       = functionNode.isVarArg();

        for (final Symbol symbol : block.getSymbols()) {
            if (symbol.isInternal() || symbol.isThis() || symbol.isTemp()) {
                continue;
            }

            if (symbol.isVar()) {
                if (allVarsInScope || symbol.isScope()) {
                    updateSymbolsLog(functionNode, symbol, true);
                    Symbol.setSymbolIsScope(lc, symbol);
                    symbol.setNeedsSlot(false);
                } else {
                    assert symbol.hasSlot() : symbol + " should have a slot only, no scope";
                }
            } else if (symbol.isParam() && (allVarsInScope || isVarArg || symbol.isScope())) {
                updateSymbolsLog(functionNode, symbol, isVarArg);
                Symbol.setSymbolIsScope(lc, symbol);
                symbol.setNeedsSlot(!isVarArg);
            }
        }
    }

    private static Expression discard(final Expression node) {
        if (node.getSymbol() != null) {
            final UnaryNode discard = new UnaryNode(Token.recast(node.getToken(), TokenType.DISCARD), node);
            //discard never has a symbol in the discard node - then it would be a nop
            assert !node.isTerminal();
            return discard;
        }

        // node has no result (symbol) so we can keep it the way it is
        return node;
    }


}
