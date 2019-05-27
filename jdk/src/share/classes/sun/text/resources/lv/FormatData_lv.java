package sun.text.resources.lv;

import sun.util.resources.ParallelListResourceBundle;

public class FormatData_lv extends ParallelListResourceBundle {
    /**
     * Overrides ParallelListResourceBundle
     */
    protected final Object[][] getContents() {
        return new Object[][] {
            { "MonthNames",
                new String[] {
                    "janv\u0101ris", // january
                    "febru\u0101ris", // february
                    "marts", // march
                    "apr\u012blis", // april
                    "maijs", // may
                    "j\u016bnijs", // june
                    "j\u016blijs", // july
                    "augusts", // august
                    "septembris", // september
                    "oktobris", // october
                    "novembris", // november
                    "decembris", // december
                    "" // month 13 if applicable
                }
            },
            { "MonthAbbreviations",
                new String[] {
                    "janv.",
                    "febr.",
                    "marts",
                    "apr.",
                    "maijs",
                    "j\u016bn.",
                    "j\u016bl.",
                    "aug.",
                    "sept.",
                    "okt.",
                    "nov.",
                    "dec.",
                    "",
                }
            },
            { "standalone.MonthAbbreviations",
                new String[] {
                    "Jan", // abb january
                    "Feb", // abb february
                    "Mar", // abb march
                    "Apr", // abb april
                    "Maijs", // abb may
                    "J\u016bn", // abb june
                    "J\u016bl", // abb july
                    "Aug", // abb august
                    "Sep", // abb september
                    "Okt", // abb october
                    "Nov", // abb november
                    "Dec", // abb december
                    "" // abb month 13 if applicable
                }
            },
            { "MonthNarrows",
                new String[] {
                    "J",
                    "F",
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
                    "sv\u0113tdiena", // Sunday
                    "pirmdiena", // Monday
                    "otrdiena", // Tuesday
                    "tre\u0161diena", // Wednesday
                    "ceturtdiena", // Thursday
                    "piektdiena", // Friday
                    "sestdiena" // Saturday
                }
            },
            { "DayAbbreviations",
                new String[] {
                    "Sv", // abb Sunday
                    "P", // abb Monday
                    "O", // abb Tuesday
                    "T", // abb Wednesday
                    "C", // abb Thursday
                    "Pk", // abb Friday
                    "S" // abb Saturday
                }
            },
            { "DayNarrows",
                new String[] {
                    "S",
                    "P",
                    "O",
                    "T",
                    "C",
                    "P",
                    "S",
                }
            },
            { "Eras",
                new String[] { // era strings
                    "pm\u0113",
                    "m\u0113"
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
                    "HH:mm:ss z", // full time pattern
                    "HH:mm:ss z", // long time pattern
                    "HH:mm:ss", // medium time pattern
                    "HH:mm", // short time pattern
                }
            },
            { "DatePatterns",
                new String[] {
                    "EEEE, yyyy, d MMMM", // full date pattern
                    "EEEE, yyyy, d MMMM", // long date pattern
                    "yyyy.d.M", // medium date pattern
                    "yy.d.M", // short date pattern
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
