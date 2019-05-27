package com.sun.tools.hat.internal.oql;

/**
 * This represents a parsed OQL query
 */
class OQLQuery {
    OQLQuery(String selectExpr, boolean isInstanceOf,
             String className, String identifier, String whereExpr) {
        this.selectExpr = selectExpr;
        this.isInstanceOf = isInstanceOf;
        this.className = className;
        this.identifier = identifier;
        this.whereExpr = whereExpr;
    }

    String   selectExpr;
    boolean  isInstanceOf;
    String   className;
    String   identifier;
    String   whereExpr;
}
