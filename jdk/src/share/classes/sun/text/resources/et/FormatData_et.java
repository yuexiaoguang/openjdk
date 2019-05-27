package sun.text.resources.et;

import sun.util.resources.ParallelListResourceBundle;

public class FormatData_et extends ParallelListResourceBundle {
    /**
     * Overrides ParallelListResourceBundle
     */
    protected final Object[][] getContents() {
        return new Object[][] {
            { "MonthNames",
                new String[] {
                    "jaanuar", // january
                    "veebruar", // february
                    "m\u00e4rts", // march
                    "aprill", // april
                    "mai", // may
                    "juuni", // june
                    "juuli", // july
                    "august", // august
                    "september", // september
                    "oktoober", // october
                    "november", // november
                    "detsember", // december
                    "" // month 13 if applicable
                }
            },
            { "MonthAbbreviations",
                new String[] {
                    "jaan", // abb january
                    "veebr", // abb february
                    "m\u00e4rts", // abb march
                    "apr", // abb april
                    "mai", // abb may
                    "juuni", // abb june
                    "juuli", // abb july
                    "aug", // abb august
                    "sept", // abb september
                    "okt", // abb october
                    "nov", // abb november
                    "dets", // abb december
                    "" // abb month 13 if applicable
                }
            },
            { "MonthNarrows",
                new String[] {
                    "J",
                    "V",
                    "M",
                    "A",
                    "M",
                    "J",
                    "J",
                    "A",
                    "S",
                    "O",
                    "N",
                    "D",
                    "",
                }
            },
            { "DayNames",
                new String[] {
                    "p\u00fchap\u00e4ev", // Sunday
                    "esmasp\u00e4ev", // Monday
                    "teisip\u00e4ev", // Tuesday
                    "kolmap\u00e4ev", // Wednesday
                    "neljap\u00e4ev", // Thursday
                    "reede", // Friday
                    "laup\u00e4ev" // Saturday
                }
            },
            { "DayAbbreviations",
                new String[] {
                    "P", // abb Sunday
                    "E", // abb Monday
                    "T", // abb Tuesday
                    "K", // abb Wednesday
                    "N", // abb Thursday
                    "R", // abb Friday
                    "L" // abb Saturday
                }
            },
            { "DayNarrows",
                new String[] {
                    "P",
                    "E",
                    "T",
                    "K",
                    "N",
                    "R",
                    "L",
                }
            },
            { "Eras",
                new String[] { // era strings
                    "e.m.a.",
                    "m.a.j."
                }
            },
            { "short.Eras",
                new String[] {
                    "e.m.a.",
                    "m.a.j.",
                }
            },
            { "NumberElements",
                new String[] {
                    ",", // decimal separator
                    "\u00a0", // group (thousands) separator
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
                    "EEEE, d. MMMM yyyy", // full date pattern
                    "EEEE, d. MMMM yyyy. 'a'", // long date pattern
                    "d.MM.yyyy", // medium date pattern
                    "d.MM.yy", // short date pattern
                }
            },
            { "DateTimePatterns",
                new String[] {
                    "{1} {0}" // date-time pattern
                }
            },
        };
    }
}
