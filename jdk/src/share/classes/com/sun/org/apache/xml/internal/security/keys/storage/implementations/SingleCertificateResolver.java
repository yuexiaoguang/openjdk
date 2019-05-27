package com.sun.org.apache.xml.internal.security.keys.storage.implementations;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.sun.org.apache.xml.internal.security.keys.storage.StorageResolverSpi;

/**
 * This {@link StorageResolverSpi} makes a single {@link X509Certificate}
 * available to the {@link com.sun.org.apache.xml.internal.security.keys.storage.StorageResolver}.
 */
public class SingleCertificateResolver extends StorageResolverSpi {

    /** Field certificate */
    private X509Certificate certificate = null;

    /**
     * @param x509cert the single {@link X509Certificate}
     */
    public SingleCertificateResolver(X509Certificate x509cert) {
        this.certificate = x509cert;
    }

    /** @inheritDoc */
    public Iterator<Certificate> getIterator() {
        return new InternalIterator(this.certificate);
    }

    /**
     * Class InternalIterator
     */
    static class InternalIterator implements Iterator<Certificate> {

        /** Field alreadyReturned */
        boolean alreadyReturned = false;

        /** Field certificate */
        X509Certificate certificate = null;

        /**
         * Constructor InternalIterator
         *
         * @param x509cert
         */
        public InternalIterator(X509Certificate x509cert) {
            this.certificate = x509cert;
        }

        /** @inheritDoc */
        public boolean hasNext() {
            return !this.alreadyReturned;
        }

        /** @inheritDoc */
        public Certificate next() {
            if (this.alreadyReturned) {
                throw new NoSuchElementException();
            }
            this.alreadyReturned = true;
            return this.certificate;
        }

        /**
         * Method remove
         */
        public void remove() {
            throw new UnsupportedOperationException("Can't remove keys from KeyStore");
        }
    }
}
