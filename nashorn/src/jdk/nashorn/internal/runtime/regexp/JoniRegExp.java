package jdk.nashorn.internal.runtime.regexp;

import jdk.nashorn.internal.runtime.ParserException;
import jdk.nashorn.internal.runtime.regexp.joni.Matcher;
import jdk.nashorn.internal.runtime.regexp.joni.Option;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import jdk.nashorn.internal.runtime.regexp.joni.Region;
import jdk.nashorn.internal.runtime.regexp.joni.Syntax;
import jdk.nashorn.internal.runtime.regexp.joni.exception.JOniException;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Regular expression implementation based on the Joni engine from the JRuby project.
 */
public class JoniRegExp extends RegExp {

    /** Compiled Joni Regex */
    private Regex regex;

    /** Matcher */
    private RegExpMatcher matcher;

    /**
     * Construct a Regular expression from the given {@code pattern} and {@code flags} strings.
     *
     * @param pattern RegExp pattern string
     * @param flags RegExp flag string
     * @throws ParserException if flags is invalid or pattern string has syntax error.
     */
    public JoniRegExp(final String pattern, final String flags) throws ParserException {
        super(pattern, flags);

        int option = Option.SINGLELINE;

        if (this.isIgnoreCase()) {
            option |= Option.IGNORECASE;
        }
        if (this.isMultiline()) {
            option &= ~Option.SINGLELINE;
            option |= Option.NEGATE_SINGLELINE;
        }

        try {
            RegExpScanner parsed;

            try {
                parsed = RegExpScanner.scan(pattern);
            } catch (final PatternSyntaxException e) {
                // refine the exception with a better syntax error, if this
                // passes, just rethrow what we have
                Pattern.compile(pattern, 0);
                throw e;
            }

            if (parsed != null) {
                char[] javaPattern = parsed.getJavaPattern().toCharArray();
                this.regex = new Regex(javaPattern, 0, javaPattern.length, option, Syntax.JAVASCRIPT);
                this.groupsInNegativeLookahead = parsed.getGroupsInNegativeLookahead();
            }
        } catch (final PatternSyntaxException | JOniException e2) {
            throwParserException("syntax", e2.getMessage());
        }
    }

    @Override
    public RegExpMatcher match(final String input) {
        if (regex == null) {
            return null;
        }

        RegExpMatcher currentMatcher = this.matcher;

        if (currentMatcher == null || input != currentMatcher.getInput()) {
            currentMatcher = new JoniMatcher(input);
            this.matcher   = currentMatcher;
        }

        return currentMatcher;
    }

    /**
     * RegExp Factory class for Joni regexp engine.
     */
    public static class Factory extends RegExpFactory {

        @Override
        public RegExp compile(final String pattern, final String flags) throws ParserException {
            return new JoniRegExp(pattern, flags);
        }

    }

    class JoniMatcher implements RegExpMatcher {
        final String input;
        final Matcher joniMatcher;

        JoniMatcher(final String input) {
            this.input = input;
            this.joniMatcher = regex.matcher(input.toCharArray());
        }

        @Override
        public boolean search(final int start) {
            return joniMatcher.search(start, input.length(), Option.NONE) > -1;
        }

        @Override
        public String getInput() {
            return input;
        }

        @Override
        public int start() {
            return joniMatcher.getBegin();
        }

        @Override
        public int start(final int group) {
            return group == 0 ? start() : joniMatcher.getRegion().beg[group];
        }

        @Override
        public int end() {
            return joniMatcher.getEnd();
        }

        @Override
        public int end(final int group) {
            return group == 0 ? end() : joniMatcher.getRegion().end[group];
        }

        @Override
        public String group() {
            return input.substring(joniMatcher.getBegin(), joniMatcher.getEnd());
        }

        @Override
        public String group(final int group) {
            if (group == 0) {
                return group();
            }
            final Region region = joniMatcher.getRegion();
            return input.substring(region.beg[group], region.end[group]);
        }

        @Override
        public int groupCount() {
            final Region region = joniMatcher.getRegion();
            return region == null ? 0 : region.numRegs - 1;
        }
    }
}