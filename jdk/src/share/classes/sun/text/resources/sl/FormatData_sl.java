package sun.text.resources.sl;

import sun.util.resources.ParallelListResourceBundle;

public class FormatData_sl extends ParallelListResourceBundle {
    /**
     * Overrides ParallelListResourceBundle
     */
    protected final Object[][] getContents() {
        return new Object[][] {
            { "MonthNames",
                new String[] {
                    "januar", // january
                    "februar", // february
                    "marec", // march
                    "april", // april
                    "maj", // may
                    "junij", // june
                    "julij", // july
                    "avgust", // august
                    "september", // september
                    "oktober", // october
                    "november", // november
                    "december", // december
                    "" // month 13 if applicable
                }
            },
            { "MonthAbbreviations",
                new String[] {
                    "jan.",
                    "feb.",
                    "mar.",
                    "apr.",
                    "maj",
                    "jun.",
                    "jul.",
                    "avg.",
                    "sep.",
                    "okt.",
                    "nov.",
                    "dec.",
                    "",
                }
            },
            { "standalone.MonthAbbreviations",
                new String[] {
                    "jan", // abb january
                    "feb", // abb february
                    "mar", // abb march
                    "apr", // abb april
                    "maj", // abb may
                    "jun", // abb june
                    "jul", // abb july
                    "avg", // abb august
                    "sep", // abb september
                    "okt", // abb october
                    "nov", // abb november
                    "dec", // abb december
                    "" // abb month 13 if applicable
                }
            },
            { "MonthNarrows",
                new String[] {
                    "j",
                    "f",
                    "m",
                    "a",
                    "m",
                    "j",
                    "j",
                    "a",
                    "s",
                    "o",
                    "n",
                    "d",
                    "",
                }
            },
            { "DayNames",
                new String[] {
                    "Nedelja", // Sunday
                    "Ponedeljek", // Monday
                    "Torek", // Tuesday
                    "Sreda", // Wednesday
                    "\u010cetrtek", // Thursday
                    "Petek", // Friday
                    "Sobota" // Saturday
                }
            },
            { "DayAbbreviations",
                new String[] {
                    "Ned", // abb Sunday
                    "Pon", // abb Monday
                    "Tor", // abb Tuesday
                    "Sre", // abb Wednesday
                    "\u010cet", // abb Thursday
                    "Pet", // abb Friday
                    "Sob" // abb Saturday
                }
            },
            { "standalone.DayAbbreviations",
                new String[] {
                    "ned",
                    "pon",
                    "tor",
                    "sre",
                    "\u010det",
                    "pet",
                    "sob",
                }
            },
            { "DayNarrows",
                new String[] {
                    "n",
                    "p",
                    "t",
                    "s",
                    "\u010d",
                    "p",
                    "s",
                }
            },
            { "Eras",
                new String[] { // era strings
                    "pr.n.\u0161.",
                    "po Kr."
                }
            },
            { "NumberElements",
                new String[] {
                    ",", // decimal separator
                    ".", // group (thousands) separator
                    ";", // list separator
                    "%", // percent sign
                    "0", // native 0 digit
                    "#", // pattern digit
                    "-", // minus sign
                    "E", // exponential
                    "\u2030", // per mille
                    "\u221e", // infinity
                    "\ufffd" // NaN
                }
            },
            { "TimePatterns",
                new String[] {
                    "H:mm:ss z", // full time pattern
                    "H:mm:ss z", // long time pattern
                    "H:mm:ss", // medium time pattern
                    "H:mm", // short time pattern
                }
            },
            { "DatePatterns",
                new String[] {
                    "EEEE, dd. MMMM y", // full date pattern
                    "dd. MMMM y", // long date pattern
                    "d.M.yyyy", // medium date pattern
                    "d.M.y", // short date pattern
                }
            },
            { "DateTimePatterns",
                new String[] {
                    "{1} {0}" // date-time pattern
                }
            },
            { "DateTimePatternChars", "GanjkHmsSEDFwWxhKzZ" },
        };
    }
}
