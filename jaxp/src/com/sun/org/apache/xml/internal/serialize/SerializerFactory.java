package com.sun.org.apache.xml.internal.serialize;

import com.sun.org.apache.xerces.internal.utils.ObjectFactory;
import com.sun.org.apache.xerces.internal.utils.SecuritySupport;
import java.io.OutputStream;
import java.io.Writer;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.StringTokenizer;

public abstract class SerializerFactory
{

    public static final String FactoriesProperty = "com.sun.org.apache.xml.internal.serialize.factories";

    private static Hashtable  _factories = new Hashtable();

    static
    {
        SerializerFactory factory;
        String            list;
        StringTokenizer   token;
        String            className;

        // The default factories are always registered first,
        // any factory specified in the properties file and supporting
        // the same method will override the default factory.
        factory =  new SerializerFactoryImpl( Method.XML );
        registerSerializerFactory( factory );
        factory =  new SerializerFactoryImpl( Method.HTML );
        registerSerializerFactory( factory );
        factory =  new SerializerFactoryImpl( Method.XHTML );
        registerSerializerFactory( factory );
        factory =  new SerializerFactoryImpl( Method.TEXT );
        registerSerializerFactory( factory );

        list = SecuritySupport.getSystemProperty( FactoriesProperty );
        if ( list != null ) {
            token = new StringTokenizer( list, " ;,:" );
            while ( token.hasMoreTokens() ) {
                className = token.nextToken();
                try {
                    factory = (SerializerFactory) ObjectFactory.newInstance( className, true);
                    if ( _factories.containsKey( factory.getSupportedMethod() ) )
                        _factories.put( factory.getSupportedMethod(), factory );
                } catch ( Exception except ) { }
            }
        }
    }


    /**
     * Register a serializer factory, keyed by the given
     * method string.
     */
    public static void registerSerializerFactory( SerializerFactory factory )
    {
        String method;

        synchronized ( _factories ) {
            method = factory.getSupportedMethod();
            _factories.put( method, factory );
        }
    }


    /**
     * Register a serializer factory, keyed by the given
     * method string.
     */
    public static SerializerFactory getSerializerFactory( String method )
    {
        return (SerializerFactory) _factories.get( method );
    }


    /**
     * Returns the method supported by this factory and used to register
     * the factory. This call is required so factories can be added from
     * a properties file by knowing only the class name. This method is
     * protected, it is only required by this class but must be implemented
     * in derived classes.
     */
    protected abstract String getSupportedMethod();


    /**
     * Create a new serializer based on the {@link OutputFormat}.
     * If this method is used to create the serializer, the {@link
     * Serializer#setOutputByteStream} or {@link Serializer#setOutputCharStream}
     * methods must be called before serializing a document.
     */
    public abstract Serializer makeSerializer(OutputFormat format);


    /**
     * Create a new serializer, based on the {@link OutputFormat} and
     * using the writer as the output character stream.  If this
     * method is used, the encoding property will be ignored.
     */
    public abstract Serializer makeSerializer( Writer writer,
                                               OutputFormat format );


    /**
     * Create a new serializer, based on the {@link OutputFormat} and
     * using the output byte stream and the encoding specified in the
     * output format.
     *
     * @throws UnsupportedEncodingException The specified encoding is
     *   not supported
     */
    public abstract Serializer makeSerializer( OutputStream output,
                                               OutputFormat format )
        throws UnsupportedEncodingException;


}
