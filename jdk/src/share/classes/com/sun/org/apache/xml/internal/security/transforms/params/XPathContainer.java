package com.sun.org.apache.xml.internal.security.transforms.params;


import com.sun.org.apache.xml.internal.security.transforms.TransformParam;
import com.sun.org.apache.xml.internal.security.utils.Constants;
import com.sun.org.apache.xml.internal.security.utils.SignatureElementProxy;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * This Object serves both as namespace prefix resolver and as container for
 * the <CODE>ds:XPath</CODE> Element. It implements the {@link org.w3c.dom.Element} interface
 * and can be used directly in a DOM tree.
 */
public class XPathContainer extends SignatureElementProxy implements TransformParam {

    /**
     * Constructor XPathContainer
     *
     * @param doc
     */
    public XPathContainer(Document doc) {
        super(doc);
    }

    /**
     * Sets the TEXT value of the <CODE>ds:XPath</CODE> Element.
     *
     * @param xpath
     */
    public void setXPath(String xpath) {
        if (this.constructionElement.getChildNodes() != null) {
            NodeList nl = this.constructionElement.getChildNodes();

            for (int i = 0; i < nl.getLength(); i++) {
                this.constructionElement.removeChild(nl.item(i));
            }
        }

        Text xpathText = this.doc.createTextNode(xpath);
        this.constructionElement.appendChild(xpathText);
    }

    /**
     * Returns the TEXT value of the <CODE>ds:XPath</CODE> Element.
     *
     * @return the TEXT value of the <CODE>ds:XPath</CODE> Element.
     */
    public String getXPath() {
        return this.getTextFromTextChild();
    }

    /** @inheritDoc */
    public String getBaseLocalName() {
        return Constants._TAG_XPATH;
    }
}
