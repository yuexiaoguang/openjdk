package com.sun.xml.internal.org.jvnet.fastinfoset;

/**
 * Application data that can be associated with a vocabulary.
 * <p>
 * The application will implement this inteface and provide
 * application specific functionality related to a vocabulary.
 */
public interface VocabularyApplicationData {
    /**
     *  Clear the vocabulary  application data.
     *  <p>
     *  This method will be invoked when a parser or serializer clears
     *  the vocabulary.
     */
    public void clear();
}
