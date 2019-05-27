package javax.xml.stream.events;

/**
 * An interface for comment events
 *
 * @since 1.6
 */
public interface Comment extends XMLEvent {

  /**
   * Return the string data of the comment, returns empty string if it
   * does not exist
   */
  public String getText();
}
