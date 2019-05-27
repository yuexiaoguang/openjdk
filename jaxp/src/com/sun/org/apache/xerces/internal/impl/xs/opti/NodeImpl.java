/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xerces.internal.impl.xs.opti;

public class NodeImpl extends DefaultNode {

    String prefix;
    String localpart;
    String rawname;
    String uri;
    short nodeType;
    boolean hidden;


    public NodeImpl() {
    }


    public NodeImpl(String prefix, String localpart, String rawname, String uri, short nodeType) {
        this.prefix = prefix;
        this.localpart = localpart;
        this.rawname = rawname;
        this.uri = uri;
        this.nodeType = nodeType;
    }


    public String getNodeName() {
        return rawname;
    }


    public String getNamespaceURI() {
        return uri;
    }


    public String getPrefix() {
        return prefix;
    }


    public String getLocalName() {
        return localpart;
    }


    public short getNodeType() {
        return nodeType;
    }


    // other methods

    public void setReadOnly(boolean hide, boolean deep) {
        hidden = hide;
    }


    public boolean getReadOnly() {
        return hidden;
    }
}
