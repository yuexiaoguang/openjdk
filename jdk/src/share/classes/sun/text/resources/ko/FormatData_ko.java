package sun.text.resources.ko;

import sun.util.resources.ParallelListResourceBundle;

public class FormatData_ko extends ParallelListResourceBundle {
    /**
     * Overrides ParallelListResourceBundle
     */
    @Override
    protected final Object[][] getContents() {
        final String[] rocEras = {
            "\uc911\ud654\ubbfc\uad6d\uc804",
            "\uc911\ud654\ubbfc\uad6d",
        };
        return new Object[][] {
            { "MonthNames",
                new String[] {
                    "1\uc6d4", // january
                    "2\uc6d4", // february
                    "3\uc6d4", // march
                    "4\uc6d4", // april
                    "5\uc6d4", // may
                    "6\uc6d4", // june
                    "7\uc6d4", // july
                    "8\uc6d4", // august
                    "9\uc6d4", // september
                    "10\uc6d4", // october
                    "11\uc6d4", // november
                    "12\uc6d4", // december
                    "" // month 13 if applicable
                }
            },
            { "MonthAbbreviations",
                new String[] {
                    "1\uc6d4", // abb january
                    "2\uc6d4", // abb february
                    "3\uc6d4", // abb march
                    "4\uc6d4", // abb april
                    "5\uc6d4", // abb may
                    "6\uc6d4", // abb june
                    "7\uc6d4", // abb july
                    "8\uc6d4", // abb august
                    "9\uc6d4", // abb september
                    "10\uc6d4", // abb october
                    "11\uc6d4", // abb november
                    "12\uc6d4", // abb december
                    "" // abb month 13 if applicable
                }
            },
            { "MonthNarrows",
                new String[] {
                    "1\uc6d4",
                    "2\uc6d4",
                    "3\uc6d4",
                    "4\uc6d4",
                    "5\uc6d4",
                    "6\uc6d4",
                    "7\uc6d4",
                    "8\uc6d4",
                    "9\uc6d4",
                    "10\uc6d4",
                    "11\uc6d4",
                    "12\uc6d4",
                    "",
                }
            },
            { "DayNames",
                new String[] {
                    "\uc77c\uc694\uc77c", // Sunday
                    "\uc6d4\uc694\uc77c", // Monday
                    "\ud654\uc694\uc77c", // Tuesday
                    "\uc218\uc694\uc77c", // Wednesday
                    "\ubaa9\uc694\uc77c", // Thursday
                    "\uae08\uc694\uc77c", // Friday
                    "\ud1a0\uc694\uc77c" // Saturday
                }
            },
            { "DayAbbreviations",
                new String[] {
                    "\uc77c", // abb Sunday
                    "\uc6d4", // abb Monday
                    "\ud654", // abb Tuesday
                    "\uc218", // abb Wednesday
                    "\ubaa9", // abb Thursday
                    "\uae08", // abb Friday
                    "\ud1a0" // abb Saturday
                }
            },
            { "DayNarrows",
                new String[] {
                    "\uc77c",
                    "\uc6d4",
                    "\ud654",
                    "\uc218",
                    "\ubaa9",
                    "\uae08",
                    "\ud1a0",
                }
            },
            { "Eras",
                new String[] {
                    "\uae30\uc6d0\uc804",
                    "\uc11c\uae30",
                }
            },
            { "buddhist.Eras",
                new String[] {
                    "BC",
                    "\ubd88\uae30",
                }
            },
            { "japanese.Eras",
                new String[] {
                    "\uc11c\uae30",
                    "\uba54\uc774\uc9c0",
                    "\ub2e4\uc774\uc1fc",
                    "\uc1fc\uc640",
                    "\ud5e4\uc774\uc138\uc774",
                }
            },
            { "AmPmMarkers",
                new String[] {
                    "\uc624\uc804", // am marker
                    "\uc624\ud6c4" // pm marker
                }
            },
            { "TimePatterns",
                new String[] {
                    "a h'\uc2dc' mm'\ubd84' ss'\ucd08' z", // full time pattern
                    "a h'\uc2dc' mm'\ubd84' ss'\ucd08'", // long time pattern
                    "a h:mm:ss", // medium time pattern
                    "a h:mm", // short time pattern
                }
            },
            { "DatePatterns",
                new String[] {
                    "yyyy'\ub144' M'\uc6d4' d'\uc77c' EEEE", // full date pattern
                    "yyyy'\ub144' M'\uc6d4' d'\uc77c' '('EE')'", // long date pattern
                    "yyyy. M. d", // medium date pattern
                    "yy. M. d", // short date pattern
                }
            },
            { "DateTimePatterns",
                new String[] {
                    "{1} {0}" // date-time pattern
                }
            },
            { "buddhist.DatePatterns",
                new String[] {
                    "GGGG y\ub144 M\uc6d4 d\uc77c EEEE",
                    "GGGG y\ub144 M\uc6d4 d\uc77c",
                    "GGGG y. M. d",
                    "GGGG y. M. d",
                }
            },
            { "japanese.DatePatterns",
                new String[] {
                    "GGGG y\ub144 M\uc6d4 d\uc77c EEEE",
                    "GGGG y\ub144 M\uc6d4 d\uc77c",
                    "GGGG y. M. d",
                    "GGGG y. M. d",
                }
            },
            { "DateTimePatternChars", "GyMdkHmsSEDFwWahKzZ" },
        };
    }
}
