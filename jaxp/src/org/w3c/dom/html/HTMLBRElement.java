package org.w3c.dom.html;

/**
 *  Force a line break. See the  BR element definition in HTML 4.0.
 * <p>See also the <a href='http://www.w3.org/TR/2000/CR-DOM-Level-2-20000510'>Document Object Model (DOM) Level 2 Specification</a>.
 */
public interface HTMLBRElement extends HTMLElement {
    /**
     *  Control flow of text around floats. See the  clear attribute definition
     *  in HTML 4.0. This attribute is deprecated in HTML 4.0.
     */
    public String getClear();
    public void setClear(String clear);

}
