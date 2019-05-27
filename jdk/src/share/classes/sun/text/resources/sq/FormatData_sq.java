package sun.text.resources.sq;

import sun.util.resources.ParallelListResourceBundle;

public class FormatData_sq extends ParallelListResourceBundle {
    /**
     * Overrides ParallelListResourceBundle
     */
    protected final Object[][] getContents() {
        return new Object[][] {
            { "MonthNames",
                new String[] {
                    "janar", // january
                    "shkurt", // february
                    "mars", // march
                    "prill", // april
                    "maj", // may
                    "qershor", // june
                    "korrik", // july
                    "gusht", // august
                    "shtator", // september
                    "tetor", // october
                    "n\u00ebntor", // november
                    "dhjetor", // december
                    "" // month 13 if applicable
                }
            },
            { "MonthAbbreviations",
                new String[] {
                    "Jan", // abb january
                    "Shk", // abb february
                    "Mar", // abb march
                    "Pri", // abb april
                    "Maj", // abb may
                    "Qer", // abb june
                    "Kor", // abb july
                    "Gsh", // abb august
                    "Sht", // abb september
                    "Tet", // abb october
                    "N\u00ebn", // abb november
                    "Dhj", // abb december
                    "" // abb month 13 if applicable
                }
            },
            { "MonthNarrows",
                new String[] {
                    "J",
                    "S",
                    "M",
                    "P",
                    "M",
                    "Q",
                    "K",
                    "G",
                    "S",
                    "T",
                    "N",
                    "D",
                    "",
                }
            },
            { "DayNames",
                new String[] {
                    "e diel", // Sunday
                    "e h\u00ebn\u00eb", // Monday
                    "e mart\u00eb", // Tuesday
                    "e m\u00ebrkur\u00eb", // Wednesday
                    "e enjte", // Thursday
                    "e premte", // Friday
                    "e shtun\u00eb" // Saturday
                }
            },
            { "DayAbbreviations",
                new String[] {
                    "Die", // abb Sunday
                    "H\u00ebn", // abb Monday
                    "Mar", // abb Tuesday
                    "M\u00ebr", // abb Wednesday
                    "Enj", // abb Thursday
                    "Pre", // abb Friday
                    "Sht" // abb Saturday
                }
            },
            { "DayNarrows",
                new String[] {
                    "D",
                    "H",
                    "M",
                    "M",
                    "E",
                    "P",
                    "S",
                }
            },
            { "AmPmMarkers",
                new String[] {
                    "PD", // am marker
                    "MD" // pm marker
                }
            },
            { "Eras",
                new String[] { // era strings
                    "p.e.r.",
                    "n.e.r."
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
                    "h.mm.ss.a z", // full time pattern
                    "h.mm.ss.a z", // long time pattern
                    "h:mm:ss.a", // medium time pattern
                    "h.mm.a", // short time pattern
                }
            },
            { "DatePatterns",
                new String[] {
                    "yyyy-MM-dd", // full date pattern
                    "yyyy-MM-dd", // long date pattern
                    "yyyy-MM-dd", // medium date pattern
                    "yy-MM-dd", // short date pattern
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
