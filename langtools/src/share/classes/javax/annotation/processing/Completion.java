package javax.annotation.processing;

/**
 * A suggested {@linkplain Processor#getCompletions <em>completion</em>} for an
 * annotation.  A completion is text meant to be inserted into a
 * program as part of an annotation.
 */
public interface Completion {

    /**
     * Returns the text of the suggested completion.
     * @return the text of the suggested completion.
     */
    String getValue();

    /**
     * Returns an informative message about the completion.
     * @return an informative message about the completion.
     */
    String getMessage();
}
