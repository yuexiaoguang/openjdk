/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.GETFIELD;
import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;

final class Comment extends Instruction {

    public void parseContents(Parser parser) {
        parseChildren(parser);
    }

    public Type typeCheck(SymbolTable stable) throws TypeCheckError {
        typeCheckContents(stable);
        return Type.String;
    }

    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();

        // Shortcut for literal strings
        Text rawText = null;
        if (elementCount() == 1) {
            Object content = elementAt(0);
            if (content instanceof Text) {
                rawText = (Text) content;
            }
        }

        // If the content is literal text, call comment(char[],int,int) or
        // comment(String), as appropriate.  Otherwise, use a
        // StringValueHandler to gather the textual content of the xsl:comment
        // and call comment(String) with the result.
        if (rawText != null) {
            il.append(methodGen.loadHandler());

            if (rawText.canLoadAsArrayOffsetLength()) {
                rawText.loadAsArrayOffsetLength(classGen, methodGen);
                final int comment =
                        cpg.addInterfaceMethodref(TRANSLET_OUTPUT_INTERFACE,
                                                  "comment",
                                                  "([CII)V");
                il.append(new INVOKEINTERFACE(comment, 4));
            } else {
                il.append(new PUSH(cpg, rawText.getText()));
                final int comment =
                        cpg.addInterfaceMethodref(TRANSLET_OUTPUT_INTERFACE,
                                                  "comment",
                                                  "(" + STRING_SIG + ")V");
                il.append(new INVOKEINTERFACE(comment, 2));
            }
        } else {
            // Save the current handler base on the stack
            il.append(methodGen.loadHandler());
            il.append(DUP);             // first arg to "comment" call

            // Get the translet's StringValueHandler
            il.append(classGen.loadTranslet());
            il.append(new GETFIELD(cpg.addFieldref(TRANSLET_CLASS,
                                                   "stringValueHandler",
                                                   STRING_VALUE_HANDLER_SIG)));
            il.append(DUP);
            il.append(methodGen.storeHandler());

            // translate contents with substituted handler
            translateContents(classGen, methodGen);

            // get String out of the handler
            il.append(new INVOKEVIRTUAL(cpg.addMethodref(STRING_VALUE_HANDLER,
                                                         "getValue",
                                                         "()" + STRING_SIG)));
            // call "comment"
            final int comment =
                        cpg.addInterfaceMethodref(TRANSLET_OUTPUT_INTERFACE,
                                                  "comment",
                                                  "(" + STRING_SIG + ")V");
            il.append(new INVOKEINTERFACE(comment, 2));
            // Restore old handler base from stack
            il.append(methodGen.storeHandler());
        }
    }
}
