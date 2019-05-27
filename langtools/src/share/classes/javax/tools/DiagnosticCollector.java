package javax.tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Provides an easy way to collect diagnostics in a list.
 *
 * @param <S> the type of source objects used by diagnostics received
 * by this object
 */
public final class DiagnosticCollector<S> implements DiagnosticListener<S> {
    private List<Diagnostic<? extends S>> diagnostics =
        Collections.synchronizedList(new ArrayList<Diagnostic<? extends S>>());

    public void report(Diagnostic<? extends S> diagnostic) {
        diagnostic.getClass(); // null check
        diagnostics.add(diagnostic);
    }

    /**
     * Gets a list view of diagnostics collected by this object.
     *
     * @return a list view of diagnostics
     */
    public List<Diagnostic<? extends S>> getDiagnostics() {
        return Collections.unmodifiableList(diagnostics);
    }
}