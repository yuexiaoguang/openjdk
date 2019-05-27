/**
 * This class implements an RTF Iterator. Currently exists for sole
 * purpose of enabling EXSLT object-type function to return "RTF".
 */
package com.sun.org.apache.xpath.internal.axes;

import com.sun.org.apache.xml.internal.dtm.DTMManager;
import com.sun.org.apache.xpath.internal.NodeSetDTM;

public class RTFIterator extends NodeSetDTM {
    static final long serialVersionUID = 7658117366258528996L;

        /**
         * Constructor for RTFIterator
         */
        public RTFIterator(int root, DTMManager manager) {
                super(root, manager);
        }
}
