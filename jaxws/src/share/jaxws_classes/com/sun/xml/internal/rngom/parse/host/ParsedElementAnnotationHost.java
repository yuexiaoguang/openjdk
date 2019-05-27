package com.sun.xml.internal.rngom.parse.host;

import com.sun.xml.internal.rngom.ast.om.ParsedElementAnnotation;

final class ParsedElementAnnotationHost implements ParsedElementAnnotation {
    final ParsedElementAnnotation lhs;
    final ParsedElementAnnotation rhs;

    ParsedElementAnnotationHost( ParsedElementAnnotation lhs, ParsedElementAnnotation rhs ) {
        this.lhs = lhs;
        this.rhs = rhs;
    }
}
