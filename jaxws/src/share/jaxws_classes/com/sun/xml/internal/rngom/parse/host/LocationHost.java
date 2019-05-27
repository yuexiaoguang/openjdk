package com.sun.xml.internal.rngom.parse.host;

import com.sun.xml.internal.rngom.ast.om.Location;

final class LocationHost implements Location {
    final Location lhs;
    final Location rhs;

    LocationHost( Location lhs, Location rhs ) {
        this.lhs = lhs;
        this.rhs = rhs;
    }
}
