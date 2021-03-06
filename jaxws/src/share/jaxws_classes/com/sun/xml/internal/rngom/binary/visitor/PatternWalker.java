package com.sun.xml.internal.rngom.binary.visitor;

import com.sun.xml.internal.rngom.binary.Pattern;
import com.sun.xml.internal.rngom.nc.NameClass;
import org.relaxng.datatype.Datatype;

/**
 * Walks the pattern tree.
 */
public class PatternWalker implements PatternVisitor {
    public void visitEmpty() {
    }

    public void visitNotAllowed() {
    }

    public void visitError() {
    }

    public void visitGroup(Pattern p1, Pattern p2) {
        visitBinary(p1, p2);
    }

    protected void visitBinary(Pattern p1, Pattern p2) {
        p1.accept(this);
        p2.accept(this);
    }

    public void visitInterleave(Pattern p1, Pattern p2) {
        visitBinary(p1, p2);
    }

    public void visitChoice(Pattern p1, Pattern p2) {
        visitBinary(p1, p2);
    }

    public void visitOneOrMore(Pattern p) {
        p.accept(this);
    }

    public void visitElement(NameClass nc, Pattern content) {
        content.accept(this);
    }

    public void visitAttribute(NameClass ns, Pattern value) {
        value.accept(this);
    }

    public void visitData(Datatype dt) {
    }

    public void visitDataExcept(Datatype dt, Pattern except) {
    }

    public void visitValue(Datatype dt, Object obj) {
    }

    public void visitText() {
    }

    public void visitList(Pattern p) {
        p.accept(this);
    }

    public void visitAfter(Pattern p1, Pattern p2) {
    }
}
