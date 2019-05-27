package org.w3c.dom.html;

/**
 *  Menu list. See the  MENU element definition in HTML 4.0. This element is
 * deprecated in HTML 4.0.
 * <p>See also the <a href='http://www.w3.org/TR/2000/CR-DOM-Level-2-20000510'>Document Object Model (DOM) Level 2 Specification</a>.
 */
public interface HTMLMenuElement extends HTMLElement {
    /**
     *  Reduce spacing between list items. See the  compact attribute
     * definition in HTML 4.0. This attribute is deprecated in HTML 4.0.
     */
    public boolean getCompact();
    public void setCompact(boolean compact);

}
