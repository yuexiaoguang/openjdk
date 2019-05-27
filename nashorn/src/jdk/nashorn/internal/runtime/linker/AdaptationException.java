package jdk.nashorn.internal.runtime.linker;

@SuppressWarnings("serial")
final class AdaptationException extends Exception {
    private final AdaptationResult adaptationResult;

    AdaptationException(final AdaptationResult.Outcome outcome, final String classList) {
        this.adaptationResult = new AdaptationResult(outcome, classList);
    }

    AdaptationResult getAdaptationResult() {
        return adaptationResult;
    }
}
