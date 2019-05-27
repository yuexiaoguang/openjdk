package com.sun.xml.internal.bind.v2.runtime;

import java.io.IOException;

import javax.xml.bind.annotation.XmlValue;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;

import com.sun.istack.internal.NotNull;
import com.sun.xml.internal.bind.api.AccessorException;
import com.sun.xml.internal.bind.v2.model.runtime.RuntimePropertyInfo;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.OptimizedTransducedAccessorFactory;

import org.xml.sax.SAXException;


/**
 * Responsible for converting a Java object to a lexical representation
 * and vice versa.
 *
 * <p>
 * An implementation of this interface hides how this conversion happens.
 *
 * <p>
 * {@link Transducer}s are immutable.
 */
public interface Transducer<ValueT> {

    /**
     * If this {@link Transducer} is the default transducer for the <code>ValueT</code>,
     * this method returns true.
     *
     * Used exclusively by {@link OptimizedTransducedAccessorFactory#get(RuntimePropertyInfo)}
     */
    boolean isDefault();

    /**
     * If true, this {@link Transducer} doesn't declare any namespace,
     * and therefore {@link #declareNamespace(Object, XMLSerializer)} is no-op.
     *
     * It also means that the {@link #parse(CharSequence)} method
     * won't use the context parameter.
     */
    boolean useNamespace();

    /**
     * Declares the namespace URIs used in the given value to {@code w}.
     *
     * @param o
     *      never be null.
     * @param w
     *      may be null if {@code !{@link #useNamespace()}}.
     */
    void declareNamespace( ValueT o, XMLSerializer w ) throws AccessorException;

    /**
     * Converts the given value to its lexical representation.
     *
     * @param o
     *      never be null.
     * @return
     *      always non-null valid lexical representation.
     */
    @NotNull CharSequence print(@NotNull ValueT o) throws AccessorException;

    /**
     * Converts the lexical representation to a value object.
     *
     * @param lexical
     *      never be null.
     * @throws AccessorException
     *      if the transducer is used to parse an user bean that uses {@link XmlValue},
     *      then this exception may occur when it tries to set the leaf value to the bean.
     * @throws SAXException
     *      if the lexical form is incorrect, the error should be reported
     *      and SAXException may thrown (or it can return null to recover.)
     */
    ValueT parse(CharSequence lexical) throws AccessorException, SAXException;

    /**
     * Sends the result of the {@link #print(Object)} operation
     * to one of the {@link XMLSerializer#text(String, String)} method,
     * but with the best representation of the value, not necessarily String.
     */
    void writeText(XMLSerializer w, ValueT o, String fieldName) throws IOException, SAXException, XMLStreamException, AccessorException;

    /**
     * Sends the result of the {@link #print(Object)} operation
     * to one of the {@link XMLSerializer#leafElement(Name, String, String)} method.
     * but with the best representation of the value, not necessarily String.
     */
    void writeLeafElement(XMLSerializer w, Name tagName, @NotNull ValueT o, String fieldName) throws IOException, SAXException, XMLStreamException, AccessorException;

    /**
     * Transducers implicitly work against a single XML type,
     * but sometimes (most notably {@link XMLGregorianCalendar},
     * an instance may choose different XML types.
     *
     * @return
     *      return non-null from this method allows transducers
     *      to specify the type it wants to marshal to.
     *      Most of the time this method returns null, in which case
     *      the implicitly associated type will be used.
     */
    QName getTypeName(@NotNull ValueT instance);
}
