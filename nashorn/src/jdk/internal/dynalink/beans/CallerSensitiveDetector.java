package jdk.internal.dynalink.beans;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import sun.reflect.CallerSensitive;

/**
 * Utility class that determines if a method or constructor is caller sensitive. It actually encapsulates two different
 * strategies for determining caller sensitivity; a more robust one that works if Dynalink runs as code with access
 * to {@code sun.reflect} package, and an unprivileged one that is used when Dynalink doesn't have access to that
 * package. Note that even the unprivileged strategy is ordinarily robust, but it relies on the {@code toString} method
 * of the annotation. If an attacker were to use a different annotation to spoof the string representation of the
 * {@code CallerSensitive} annotation, they could designate their own methods as caller sensitive. This however does not
 * escalate privileges, only causes Dynalink to never cache method handles for such methods, so all it would do would
 * decrease the performance in linking such methods. In the opposite case when an attacker could trick Dynalink into not
 * recognizing genuine {@code CallerSensitive} annotations, Dynalink would treat caller sensitive methods as ordinary
 * methods, and would cache them bound to a zero-privilege delegate as the caller (just what Dynalink did before it
 * could handle caller-sensitive methods). That would practically render caller-sensitive methods exposed through
 * Dynalink unusable, but again, can not lead to any privilege escalations. Therefore, even the less robust unprivileged
 * strategy is safe; the worst thing a successful attack against it can achieve is slight reduction in Dynalink-exposed
 * functionality or performance.
 */
public class CallerSensitiveDetector {

    private static final DetectionStrategy DETECTION_STRATEGY = getDetectionStrategy();

    static boolean isCallerSensitive(AccessibleObject ao) {
        return DETECTION_STRATEGY.isCallerSensitive(ao);
    }

    private static DetectionStrategy getDetectionStrategy() {
        try {
            return new PrivilegedDetectionStrategy();
        } catch(Throwable t) {
            return new UnprivilegedDetectionStrategy();
        }
    }

    private abstract static class DetectionStrategy {
        abstract boolean isCallerSensitive(AccessibleObject ao);
    }

    private static class PrivilegedDetectionStrategy extends DetectionStrategy {
        private static final Class<? extends Annotation> CALLER_SENSITIVE_ANNOTATION_CLASS = CallerSensitive.class;

        @Override
        boolean isCallerSensitive(AccessibleObject ao) {
            return ao.getAnnotation(CALLER_SENSITIVE_ANNOTATION_CLASS) != null;
        }
    }

    private static class UnprivilegedDetectionStrategy extends DetectionStrategy {
        private static final String CALLER_SENSITIVE_ANNOTATION_STRING = "@sun.reflect.CallerSensitive()";

        @Override
        boolean isCallerSensitive(AccessibleObject o) {
            for(Annotation a: o.getAnnotations()) {
                if(String.valueOf(a).equals(CALLER_SENSITIVE_ANNOTATION_STRING)) {
                    return true;
                }
            }
            return false;
        }
    }
}
