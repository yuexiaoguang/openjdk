package jdk.nashorn.internal.runtime.linker;

import jdk.nashorn.internal.runtime.ECMAErrors;
import jdk.nashorn.internal.runtime.ECMAException;

/**
 * A result of generating an adapter for a class. A tuple of an outcome and - in case of an error outcome - a list of
 * classes that caused the error.
 */
final class AdaptationResult {
    /**
     * Contains various outcomes for attempting to generate an adapter class. These are stored in AdapterInfo instances.
     * We have a successful outcome (adapter class was generated) and four possible error outcomes: superclass is final,
     * superclass is not public, superclass has no public or protected constructor, more than one superclass was
     * specified. We don't throw exceptions when we try to generate the adapter, but rather just record these error
     * conditions as they are still useful as partial outcomes, as Nashorn's linker can still successfully check whether
     * the class can be autoconverted from a script function even when it is not possible to generate an adapter for it.
     */
    enum Outcome {
        SUCCESS,
        ERROR_FINAL_CLASS,
        ERROR_NON_PUBLIC_CLASS,
        ERROR_NO_ACCESSIBLE_CONSTRUCTOR,
        ERROR_MULTIPLE_SUPERCLASSES,
        ERROR_NO_COMMON_LOADER,
        ERROR_FINAL_FINALIZER
    }

    static final AdaptationResult SUCCESSFUL_RESULT = new AdaptationResult(Outcome.SUCCESS, "");

    private final Outcome outcome;
    private final String classList;

    AdaptationResult(final Outcome outcome, final String classList) {
        this.outcome = outcome;
        this.classList = classList;
    }

    Outcome getOutcome() {
        return outcome;
    }

    String getClassList() {
        return classList;
    }

    ECMAException typeError() {
        return ECMAErrors.typeError("extend." + outcome, classList);
    }
}
