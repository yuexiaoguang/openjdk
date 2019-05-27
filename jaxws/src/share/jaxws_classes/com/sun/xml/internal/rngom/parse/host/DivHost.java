package com.sun.xml.internal.rngom.parse.host;

import com.sun.xml.internal.rngom.ast.builder.Annotations;
import com.sun.xml.internal.rngom.ast.builder.BuildException;
import com.sun.xml.internal.rngom.ast.builder.Div;
import com.sun.xml.internal.rngom.ast.om.Location;

public class DivHost extends GrammarSectionHost implements Div {
    private final Div lhs;
    private final Div rhs;

    DivHost(Div lhs, Div rhs) {
        super(lhs, rhs);
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public void endDiv(Location _loc, Annotations _anno) throws BuildException {
        LocationHost loc = cast(_loc);
        AnnotationsHost anno = cast(_anno);

        lhs.endDiv( loc.lhs, anno.lhs );
        rhs.endDiv( loc.rhs, anno.rhs );
    }

}
