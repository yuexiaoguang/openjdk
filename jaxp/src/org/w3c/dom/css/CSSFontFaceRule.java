package org.w3c.dom.css;

/**
 *  The <code>CSSFontFaceRule</code> interface represents a @font-face rule in
 * a CSS style sheet. The <code>@font-face</code> rule is used to hold a set
 * of font descriptions.
 * <p>See also the <a href='http://www.w3.org/TR/2000/REC-DOM-Level-2-Style-20001113'>Document Object Model (DOM) Level 2 Style Specification</a>.
 * @since DOM Level 2
 */
public interface CSSFontFaceRule extends CSSRule {
    /**
     *  The declaration-block of this rule.
     */
    public CSSStyleDeclaration getStyle();

}
