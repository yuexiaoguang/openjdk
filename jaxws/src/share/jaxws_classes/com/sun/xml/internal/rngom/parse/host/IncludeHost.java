package com.sun.xml.internal.rngom.parse.host;

import com.sun.xml.internal.rngom.ast.builder.Annotations;
import com.sun.xml.internal.rngom.ast.builder.BuildException;
import com.sun.xml.internal.rngom.ast.builder.Include;
import com.sun.xml.internal.rngom.ast.om.Location;
import com.sun.xml.internal.rngom.parse.IllegalSchemaException;
import com.sun.xml.internal.rngom.parse.Parseable;

public class IncludeHost extends GrammarSectionHost implements Include {

    private final Include lhs;
    private final Include rhs;

    IncludeHost(Include lhs, Include rhs) {
        super(lhs, rhs);
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public void endInclude(Parseable current, String uri, String ns, Location _loc, Annotations _anno) throws BuildException, IllegalSchemaException {
        LocationHost loc = cast(_loc);
        AnnotationsHost anno = cast(_anno);

        lhs.endInclude( current, uri, ns, loc.lhs, anno.lhs );
        rhs.endInclude( current, uri, ns, loc.rhs, anno.rhs );
    }
}
