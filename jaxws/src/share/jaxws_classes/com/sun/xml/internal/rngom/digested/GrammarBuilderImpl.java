package com.sun.xml.internal.rngom.digested;

import java.util.ArrayList;
import java.util.List;

import com.sun.xml.internal.rngom.ast.builder.Annotations;
import com.sun.xml.internal.rngom.ast.builder.BuildException;
import com.sun.xml.internal.rngom.ast.builder.CommentList;
import com.sun.xml.internal.rngom.ast.builder.Div;
import com.sun.xml.internal.rngom.ast.builder.Grammar;
import com.sun.xml.internal.rngom.ast.builder.Include;
import com.sun.xml.internal.rngom.ast.builder.Scope;
import com.sun.xml.internal.rngom.ast.om.Location;
import com.sun.xml.internal.rngom.ast.om.ParsedElementAnnotation;
import com.sun.xml.internal.rngom.ast.om.ParsedPattern;
import com.sun.xml.internal.rngom.ast.util.LocatorImpl;
import org.w3c.dom.Element;

class GrammarBuilderImpl implements Grammar, Div {

    protected final DGrammarPattern grammar;

    protected final Scope parent;

    protected final DSchemaBuilderImpl sb;

    /**
     * Additional top-level element annotations.
     * Can be null.
     */
    private List<Element> additionalElementAnnotations;

    public GrammarBuilderImpl(DGrammarPattern p, Scope parent, DSchemaBuilderImpl sb) {
        this.grammar = p;
        this.parent = parent;
        this.sb = sb;
    }

    public ParsedPattern endGrammar(Location loc, Annotations anno) throws BuildException {
        // Harshit : Fixed possible NPE and issue in handling of annotations
        if (anno != null) {
            if (grammar.annotation != null) {
                grammar.annotation.contents.addAll(((Annotation) anno).getResult().contents);
            }
        }
        return grammar;
    }

    public void endDiv(Location loc, Annotations anno) throws BuildException {
    }

    public void define(String name, Combine combine, ParsedPattern pattern, Location loc, Annotations anno) throws BuildException {
        if(name==START) {
            grammar.start = (DPattern)pattern;
        } else {
            // TODO: handle combine
            DDefine d = grammar.getOrAdd(name);
            d.setPattern( (DPattern) pattern );
            if (anno!=null) {
                d.annotation = ((Annotation)anno).getResult();
            }
        }
    }

    public void topLevelAnnotation(ParsedElementAnnotation ea) throws BuildException {
        // Harshit : Fixed issue in handling of annotations
        if (additionalElementAnnotations==null) {
            additionalElementAnnotations = new ArrayList<Element>();
        }
        additionalElementAnnotations.add(((ElementWrapper)ea).element);
        if (grammar.annotation==null) {
            grammar.annotation = new DAnnotation();
        }
        grammar.annotation.contents.addAll(additionalElementAnnotations);
    }

    public void topLevelComment(CommentList comments) throws BuildException {
    }

    public Div makeDiv() {
        return this;
    }

    public Include makeInclude() {
        return new IncludeImpl(grammar,parent,sb);
    }

    public ParsedPattern makeParentRef(String name, Location loc, Annotations anno) throws BuildException {
        return parent.makeRef(name,loc,anno);
    }

    public ParsedPattern makeRef(String name, Location loc, Annotations anno) throws BuildException {
        return DSchemaBuilderImpl.wrap( new DRefPattern(grammar.getOrAdd(name)), (LocatorImpl)loc, (Annotation)anno );
    }
}
