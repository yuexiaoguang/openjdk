package jdk.nashorn.internal.runtime.regexp;

import jdk.nashorn.internal.runtime.ParserException;
import jdk.nashorn.internal.runtime.options.Options;

/**
 * Factory class for regular expressions. This class creates instances of {@link JdkRegExp}.
 * An alternative factory can be installed using the {@code nashorn.regexp.impl} system property.
 */
public class RegExpFactory {

    private final static RegExpFactory instance;

    private final static String JDK  = "jdk";
    private final static String JONI = "joni";

    static {
        final String impl = Options.getStringProperty("nashorn.regexp.impl", JONI);
        switch (impl) {
            case JONI:
                instance = new JoniRegExp.Factory();
                break;
            case JDK:
                instance = new RegExpFactory();
                break;
            default:
                instance = null;
                throw new InternalError("Unsupported RegExp factory: " + impl);
        }
    }

    /**
     * Creates a Regular expression from the given {@code pattern} and {@code flags} strings.
     *
     * @param pattern RegExp pattern string
     * @param flags   RegExp flags string
     * @return new RegExp
     * @throws ParserException if flags is invalid or pattern string has syntax error.
     */
    public RegExp compile(final String pattern, final String flags) throws ParserException {
        return new JdkRegExp(pattern, flags);
    }

    /**
     * Compile a regexp with the given {@code source} and {@code flags}.
     *
     * @param pattern RegExp pattern string
     * @param flags   flag string
     * @return new RegExp
     * @throws ParserException if invalid source or flags
     */
    public static RegExp create(final String pattern, final String flags) {
        return instance.compile(pattern,  flags);
    }

    /**
     * Validate a regexp with the given {@code source} and {@code flags}.
     *
     * @param pattern RegExp pattern string
     * @param flags  flag string
     *
     * @throws ParserException if invalid source or flags
     */
    // @SuppressWarnings({"unused"})
    public static void validate(final String pattern, final String flags) throws ParserException {
        instance.compile(pattern, flags);
    }

    /**
     * Returns true if the instance uses the JDK's {@code java.util.regex} package.
     *
     * @return true if instance uses JDK regex package
     */
    public static boolean usesJavaUtilRegex() {
        return instance != null && instance.getClass() == RegExpFactory.class;
    }
}
