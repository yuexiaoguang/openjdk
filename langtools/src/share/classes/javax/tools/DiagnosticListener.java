package javax.tools;

/**
 * Interface for receiving diagnostics from tools.
 *
 * @param <S> the type of source objects used by diagnostics received
 * by this listener
 */
public interface DiagnosticListener<S> {
    /**
     * Invoked when a problem is found.
     *
     * @param diagnostic a diagnostic representing the problem that
     * was found
     * @throws NullPointerException if the diagnostic argument is
     * {@code null} and the implementation cannot handle {@code null}
     * arguments
     */
    void report(Diagnostic<? extends S> diagnostic);
}
