package com.sun.xml.internal.rngom.parse.host;

import com.sun.xml.internal.rngom.ast.om.ParsedPattern;

public class ParsedPatternHost implements ParsedPattern {
    public final ParsedPattern lhs;
    public final ParsedPattern rhs;

    ParsedPatternHost( ParsedPattern lhs, ParsedPattern rhs ) {
        this.lhs = lhs;
        this.rhs = rhs;
    }
}
