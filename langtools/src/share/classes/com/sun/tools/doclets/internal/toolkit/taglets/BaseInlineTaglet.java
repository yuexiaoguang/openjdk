package com.sun.tools.doclets.internal.toolkit.taglets;

/**
 * An abstract inline taglet that outputs HTML.
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */
public abstract class BaseInlineTaglet extends BaseTaglet {

    /**
     * Will return true since this is an inline tag.
     * @return true since this is an inline tag.
     */
    public boolean isInlineTag() {
        return true;
    }
}
