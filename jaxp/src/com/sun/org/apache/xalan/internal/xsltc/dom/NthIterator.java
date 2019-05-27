/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xalan.internal.xsltc.dom;

import com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase;

public final class NthIterator extends DTMAxisIteratorBase {
    // ...[N]
    private DTMAxisIterator _source;
    private final int _position;
    private boolean _ready;

    public NthIterator(DTMAxisIterator source, int n) {
        _source = source;
        _position = n;
    }

    public void setRestartable(boolean isRestartable) {
        _isRestartable = isRestartable;
        _source.setRestartable(isRestartable);
    }

    public DTMAxisIterator cloneIterator() {
        try {
            final NthIterator clone = (NthIterator) super.clone();
            clone._source = _source.cloneIterator();    // resets source
            clone._isRestartable = false;
            return clone;
        }
        catch (CloneNotSupportedException e) {
            BasisLibrary.runTimeError(BasisLibrary.ITERATOR_CLONE_ERR,
                                      e.toString());
            return null;
        }
    }

    public int next() {
        if (_ready) {
            _ready = false;
            return _source.getNodeByPosition(_position);
        }
        return DTMAxisIterator.END;
        /*
        if (_ready && _position > 0) {
            final int pos = _source.isReverse()
                                       ? _source.getLast() - _position + 1
                                       : _position;

            _ready = false;
            int node;
            while ((node = _source.next()) != DTMAxisIterator.END) {
                if (pos == _source.getPosition()) {
                    return node;
                }
            }
        }
        return DTMAxisIterator.END;
        */
    }

    public DTMAxisIterator setStartNode(final int node) {
        if (_isRestartable) {
            _source.setStartNode(node);
            _ready = true;
        }
        return this;
    }

    public DTMAxisIterator reset() {
        _source.reset();
        _ready = true;
        return this;
    }

    public int getLast() {
        return 1;
    }

    public int getPosition() {
        return 1;
    }

    public void setMark() {
        _source.setMark();
    }

    public void gotoMark() {
        _source.gotoMark();
    }
}
