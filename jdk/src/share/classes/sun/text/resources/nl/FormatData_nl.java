package sun.text.resources.nl;

import sun.util.resources.ParallelListResourceBundle;

public class FormatData_nl extends ParallelListResourceBundle {
    /**
     * Overrides ParallelListResourceBundle
     */
    protected final Object[][] getContents() {
        return new Object[][] {
            { "MonthNames",
                new String[] {
                    "januari", // january
                    "februari", // february
                    "maart", // march
                    "april", // april
                    "mei", // may
                    "juni", // june
                    "juli", // july
                    "augustus", // august
                    "september", // september
                    "oktober", // october
                    "november", // november
                    "december", // december
                    "" // month 13 if applicable
                }
            },
            { "MonthAbbreviations",
                new String[] {
                    "jan", // abb january
                    "feb", // abb february
                    "mrt", // abb march
                    "apr", // abb april
                    "mei", // abb may
                    "jun", // abb june
                    "jul", // abb july
                    "aug", // abb august
                    "sep", // abb september
                    "okt", // abb october
                    "nov", // abb november
                    "dec", // abb december
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
                    "zondag", // Sunday
                    "maandag", // Monday
                    "dinsdag", // Tuesday
                    "woensdag", // Wednesday
                    "donderdag", // Thursday
                    "vrijdag", // Friday
                    "zaterdag" // Saturday
                }
            },
            { "DayAbbreviations",
                new String[] {
                    "zo", // abb Sunday
                    "ma", // abb Monday
                    "di", // abb Tuesday
                    "wo", // abb Wednesday
                    "do", // abb Thursday
                    "vr", // abb Friday
                    "za" // abb Saturday
                }
            },
            { "DayNarrows",
                new String[] {
                    "Z",
                    "M",
                    "D",
                    "W",
                    "D",
                    "V",
                    "Z",
                }
            },
            { "Eras",
                new String[] { // era strings for GregorianCalendar
                    "v. Chr.",
                    "n. Chr."
                }
            },
            { "NumberElements",
                new String[] {
                    ",", // decimal separator
                    ".", // group (thousandsnds) separator
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
                    "H:mm:ss' uur' z", // full time pattern
                    "H:mm:ss z", // long time pattern
                    "H:mm:ss", // medium time pattern
                    "H:mm", // short time pattern
                }
            },
            { "DatePatterns",
                new String[] {
                    "EEEE d MMMM yyyy", // full date pattern
                    "d MMMM yyyy", // long date pattern
                    "d-MMM-yyyy", // medium date pattern
                    "d-M-yy", // short date pattern
                }
            },
            { "DateTimePatterns",
                new String[] {
                    "{1} {0}" // date-time pattern
                }
            },
            { "DateTimePatternChars", "GyMdkHmsSEDFwWahKzZ" },
        };
    }
}
