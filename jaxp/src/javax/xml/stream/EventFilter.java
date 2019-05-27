package javax.xml.stream;

import javax.xml.stream.events.XMLEvent;

/**
 * This interface declares a simple filter interface that one can
 * create to filter XMLEventReaders
 * @since 1.6
 */
public interface EventFilter {
  /**
   * Tests whether this event is part of this stream.  This method
   * will return true if this filter accepts this event and false
   * otherwise.
   *
   * @param event the event to test
   * @return true if this filter accepts this event, false otherwise
   */
  public boolean accept(XMLEvent event);
}
