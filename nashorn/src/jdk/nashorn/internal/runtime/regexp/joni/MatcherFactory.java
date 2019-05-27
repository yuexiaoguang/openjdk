package jdk.nashorn.internal.runtime.regexp.joni;

public abstract class MatcherFactory {
    public abstract Matcher create(Regex regex, char[] chars, int p, int end);

    static final MatcherFactory DEFAULT = new MatcherFactory() {
        @Override
        public Matcher create(Regex regex, char[] chars, int p, int end) {
            return new ByteCodeMachine(regex, chars, p, end);
        }
    };
}
