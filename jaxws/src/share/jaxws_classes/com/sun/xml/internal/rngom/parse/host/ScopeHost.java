package com.sun.xml.internal.rngom.parse.host;

import com.sun.xml.internal.rngom.ast.builder.Annotations;
import com.sun.xml.internal.rngom.ast.builder.BuildException;
import com.sun.xml.internal.rngom.ast.builder.Scope;
import com.sun.xml.internal.rngom.ast.om.Location;
import com.sun.xml.internal.rngom.ast.om.ParsedPattern;

public class ScopeHost extends GrammarSectionHost implements Scope {
    protected final Scope lhs;
    protected final Scope rhs;

    protected ScopeHost( Scope lhs, Scope rhs ) {
        super(lhs,rhs);
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public ParsedPattern makeParentRef(String name, Location _loc, Annotations _anno) throws BuildException {
        LocationHost loc = cast(_loc);
        AnnotationsHost anno = cast(_anno);

        return new ParsedPatternHost(
            lhs.makeParentRef(name, loc.lhs, anno.lhs),
            rhs.makeParentRef(name, loc.rhs, anno.rhs));
    }

    public ParsedPattern makeRef(String name, Location _loc, Annotations _anno) throws BuildException {
        LocationHost loc = cast(_loc);
        AnnotationsHost anno = cast(_anno);

        return new ParsedPatternHost(
            lhs.makeRef(name, loc.lhs, anno.lhs),
            rhs.makeRef(name, loc.rhs, anno.rhs));
    }
}
