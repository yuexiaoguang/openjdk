package sun.misc;

/**
 * The request processor allows functors (Request instances) to be created
 * in arbitrary threads, and to be posted for execution in a non-restricted
 * thread.
 */
public class RequestProcessor implements Runnable {

    private static Queue<Request> requestQueue;
    private static Thread dispatcher;

    /**
     * Queues a Request instance for execution by the request procesor
     * thread.
     */
    public static void postRequest(Request req) {
        lazyInitialize();
        requestQueue.enqueue(req);
    }

    /**
     * Process requests as they are queued.
     */
    public void run() {
        lazyInitialize();
        while (true) {
            try {
                Request req = requestQueue.dequeue();
                try {
                    req.execute();
                } catch (Throwable t) {
                    // do nothing at the moment...maybe report an error
                    // in the future
                }
            } catch (InterruptedException e) {
                // do nothing at the present time.
            }
        }
    }


    /**
     * This method initiates the request processor thread.  It is safe
     * to call it after the thread has been started.  It provides a way for
     * clients to deliberately control the context in which the request
     * processor thread is created
     */
    public static synchronized void startProcessing() {
        if (dispatcher == null) {
            dispatcher = new Thread(new RequestProcessor(), "Request Processor");
            dispatcher.setPriority(Thread.NORM_PRIORITY + 2);
            dispatcher.start();
        }
    }


    /**
     * This method performs lazy initialization.
     */
    private static synchronized void lazyInitialize() {
        if (requestQueue == null) {
            requestQueue = new Queue<Request>();
        }
    }

}
