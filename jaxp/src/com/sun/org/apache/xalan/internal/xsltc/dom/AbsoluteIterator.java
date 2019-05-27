/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xalan.internal.xsltc.dom;

import com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase;
import com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase;

/**
 * Absolute iterators ignore the node that is passed to setStartNode().
 * Instead, they always start from the root node. The node passed to
 * setStartNode() is not totally useless, though. It is needed to obtain the
 * DOM mask, i.e. the index into the MultiDOM table that corresponds to the
 * DOM "owning" the node.
 *
 * The DOM mask is cached, so successive calls to setStartNode() passing
 * nodes from other DOMs will have no effect (i.e. this iterator cannot
 * migrate between DOMs).
 */
public final class AbsoluteIterator extends DTMAxisIteratorBase {

    /**
     * Source for this iterator.
     */
    private DTMAxisIterator _source;

    public AbsoluteIterator(DTMAxisIterator source) {
        _source = source;
// System.out.println("AI source = " + source + " this = " + this);
    }

    public void setRestartable(boolean isRestartable) {
        _isRestartable = isRestartable;
        _source.setRestartable(isRestartable);
    }

    public DTMAxisIterator setStartNode(int node) {
        _startNode = DTMDefaultBase.ROOTNODE;
        if (_isRestartable) {
            _source.setStartNode(_startNode);
            resetPosition();
        }
        return this;
    }

    public int next() {
        return returnNode(_source.next());
    }

    public DTMAxisIterator cloneIterator() {
        try {
            final AbsoluteIterator clone = (AbsoluteIterator) super.clone();
            clone._source = _source.cloneIterator();    // resets source
            clone.resetPosition();
            clone._isRestartable = false;
            return clone;
        }
        catch (CloneNotSupportedException e) {
            BasisLibrary.runTimeError(BasisLibrary.ITERATOR_CLONE_ERR,
                                      e.toString());
            return null;
        }
    }

    public DTMAxisIterator reset() {
        _source.reset();
        return resetPosition();
    }

    public void setMark() {
        _source.setMark();
    }

    public void gotoMark() {
        _source.gotoMark();
    }
}
