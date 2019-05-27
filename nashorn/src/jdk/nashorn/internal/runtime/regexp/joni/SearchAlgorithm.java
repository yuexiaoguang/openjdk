package jdk.nashorn.internal.runtime.regexp.joni;

public abstract class SearchAlgorithm {

    public abstract String getName();
    public abstract int search(Regex regex, char[] text, int textP, int textEnd, int textRange);
    public abstract int searchBackward(Regex regex, char[] text, int textP, int adjustText, int textEnd, int textStart, int s_, int range_);


    public static final SearchAlgorithm NONE = new SearchAlgorithm() {

        @Override
        public final String getName() {
            return "NONE";
        }

        @Override
        public final int search(Regex regex, char[] text, int textP, int textEnd, int textRange) {
            return textP;
        }

        @Override
        public final int searchBackward(Regex regex, char[] text, int textP, int adjustText, int textEnd, int textStart, int s_, int range_) {
            return textP;
        }

    };

    public static final SearchAlgorithm SLOW = new SearchAlgorithm() {

        @Override
        public final String getName() {
            return "EXACT";
        }

        @Override
        public final int search(Regex regex, char[] text, int textP, int textEnd, int textRange) {
            char[] target = regex.exact;
            int targetP = regex.exactP;
            int targetEnd = regex.exactEnd;


            int end = textEnd;
            end -= targetEnd - targetP - 1;

            if (end > textRange) end = textRange;

            int s = textP;

            while (s < end) {
                if (text[s] == target[targetP]) {
                    int p = s + 1;
                    int t = targetP + 1;
                    while (t < targetEnd) {
                        if (target[t] != text[p++]) break;
                        t++;
                    }

                    if (t == targetEnd) return s;
                }
                s++;
            }

            return -1;
        }

        @Override
        public final int searchBackward(Regex regex, char[] text, int textP, int adjustText, int textEnd, int textStart, int s_, int range_) {
            char[] target = regex.exact;
            int targetP = regex.exactP;
            int targetEnd = regex.exactEnd;

            int s = textEnd;
            s -= targetEnd - targetP;

            if (s > textStart) {
                s = textStart;
            }

            while (s >= textP) {
                if (text[s] == target[targetP]) {
                    int p = s + 1;
                    int t = targetP + 1;
                    while (t < targetEnd) {
                        if (target[t] != text[p++]) break;
                        t++;
                    }
                    if (t == targetEnd) return s;
                }
                // s = enc.prevCharHead or s = s <= adjustText ? -1 : s - 1;
                s--;
            }
            return -1;
        }
    };

    public static final class SLOW_IC extends SearchAlgorithm {
        private final int caseFoldFlag;

        public SLOW_IC(Regex regex) {
            this.caseFoldFlag = regex.caseFoldFlag;
        }

        @Override
        public final String getName() {
            return "EXACT_IC";
        }

        @Override
        public final int search(Regex regex, char[] text, int textP, int textEnd, int textRange) {
            char[] target = regex.exact;
            int targetP = regex.exactP;
            int targetEnd = regex.exactEnd;

            int end = textEnd;
            end -= targetEnd - targetP - 1;

            if (end > textRange) end = textRange;
            int s = textP;

            while (s < end) {
                if (lowerCaseMatch(target, targetP, targetEnd, text, s, textEnd)) return s;
                s++;
            }
            return -1;
        }

        @Override
        public final int searchBackward(Regex regex, char[] text, int textP, int adjustText, int textEnd, int textStart, int s_, int range_) {
            char[] target = regex.exact;
            int targetP = regex.exactP;
            int targetEnd = regex.exactEnd;

            int s = textEnd;
            s -= targetEnd - targetP;

            if (s > textStart) {
                s = textStart;
            }

            while (s >= textP) {
                if (lowerCaseMatch(target, targetP, targetEnd, text, s, textEnd)) return s;
                s = EncodingHelper.prevCharHead(adjustText, s);
            }
            return -1;
        }

        private boolean lowerCaseMatch(char[] t, int tP, int tEnd,
                                       char[] chars, int p, int end) {

            while (tP < tEnd) {
                if (t[tP++] != Character.toLowerCase(chars[p++])) return false;
            }
            return true;
        }
    }

    public static final SearchAlgorithm BM = new SearchAlgorithm() {

        @Override
        public final String getName() {
            return "EXACT_BM";
        }

        @Override
        public final int search(Regex regex, char[] text, int textP, int textEnd, int textRange) {
            char[] target = regex.exact;
            int targetP = regex.exactP;
            int targetEnd = regex.exactEnd;

            int end = textRange + (targetEnd - targetP) - 1;
            if (end > textEnd) end = textEnd;

            int tail = targetEnd - 1;
            int s = textP + (targetEnd - targetP) - 1;

            if (regex.intMap == null) {
                while (s < end) {
                    int p = s;
                    int t = tail;

                    while (text[p] == target[t]) {
                        if (t == targetP) return p;
                        p--; t--;
                    }

                    s += regex.map[text[s] & 0xff];
                }
            } else { /* see int_map[] */
                while (s < end) {
                    int p = s;
                    int t = tail;

                    while (text[p] == target[t]) {
                        if (t == targetP) return p;
                        p--; t--;
                    }

                    s += regex.intMap[text[s] & 0xff];
                }
            }
            return -1;
        }

        private static final int BM_BACKWARD_SEARCH_LENGTH_THRESHOLD = 100;

        @Override
        public final int searchBackward(Regex regex, char[] text, int textP, int adjustText, int textEnd, int textStart, int s_, int range_) {
            char[] target = regex.exact;
            int targetP = regex.exactP;
            int targetEnd = regex.exactEnd;

            if (regex.intMapBackward == null) {
                if (s_ - range_ < BM_BACKWARD_SEARCH_LENGTH_THRESHOLD) {
                    // goto exact_method;
                    return SLOW.searchBackward(regex, text, textP, adjustText, textEnd, textStart, s_, range_);
                }
                setBmBackwardSkip(regex, target, targetP, targetEnd);
            }

            int s = textEnd - (targetEnd - targetP);

            if (textStart < s) {
                s = textStart;
            }

            while (s >= textP) {
                int p = s;
                int t = targetP;
                while (t < targetEnd && text[p] == target[t]) {
                    p++; t++;
                }
                if (t == targetEnd) return s;

                s -= regex.intMapBackward[text[s] & 0xff];
            }
            return -1;
        }


        private void setBmBackwardSkip(Regex regex, char[] chars, int p, int end) {
            int[] skip;
            if (regex.intMapBackward == null) {
                skip = new int[Config.CHAR_TABLE_SIZE];
                regex.intMapBackward = skip;
            } else {
                skip = regex.intMapBackward;
            }

            int len = end - p;

            for (int i=0; i<Config.CHAR_TABLE_SIZE; i++) skip[i] = len;
            for (int i=len-1; i>0; i--) skip[chars[i] & 0xff] = i;
        }
    };

    public static final SearchAlgorithm MAP = new SearchAlgorithm() {

        @Override
        public final String getName() {
            return "MAP";
        }

        @Override
        public final int search(Regex regex, char[] text, int textP, int textEnd, int textRange) {
            byte[] map = regex.map;
            int s = textP;

            while (s < textRange) {
                if (text[s] > 0xff || map[text[s]] != 0) return s;
                s++;
            }
            return -1;
        }

        @Override
        public final int searchBackward(Regex regex, char[] text, int textP, int adjustText, int textEnd, int textStart, int s_, int range_) {
            byte[] map = regex.map;
            int s = textStart;

            if (s >= textEnd) s = textEnd - 1;
            while (s >= textP) {
                if (text[s] > 0xff || map[text[s]] != 0) return s;
                s--;
            }
            return -1;
        }
    };

}