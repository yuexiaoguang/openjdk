package sun.misc;

/**
 * Requests are functor objects; that is, they provide part of the mechanism
 * for deferred function application.
 */
abstract public class Request {
    /**
     * The main task of the Request object is to be exectuted from a request
     * queue.
     */
    abstract public void execute();
}
