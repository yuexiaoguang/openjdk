package com.sun.org.apache.xml.internal.resolver.tools;

import org.xml.sax.*;

import javax.xml.parsers.*;

import com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl;
import com.sun.org.apache.xml.internal.resolver.*;

/**
 * A SAX XMLReader that performs catalog-based entity resolution.
 *
 * <p>This class implements a SAX XMLReader that performs entity resolution
 * using the CatalogResolver. The actual, underlying parser is obtained
 * from a SAXParserFactory.</p>
 * </p>
 */
public class ResolvingXMLReader extends ResolvingXMLFilter {
  /** Make the parser Namespace aware? */
  public static boolean namespaceAware = true;

  /** Make the parser validating? */
  public static boolean validating = false;

  /**
   * Construct a new reader from the JAXP factory.
   *
   * <p>In order to do its job, a ResolvingXMLReader must in fact be
   * a filter. So the only difference between this code and the filter
   * code is that the constructor builds a new reader.</p>
   */
  public ResolvingXMLReader() {
    super();
    SAXParserFactory spf = catalogManager.useServicesMechanism() ?
                    SAXParserFactory.newInstance() : new SAXParserFactoryImpl();
    spf.setNamespaceAware(namespaceAware);
    spf.setValidating(validating);
    try {
      SAXParser parser = spf.newSAXParser();
      setParent(parser.getXMLReader());
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Construct a new reader from the JAXP factory.
   *
   * <p>In order to do its job, a ResolvingXMLReader must in fact be
   * a filter. So the only difference between this code and the filter
   * code is that the constructor builds a new reader.</p>
   */
  public ResolvingXMLReader(CatalogManager manager) {
    super(manager);
    SAXParserFactory spf = catalogManager.useServicesMechanism() ?
                    SAXParserFactory.newInstance() : new SAXParserFactoryImpl();
    spf.setNamespaceAware(namespaceAware);
    spf.setValidating(validating);
    try {
      SAXParser parser = spf.newSAXParser();
      setParent(parser.getXMLReader());
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
