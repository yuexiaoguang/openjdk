package sun.util.spi;

import java.util.Properties;
import java.util.InvalidPropertiesFormatException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

/**
 * Service-provider class for loading and storing {@link Properites} in XML
 * format.
 */
public abstract class XmlPropertiesProvider {

    /**
     * Initializes a new instance of this class.
     */
    protected XmlPropertiesProvider() {
        // do nothing for now
    }

    /**
     * Loads all of the properties represented by the XML document on the
     * specified input stream into a properties table.
     *
     * @param props the properties table to populate
     * @param in the input stream from which to read the XML document
     * @throws IOException if reading from the specified input stream fails
     * @throws java.io.UnsupportedEncodingException if the document's encoding
     *         declaration can be read and it specifies an encoding that is not
     *         supported
     * @throws InvalidPropertiesFormatException Data on input stream does not
     *         constitute a valid XML document with the mandated document type.
     *
     * @see Properties#loadFromXML
     */
    public abstract void load(Properties props, InputStream in)
        throws IOException, InvalidPropertiesFormatException;

    /**
     * Emits an XML document representing all of the properties in a given
     * table.
     *
     * @param props the properies to store
     * @param out the output stream on which to emit the XML document.
     * @param comment  a description of the property list, can be @{code null}
     * @param encoding the name of a supported character encoding
     *
     * @throws IOException if writing to the specified output stream fails
     * @throws java.io.UnsupportedEncodingException if the encoding is not
     *         supported by the implementation
     * @throws NullPointerException if {@code out} is null.
     * @throws ClassCastException  if this {@code Properties} object
     *         contains any keys or values that are not
     *         {@code Strings}.
     *
     * @see Properties#storeToXML
     */
    public abstract void store(Properties props, OutputStream out,
                               String comment, String encoding)
        throws IOException;
}
