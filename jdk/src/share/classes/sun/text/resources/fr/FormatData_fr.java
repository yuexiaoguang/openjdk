package sun.text.resources.fr;

import sun.util.resources.ParallelListResourceBundle;

public class FormatData_fr extends ParallelListResourceBundle {
    /**
     * Overrides ParallelListResourceBundle
     */
    protected final Object[][] getContents() {
        return new Object[][] {
            { "MonthNames",
                new String[] {
                    "janvier", // january
                    "f\u00e9vrier", // february
                    "mars", // march
                    "avril", // april
                    "mai", // may
                    "juin", // june
                    "juillet", // july
                    "ao\u00fbt", // august
                    "septembre", // september
                    "octobre", // october
                    "novembre", // november
                    "d\u00e9cembre", // december
                    "" // month 13 if applicable
                }
            },
            { "MonthAbbreviations",
                new String[] {
                    "janv.", // abb january
                    "f\u00e9vr.", // abb february
                    "mars", // abb march
                    "avr.", // abb april
                    "mai", // abb may
                    "juin", // abb june
                    "juil.", // abb july
                    "ao\u00fbt", // abb august
                    "sept.", // abb september
                    "oct.", // abb october
                    "nov.", // abb november
                    "d\u00e9c.", // abb december
                    "" // abb mo month 13 if applicable
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
                    "dimanche", // Sunday
                    "lundi", // Monday
                    "mardi", // Tuesday
                    "mercredi", // Wednesday
                    "jeudi", // Thursday
                    "vendredi", // Friday
                    "samedi" // Saturday
                }
            },
            { "DayAbbreviations",
                new String[] {
                    "dim.", // abb Sunday
                    "lun.", // abb Monday
                    "mar.", // abb Tuesday
                    "mer.", // abb Wednesday
                    "jeu.", // abb Thursday
                    "ven.", // abb Friday
                    "sam." // abb Saturday
                }
            },
            { "standalone.DayAbbreviations",
                new String[] {
                    "dim.",
                    "lun.",
                    "mar.",
                    "mer.",
                    "jeu.",
                    "ven.",
                    "sam.",
                }
            },
            { "DayNarrows",
                new String[] {
                    "D",
                    "L",
                    "M",
                    "M",
                    "J",
                    "V",
                    "S",
                }
            },
            { "Eras",
                new String[] { // era strings
                    "BC",
                    "ap. J.-C."
                }
            },
            { "short.Eras",
                new String[] {
                    "av. J.-C.",
                    "ap. J.-C.",
                }
            },
            { "buddhist.Eras",
                new String[] {
                    "BC",
                    "\u00e8re bouddhiste",
                }
            },
            { "buddhist.short.Eras",
                new String[] {
                    "BC",
                    "\u00e8re b.",
                }
            },
            { "buddhist.narrow.Eras",
                new String[] {
                    "BC",
                    "E.B.",
                }
            },
            { "NumberPatterns",
                new String[] {
                    "#,##0.###;-#,##0.###", // decimal pattern
                    "#,##0.00 \u00A4;-#,##0.00 \u00A4", // currency pattern
                    "#,##0 %" // percent pattern
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
                    "HH' h 'mm z", // full time pattern
                    "HH:mm:ss z", // long time pattern
                    "HH:mm:ss", // medium time pattern
                    "HH:mm", // short time pattern
                }
            },
            { "DatePatterns",
                new String[] {
                    "EEEE d MMMM yyyy", // full date pattern
                    "d MMMM yyyy", // long date pattern
                    "d MMM yyyy", // medium date pattern
                    "dd/MM/yy", // short date pattern
                }
            },
            { "DateTimePatterns",
                new String[] {
                    "{1} {0}" // date-time pattern
                }
            },
            { "DateTimePatternChars", "GaMjkHmsSEDFwWxhKzZ" },
        };
    }
}
