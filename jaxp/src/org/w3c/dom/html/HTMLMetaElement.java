package org.w3c.dom.html;

/**
 *  This contains generic meta-information about the document. See the  META
 * element definition in HTML 4.0.
 * <p>See also the <a href='http://www.w3.org/TR/2000/CR-DOM-Level-2-20000510'>Document Object Model (DOM) Level 2 Specification</a>.
 */
public interface HTMLMetaElement extends HTMLElement {
    /**
     *  Associated information. See the  content attribute definition in HTML
     * 4.0.
     */
    public String getContent();
    public void setContent(String content);

    /**
     *  HTTP response header name. See the  http-equiv attribute definition in
     * HTML 4.0.
     */
    public String getHttpEquiv();
    public void setHttpEquiv(String httpEquiv);

    /**
     *  Meta information name. See the  name attribute definition in HTML 4.0.
     */
    public String getName();
    public void setName(String name);

    /**
     *  Select form of content. See the  scheme attribute definition in HTML
     * 4.0.
     */
    public String getScheme();
    public void setScheme(String scheme);

}
