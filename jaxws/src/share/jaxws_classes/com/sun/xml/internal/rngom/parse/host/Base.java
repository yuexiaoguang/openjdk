package com.sun.xml.internal.rngom.parse.host;

import com.sun.xml.internal.rngom.ast.builder.Annotations;
import com.sun.xml.internal.rngom.ast.om.Location;

public class Base {
    protected AnnotationsHost cast( Annotations ann ) {
        if(ann==null)
            return nullAnnotations;
        else
            return (AnnotationsHost)ann;
    }

    protected LocationHost cast( Location loc ) {
        if(loc==null)
            return nullLocation;
        else
            return (LocationHost)loc;
    }

    private static final AnnotationsHost nullAnnotations = new AnnotationsHost(null,null);
    private static final LocationHost nullLocation = new LocationHost(null,null);
}
