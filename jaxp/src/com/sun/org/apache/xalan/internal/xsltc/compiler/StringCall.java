/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xalan.internal.xsltc.compiler;

import java.util.Vector;

import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;

final class StringCall extends FunctionCall {
    public StringCall(QName fname, Vector arguments) {
        super(fname, arguments);
    }

    public Type typeCheck(SymbolTable stable) throws TypeCheckError {
        final int argc = argumentCount();
        if (argc > 1) {
            ErrorMsg err = new ErrorMsg(ErrorMsg.ILLEGAL_ARG_ERR, this);
            throw new TypeCheckError(err);
        }

        if (argc > 0) {
            argument().typeCheck(stable);
        }
        return _type = Type.String;
    }

    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        final InstructionList il = methodGen.getInstructionList();
        Type targ;

        if (argumentCount() == 0) {
            il.append(methodGen.loadContextNode());
            targ = Type.Node;
        }
        else {
            final Expression arg = argument();
            arg.translate(classGen, methodGen);
            arg.startIterator(classGen, methodGen);
            targ = arg.getType();
        }

        if (!targ.identicalTo(Type.String)) {
            targ.translateTo(classGen, methodGen, Type.String);
        }
    }
}
