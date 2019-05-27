package com.sun.org.apache.xml.internal.security.encryption;

import com.sun.org.apache.xml.internal.security.c14n.Canonicalizer;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Converts <code>String</code>s into <code>Node</code>s and visa versa.
 */
public interface Serializer {

    /**
     * Set the Canonicalizer object to use.
     */
    void setCanonicalizer(Canonicalizer canon);

    /**
     * Returns a <code>byte[]</code> representation of the specified
     * <code>Element</code>.
     *
     * @param element the <code>Element</code> to serialize.
     * @return the <code>byte[]</code> representation of the serilaized
     *   <code>Element</code>.
     * @throws Exception
     */
    byte[] serializeToByteArray(Element element) throws Exception;

    /**
     * Returns a <code>byte[]</code> representation of the specified
     * <code>NodeList</code>.
     *
     * @param content the <code>NodeList</code> to serialize.
     * @return the <code>byte[]</code> representation of the serialized
     *   <code>NodeList</code>.
     * @throws Exception
     */
    byte[] serializeToByteArray(NodeList content) throws Exception;

    /**
     * Use the Canonicalizer to serialize the node
     * @param node
     * @return the (byte[]) canonicalization of the node
     * @throws Exception
     */
    byte[] canonSerializeToByteArray(Node node) throws Exception;

    /**
     * @param source
     * @param ctx
     * @return the Node resulting from the parse of the source
     * @throws XMLEncryptionException
     */
    Node deserialize(byte[] source, Node ctx) throws XMLEncryptionException;
}
