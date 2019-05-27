package com.sun.jndi.ldap.pool;

import javax.naming.NamingException;

/**
 * Represents a factory that creates PooledConnection.
 *
 * The user of the connection pool should provide an implementation of this
 * interface and pass it to the Pool.getPooledConnection() method.
 * The implementation of the factory should contain all the information
 * necessary to create a PooledConnection.
 */
public interface PooledConnectionFactory {
    /**
     * Creates a pooled connection.
     * @param pcb callback responsible for removing and releasing the pooled
     * connection from the pool.
     */
    public abstract PooledConnection createPooledConnection(PoolCallback pcb)
        throws NamingException;
};
