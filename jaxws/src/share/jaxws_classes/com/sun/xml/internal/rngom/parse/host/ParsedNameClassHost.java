package com.sun.xml.internal.rngom.parse.host;

import com.sun.xml.internal.rngom.ast.om.ParsedNameClass;

final class ParsedNameClassHost implements ParsedNameClass {
    final ParsedNameClass lhs;
    final ParsedNameClass rhs;

    ParsedNameClassHost( ParsedNameClass lhs, ParsedNameClass rhs ) {
        this.lhs = lhs;
        this.rhs = rhs;
    }
}
