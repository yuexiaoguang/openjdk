/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xalan.internal.xsltc.compiler;

final class QName {
    private final String _localname;
    private String _prefix;
    private String _namespace;
    private String _stringRep;
    private int    _hashCode;

    public QName(String namespace, String prefix, String localname) {
        _namespace = namespace;
        _prefix    = prefix;
        _localname = localname;

        _stringRep =
            (namespace != null && !namespace.equals(Constants.EMPTYSTRING)) ?
            (namespace + ':' + localname) : localname;

        _hashCode  = _stringRep.hashCode() + 19; // cached for speed
    }

    public void clearNamespace() {
        _namespace = Constants.EMPTYSTRING;
    }

    public String toString() {
        return _stringRep;
    }

    public String getStringRep() {
        return _stringRep;
    }

    public boolean equals(Object other) {
        return (this == other)
                   || (other instanceof QName
                           && _stringRep.equals(((QName) other).getStringRep()));
    }

    public String getLocalPart() {
        return _localname;
    }

    public String getNamespace() {
        return _namespace;
    }

    public String getPrefix() {
        return _prefix;
    }

    public int hashCode() {
        return _hashCode;
    }

    public String dump() {
        return "QName: " + _namespace + "(" + _prefix + "):" + _localname;
    }
}
