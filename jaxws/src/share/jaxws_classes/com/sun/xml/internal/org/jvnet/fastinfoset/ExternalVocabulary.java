package com.sun.xml.internal.org.jvnet.fastinfoset;

/**
 * An external vocabulary.
 * <p>
 * An external vocabulary has a URI that refers to a vocabulary.
 */
public class ExternalVocabulary {

    /**
     * A URI that refers to the external vocabulary.
     */
    public final String URI;

    /**
     * The vocabulary that is refered to by the URI.
     */
    public final Vocabulary vocabulary;

    public ExternalVocabulary(String URI, Vocabulary vocabulary) {
        if (URI == null || vocabulary == null) {
            throw new IllegalArgumentException();
        }

        this.URI = URI;
        this.vocabulary = vocabulary;
    }
}
