package jdk.internal.util.xml;

import java.util.Properties;
import java.util.InvalidPropertiesFormatException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

import sun.util.spi.XmlPropertiesProvider;

/**
 * A {@code XmlPropertiesProvider} implementation that uses the UKit XML parser.
 */
public class BasicXmlPropertiesProvider extends XmlPropertiesProvider {

    public BasicXmlPropertiesProvider() { }

    @Override
    public void load(Properties props, InputStream in)
        throws IOException, InvalidPropertiesFormatException
    {
        PropertiesDefaultHandler handler = new PropertiesDefaultHandler();
        handler.load(props, in);
    }

    @Override
    public void store(Properties props, OutputStream os, String comment,
                      String encoding)
        throws IOException
    {
        PropertiesDefaultHandler handler = new PropertiesDefaultHandler();
        handler.store(props, os, comment, encoding);
    }
}
