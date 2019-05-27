package sun.text.resources.pl;

import sun.util.resources.ParallelListResourceBundle;

public class FormatData_pl extends ParallelListResourceBundle {
    /**
     * Overrides ParallelListResourceBundle
     */
    protected final Object[][] getContents() {
        return new Object[][] {
            { "MonthNames",
                new String[] {
                    "stycznia",
                    "lutego",
                    "marca",
                    "kwietnia",
                    "maja",
                    "czerwca",
                    "lipca",
                    "sierpnia",
                    "wrze\u015bnia",
                    "pa\u017adziernika",
                    "listopada",
                    "grudnia",
                    "",
                }
            },
            { "standalone.MonthNames",
                new String[] {
                    "stycze\u0144", // january
                    "luty", // february
                    "marzec", // march
                    "kwiecie\u0144", // april
                    "maj", // may
                    "czerwiec", // june
                    "lipiec", // july
                    "sierpie\u0144", // august
                    "wrzesie\u0144", // september
                    "pa\u017adziernik", // october
                    "listopad", // november
                    "grudzie\u0144", // december
                    "" // month 13 if applicable
                }
            },
            { "MonthAbbreviations",
                new String[] {
                    "sty", // abb january
                    "lut", // abb february
                    "mar", // abb march
                    "kwi", // abb april
                    "maj", // abb may
                    "cze", // abb june
                    "lip", // abb july
                    "sie", // abb august
                    "wrz", // abb september
                    "pa\u017a", // abb october
                    "lis", // abb november
                    "gru", // abb december
                    "" // abb month 13 if applicable
                }
            },
            { "standalone.MonthAbbreviations",
                new String[] {
                    "sty",
                    "lut",
                    "mar",
                    "kwi",
                    "maj",
                    "cze",
                    "lip",
                    "sie",
                    "wrz",
                    "pa\u017a",
                    "lis",
                    "gru",
                    "",
                }
            },
            { "MonthNarrows",
                new String[] {
                    "s",
                    "l",
                    "m",
                    "k",
                    "m",
                    "c",
                    "l",
                    "s",
                    "w",
                    "p",
                    "l",
                    "g",
                    "",
                }
            },
            { "standalone.MonthNarrows",
                new String[] {
                    "s",
                    "l",
                    "m",
                    "k",
                    "m",
                    "c",
                    "l",
                    "s",
                    "w",
                    "p",
                    "l",
                    "g",
                    "",
                }
            },
            { "DayNames",
                new String[] {
                    "niedziela", // Sunday
                    "poniedzia\u0142ek", // Monday
                    "wtorek", // Tuesday
                    "\u015broda", // Wednesday
                    "czwartek", // Thursday
                    "pi\u0105tek", // Friday
                    "sobota" // Saturday
                }
            },
            { "standalone.DayNames",
                new String[] {
                    "niedziela",
                    "poniedzia\u0142ek",
                    "wtorek",
                    "\u015broda",
                    "czwartek",
                    "pi\u0105tek",
                    "sobota",
                }
            },
            { "DayAbbreviations",
                new String[] {
                    "N", // abb Sunday
                    "Pn", // abb Monday
                    "Wt", // abb Tuesday
                    "\u015ar", // abb Wednesday
                    "Cz", // abb Thursday
                    "Pt", // abb Friday
                    "So" // abb Saturday
                }
            },
            { "standalone.DayAbbreviations",
                new String[] {
                    "niedz.",
                    "pon.",
                    "wt.",
                    "\u015br.",
                    "czw.",
                    "pt.",
                    "sob.",
                }
            },
            { "DayNarrows",
                new String[] {
                    "N",
                    "P",
                    "W",
                    "\u015a",
                    "C",
                    "P",
                    "S",
                }
            },
            { "standalone.DayNarrows",
                new String[] {
                    "N",
                    "P",
                    "W",
                    "\u015a",
                    "C",
                    "P",
                    "S",
                }
            },
            { "Eras",
                new String[] { // era strings
                    "p.n.e.",
                    "n.e."
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
                    "EEEE, d MMMM yyyy", // full date pattern
                    "d MMMM yyyy", // long date pattern
                    "yyyy-MM-dd", // medium date pattern
                    "yy-MM-dd", // short date pattern
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
