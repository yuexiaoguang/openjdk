package org.w3c.dom.html;

/**
 *  Root of an HTML document. See the  HTML element definition in HTML 4.0.
 * <p>See also the <a href='http://www.w3.org/TR/2000/CR-DOM-Level-2-20000510'>Document Object Model (DOM) Level 2 Specification</a>.
 */
public interface HTMLHtmlElement extends HTMLElement {
    /**
     *  Version information about the document's DTD. See the  version
     * attribute definition in HTML 4.0. This attribute is deprecated in HTML
     * 4.0.
     */
    public String getVersion();
    public void setVersion(String version);

}
