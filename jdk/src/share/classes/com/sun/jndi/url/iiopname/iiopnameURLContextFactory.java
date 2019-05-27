package com.sun.jndi.url.iiopname;

import com.sun.jndi.url.iiop.iiopURLContextFactory;

/**
 * An iiopname URL context factory.
 * It just uses the iiop URL context factory but is needed here
 * so that NamingManager.getURLContext() will find it.
 */
final public class iiopnameURLContextFactory extends iiopURLContextFactory {
}
