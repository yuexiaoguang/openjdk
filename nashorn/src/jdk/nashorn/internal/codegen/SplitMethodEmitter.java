package jdk.nashorn.internal.codegen;

import java.util.ArrayList;
import java.util.List;

import static jdk.nashorn.internal.codegen.CompilerConstants.SCOPE;

import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.nashorn.internal.codegen.types.Type;
import jdk.nashorn.internal.ir.LexicalContext;
import jdk.nashorn.internal.ir.SplitNode;
import jdk.nashorn.internal.runtime.Scope;

/**
 * Emitter used for splitting methods. Needs to keep track of if there are jump targets
 * outside the current split node. All external jump targets encountered at method
 * emission are logged, and {@code CodeGenerator#leaveSplitNode(SplitNode)} creates
 * an appropriate jump table when the SplitNode has been iterated through
 */
public class SplitMethodEmitter extends MethodEmitter {

    private final SplitNode splitNode;

    private final List<Label> externalTargets = new ArrayList<>();

    SplitMethodEmitter(final ClassEmitter classEmitter, final MethodVisitor mv, SplitNode splitNode) {
        super(classEmitter, mv);
        this.splitNode = splitNode;
    }

    @Override
    void splitAwareGoto(final LexicalContext lc, final Label label) {
        assert splitNode != null;
        final int index = findExternalTarget(lc, label);
        if (index >= 0) {
            loadCompilerConstant(SCOPE);
            checkcast(Scope.class);
            load(index + 1);
            invoke(Scope.SET_SPLIT_STATE);
            loadUndefined(Type.OBJECT);
            _return(functionNode.getReturnType());
            return;
        }
        super.splitAwareGoto(lc, label);
    }

    private int findExternalTarget(final LexicalContext lc, final Label label) {
        final int index = externalTargets.indexOf(label);

        if (index >= 0) {
            return index;
        }

        if (lc.isExternalTarget(splitNode, label)) {
            externalTargets.add(label);
            return externalTargets.size() - 1;
        }
        return -1;
    }

    @Override
    MethodEmitter registerReturn() {
        setHasReturn();
        loadCompilerConstant(SCOPE);
        checkcast(Scope.class);
        load(0);
        invoke(Scope.SET_SPLIT_STATE);
        return this;
    }

    @Override
    final List<Label> getExternalTargets() {
        return externalTargets;
    }
}
