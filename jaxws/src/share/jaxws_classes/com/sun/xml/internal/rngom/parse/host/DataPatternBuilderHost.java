package com.sun.xml.internal.rngom.parse.host;

import com.sun.xml.internal.rngom.ast.builder.Annotations;
import com.sun.xml.internal.rngom.ast.builder.BuildException;
import com.sun.xml.internal.rngom.ast.builder.DataPatternBuilder;
import com.sun.xml.internal.rngom.ast.om.Location;
import com.sun.xml.internal.rngom.ast.om.ParsedElementAnnotation;
import com.sun.xml.internal.rngom.ast.om.ParsedPattern;
import com.sun.xml.internal.rngom.parse.Context;

final class DataPatternBuilderHost extends Base implements DataPatternBuilder {
    final DataPatternBuilder lhs;
    final DataPatternBuilder rhs;

    DataPatternBuilderHost( DataPatternBuilder lhs, DataPatternBuilder rhs ) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public void addParam(String name, String value, Context context, String ns, Location _loc, Annotations _anno) throws BuildException {
        LocationHost loc = cast(_loc);
        AnnotationsHost anno = cast(_anno);

        lhs.addParam( name, value, context, ns, loc.lhs, anno.lhs );
        rhs.addParam( name, value, context, ns, loc.rhs, anno.rhs );
    }

    public void annotation(ParsedElementAnnotation _ea) {
        ParsedElementAnnotationHost ea = (ParsedElementAnnotationHost) _ea;

        lhs.annotation(ea.lhs);
        rhs.annotation(ea.rhs);
    }

    public ParsedPattern makePattern(Location _loc, Annotations _anno) throws BuildException {
        LocationHost loc = cast(_loc);
        AnnotationsHost anno = cast(_anno);

        return new ParsedPatternHost(
            lhs.makePattern( loc.lhs, anno.lhs ),
            rhs.makePattern( loc.rhs, anno.rhs ));
    }

    public ParsedPattern makePattern(ParsedPattern _except, Location _loc, Annotations _anno) throws BuildException {
        ParsedPatternHost except = (ParsedPatternHost) _except;
        LocationHost loc = cast(_loc);
        AnnotationsHost anno = cast(_anno);

        return new ParsedPatternHost(
            lhs.makePattern(except.lhs, loc.lhs, anno.lhs),
            rhs.makePattern(except.rhs, loc.rhs, anno.rhs));
    }
}
