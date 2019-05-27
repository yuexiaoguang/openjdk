package com.sun.xml.internal.bind.v2.model.annotation;

import com.sun.xml.internal.bind.v2.model.nav.Navigator;
import com.sun.xml.internal.bind.v2.runtime.Location;

/**
 * {@link Locatable} implementation for a field.
 */
public class FieldLocatable<F> implements Locatable {
    private final Locatable upstream;
    private final F field;
    private final Navigator<?,?,F,?> nav;

    public FieldLocatable(Locatable upstream, F field, Navigator<?,?,F,?> nav) {
        this.upstream = upstream;
        this.field = field;
        this.nav = nav;
    }

    public Locatable getUpstream() {
        return upstream;
    }

    public Location getLocation() {
        return nav.getFieldLocation(field);
    }
}
