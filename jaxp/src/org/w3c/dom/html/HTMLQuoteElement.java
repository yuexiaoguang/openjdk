package org.w3c.dom.html;

/**
 *  For the <code>Q</code> and <code>BLOCKQUOTE</code> elements. See the  Q
 * element definition in HTML 4.0.
 * <p>See also the <a href='http://www.w3.org/TR/2000/CR-DOM-Level-2-20000510'>Document Object Model (DOM) Level 2 Specification</a>.
 */
public interface HTMLQuoteElement extends HTMLElement {
    /**
     *  A URI designating a source document or message. See the  cite
     * attribute definition in HTML 4.0.
     */
    public String getCite();
    public void setCite(String cite);

}
