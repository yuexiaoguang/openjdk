package com.sun.org.apache.xml.internal.utils;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Simple static utility to convert Hashtable to a Node.
 *
 * Please maintain JDK 1.1.x compatibility; no Collections!
 */
public abstract class Hashtree2Node
{

    /**
     * Convert a Hashtable into a Node tree.
     *
     * <p>The hash may have either Hashtables as values (in which
     * case we recurse) or other values, in which case we print them
     * as &lt;item> elements, with a 'key' attribute with the value
     * of the key, and the element contents as the value.</p>
     *
     * <p>If args are null we simply return without doing anything.
     * If we encounter an error, we will attempt to add an 'ERROR'
     * Element with exception info; if that doesn't work we simply
     * return without doing anything else byt printStackTrace().</p>
     *
     * @param hash to get info from (may have sub-hashtables)
     * @param name to use as parent element for appended node
     * futurework could have namespace and prefix as well
     * @param container Node to append our report to
     * @param factory Document providing createElement, etc. services
     */
    public static void appendHashToNode(Hashtable hash, String name,
            Node container, Document factory)
    {
        // Required arguments must not be null
        if ((null == container) || (null == factory) || (null == hash))
        {
            return;
        }

        // name we will provide a default value for
        String elemName = null;
        if ((null == name) || ("".equals(name)))
            elemName = "appendHashToNode";
        else
            elemName = name;

        try
        {
            Element hashNode = factory.createElement(elemName);
            container.appendChild(hashNode);

            Enumeration keys = hash.keys();
            Vector v = new Vector();

            while (keys.hasMoreElements())
            {
                Object key = keys.nextElement();
                String keyStr = key.toString();
                Object item = hash.get(key);

                if (item instanceof Hashtable)
                {
                    // Ensure a pre-order traversal; add this hashes
                    //  items before recursing to child hashes
                    // Save name and hash in two steps
                    v.addElement(keyStr);
                    v.addElement((Hashtable) item);
                }
                else
                {
                    try
                    {
                        // Add item to node
                        Element node = factory.createElement("item");
                        node.setAttribute("key", keyStr);
                        node.appendChild(factory.createTextNode((String)item));
                        hashNode.appendChild(node);
                    }
                    catch (Exception e)
                    {
                        Element node = factory.createElement("item");
                        node.setAttribute("key", keyStr);
                        node.appendChild(factory.createTextNode("ERROR: Reading " + key + " threw: " + e.toString()));
                        hashNode.appendChild(node);
                    }
                }
            }

            // Now go back and do the saved hashes
            keys = v.elements();
            while (keys.hasMoreElements())
            {
                // Retrieve name and hash in two steps
                String n = (String) keys.nextElement();
                Hashtable h = (Hashtable) keys.nextElement();

                appendHashToNode(h, n, hashNode, factory);
            }
        }
        catch (Exception e2)
        {
            // Ooops, just bail (suggestions for a safe thing
            //  to do in this case appreciated)
            e2.printStackTrace();
        }
    }
}
