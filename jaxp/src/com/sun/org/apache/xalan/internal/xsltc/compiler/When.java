/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.BooleanType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;

final class When extends Instruction {

    private Expression _test;
    private boolean _ignore = false;

    public void display(int indent) {
        indent(indent);
        Util.println("When");
        indent(indent + IndentIncrement);
        System.out.print("test ");
        Util.println(_test.toString());
        displayContents(indent + IndentIncrement);
    }

    public Expression getTest() {
        return _test;
    }

    public boolean ignore() {
        return(_ignore);
    }

    public void parseContents(Parser parser) {
        _test = parser.parseExpression(this, "test", null);

        // Ignore xsl:if when test is false (function-available() and
        // element-available())
        Object result = _test.evaluateAtCompileTime();
        if (result != null && result instanceof Boolean) {
            _ignore = !((Boolean) result).booleanValue();
        }

        parseChildren(parser);

        // Make sure required attribute(s) have been set
        if (_test.isDummy()) {
            reportError(this, parser, ErrorMsg.REQUIRED_ATTR_ERR, "test");
        }
    }

    /**
     * Type-check this when element. The test should always be type checked,
     * while we do not bother with the contents if we know the test fails.
     * This is important in cases where the "test" expression tests for
     * the support of a non-available element, and the <xsl:when> body contains
     * this non-available element.
     */
    public Type typeCheck(SymbolTable stable) throws TypeCheckError {
        // Type-check the test expression
        if (_test.typeCheck(stable) instanceof BooleanType == false) {
            _test = new CastExpr(_test, Type.Boolean);
        }
        // Type-check the contents (if necessary)
        if (!_ignore) {
            typeCheckContents(stable);
        }

        return Type.Void;
    }

    /**
     * This method should never be called. An Otherwise object will explicitly
     * translate the "test" expression and and contents of this element.
     */
    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        final ErrorMsg msg = new ErrorMsg(ErrorMsg.STRAY_WHEN_ERR, this);
        getParser().reportError(Constants.ERROR, msg);
    }
}
