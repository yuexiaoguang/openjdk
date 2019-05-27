/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xalan.internal.xsltc.dom;

import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;

public class ArrayNodeListIterator implements DTMAxisIterator  {

    private int _pos = 0;

    private int _mark = 0;

    private int _nodes[];

    private static final int[] EMPTY = { };

    public ArrayNodeListIterator(int[] nodes) {
        _nodes = nodes;
    }

    public int next() {
        return _pos < _nodes.length ? _nodes[_pos++] : END;
    }

    public DTMAxisIterator reset() {
        _pos = 0;
        return this;
    }

    public int getLast() {
        return _nodes.length;
    }

    public int getPosition() {
        return _pos;
    }

    public void setMark() {
        _mark = _pos;
    }

    public void gotoMark() {
        _pos = _mark;
    }

    public DTMAxisIterator setStartNode(int node) {
        if (node == END) _nodes = EMPTY;
        return this;
    }

    public int getStartNode() {
        return END;
    }

    public boolean isReverse() {
        return false;
    }

    public DTMAxisIterator cloneIterator() {
        return new ArrayNodeListIterator(_nodes);
    }

    public void setRestartable(boolean isRestartable) {
    }

    public int getNodeByPosition(int position) {
        return _nodes[position - 1];
    }

}
