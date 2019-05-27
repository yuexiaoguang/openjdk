package org.w3c.dom.css;

import org.w3c.dom.DOMException;

/**
 *  The <code>CSSStyleRule</code> interface represents a single rule set in a
 * CSS style sheet.
 * <p>See also the <a href='http://www.w3.org/TR/2000/REC-DOM-Level-2-Style-20001113'>Document Object Model (DOM) Level 2 Style Specification</a>.
 * @since DOM Level 2
 */
public interface CSSStyleRule extends CSSRule {
    /**
     *  The textual representation of the selector for the rule set. The
     * implementation may have stripped out insignificant whitespace while
     * parsing the selector.
     */
    public String getSelectorText();
    /**
     *  The textual representation of the selector for the rule set. The
     * implementation may have stripped out insignificant whitespace while
     * parsing the selector.
     * @exception DOMException
     *   SYNTAX_ERR: Raised if the specified CSS string value has a syntax
     *   error and is unparsable.
     *   <br>NO_MODIFICATION_ALLOWED_ERR: Raised if this rule is readonly.
     */
    public void setSelectorText(String selectorText)
                        throws DOMException;

    /**
     *  The declaration-block of this rule set.
     */
    public CSSStyleDeclaration getStyle();

}
