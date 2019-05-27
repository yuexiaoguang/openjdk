package com.sun.org.apache.xpath.internal.domapi;

import com.sun.org.apache.xml.internal.utils.PrefixResolverDefault;
import org.w3c.dom.Node;
import org.w3c.dom.xpath.XPathNSResolver;

/**
 *
 * The class provides an implementation XPathNSResolver according
 * to the DOM L3 XPath Specification, Working Group Note 26 February 2004.
 *
 * <p>See also the <a href='http://www.w3.org/TR/2004/NOTE-DOM-Level-3-XPath-20040226'>Document Object Model (DOM) Level 3 XPath Specification</a>.</p>
 *
 * <p>The <code>XPathNSResolver</code> interface permit <code>prefix</code>
 * strings in the expression to be properly bound to
 * <code>namespaceURI</code> strings. <code>XPathEvaluator</code> can
 * construct an implementation of <code>XPathNSResolver</code> from a node,
 * or the interface may be implemented by any application.</p>
 */
class XPathNSResolverImpl extends PrefixResolverDefault implements XPathNSResolver {

        /**
         * Constructor for XPathNSResolverImpl.
         * @param xpathExpressionContext
         */
        public XPathNSResolverImpl(Node xpathExpressionContext) {
                super(xpathExpressionContext);
        }

        /**
         * @see org.w3c.dom.xpath.XPathNSResolver#lookupNamespaceURI(String)
         */
        public String lookupNamespaceURI(String prefix) {
                return super.getNamespaceForPrefix(prefix);
        }

}
