package org.w3c.dom.ls;

import org.w3c.dom.Document;
import org.w3c.dom.events.Event;

/**
 *  This interface represents a load event object that signals the completion
 * of a document load.
 * <p>See also the <a href='http://www.w3.org/TR/2004/REC-DOM-Level-3-LS-20040407'>Document Object Model (DOM) Level 3 Load
and Save Specification</a>.
 */
public interface LSLoadEvent extends Event {
    /**
     * The document that finished loading.
     */
    public Document getNewDocument();

    /**
     * The input source that was parsed.
     */
    public LSInput getInput();

}
