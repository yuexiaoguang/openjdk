package org.w3c.dom.css;

/**
 *  Inline style information attached to elements is exposed through the
 * <code>style</code> attribute. This represents the contents of the STYLE
 * attribute for HTML elements (or elements in other schemas or DTDs which
 * use the STYLE attribute in the same way). The expectation is that an
 * instance of the ElementCSSInlineStyle interface can be obtained by using
 * binding-specific casting methods on an instance of the Element interface
 * when the element supports inline CSS style informations.
 * <p>See also the <a href='http://www.w3.org/TR/2000/REC-DOM-Level-2-Style-20001113'>Document Object Model (DOM) Level 2 Style Specification</a>.
 * @since DOM Level 2
 */
public interface ElementCSSInlineStyle {
    /**
     *  The style attribute.
     */
    public CSSStyleDeclaration getStyle();

}
