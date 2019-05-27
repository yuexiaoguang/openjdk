package sun.text.resources.lt;

import sun.util.resources.ParallelListResourceBundle;

public class FormatData_lt extends ParallelListResourceBundle {
    /**
     * Overrides ParallelListResourceBundle
     */
    protected final Object[][] getContents() {
        return new Object[][] {
            { "MonthNames",
                new String[] {
                    "sausio",
                    "vasaris",
                    "kovas",
                    "balandis",
                    "gegu\u017e\u0117",
                    "bir\u017eelis",
                    "liepa",
                    "rugpj\u016btis",
                    "rugs\u0117jis",
                    "spalis",
                    "lapkritis",
                    "gruodis",
                    "",
                }
            },
            { "standalone.MonthNames",
                new String[] {
                    "Sausio", // january
                    "Vasario", // february
                    "Kovo", // march
                    "Baland\u017eio", // april
                    "Gegu\u017e\u0117s", // may
                    "Bir\u017eelio", // june
                    "Liepos", // july
                    "Rugpj\u016b\u010dio", // august
                    "Rugs\u0117jo", // september
                    "Spalio", // october
                    "Lapkri\u010dio", // november
                    "Gruod\u017eio", // december
                    "" // month 13 if applicable
                }
            },
            { "MonthAbbreviations",
                new String[] {
                    "Sau", // abb january
                    "Vas", // abb february
                    "Kov", // abb march
                    "Bal", // abb april
                    "Geg", // abb may
                    "Bir", // abb june
                    "Lie", // abb july
                    "Rgp", // abb august
                    "Rgs", // abb september
                    "Spa", // abb october
                    "Lap", // abb november
                    "Grd", // abb december
                    "" // abb month 13 if applicable
                }
            },
            { "standalone.MonthAbbreviations",
                new String[] {
                    "Saus.",
                    "Vas.",
                    "Kov.",
                    "Bal.",
                    "Geg.",
                    "Bir.",
                    "Liep.",
                    "Rugp.",
                    "Rugs.",
                    "Spal.",
                    "Lapkr.",
                    "Gruod.",
                    "",
                }
            },
            { "MonthNarrows",
                new String[] {
                    "S",
                    "V",
                    "K",
                    "B",
                    "G",
                    "B",
                    "L",
                    "R",
                    "R",
                    "S",
                    "L",
                    "G",
                    "",
                }
            },
            { "standalone.MonthNarrows",
                new String[] {
                    "S",
                    "V",
                    "K",
                    "B",
                    "G",
                    "B",
                    "L",
                    "R",
                    "R",
                    "S",
                    "L",
                    "G",
                    "",
                }
            },
            { "DayNames",
                new String[] {
                    "Sekmadienis", // Sunday
                    "Pirmadienis", // Monday
                    "Antradienis", // Tuesday
                    "Tre\u010diadienis", // Wednesday
                    "Ketvirtadienis", // Thursday
                    "Penktadienis", // Friday
                    "\u0160e\u0161tadienis" // Saturday
                }
            },
            { "standalone.DayNames",
                new String[] {
                    "sekmadienis",
                    "pirmadienis",
                    "antradienis",
                    "tre\u010diadienis",
                    "ketvirtadienis",
                    "penktadienis",
                    "\u0161e\u0161tadienis",
                }
            },
            { "DayAbbreviations",
                new String[] {
                    "Sk", // abb Sunday
                    "Pr", // abb Monday
                    "An", // abb Tuesday
                    "Tr", // abb Wednesday
                    "Kt", // abb Thursday
                    "Pn", // abb Friday
                    "\u0160t" // abb Saturday
                }
            },
            { "standalone.DayAbbreviations",
                new String[] {
                    "Sk",
                    "Pr",
                    "An",
                    "Tr",
                    "Kt",
                    "Pn",
                    "\u0160t",
                }
            },
            { "DayNarrows",
                new String[] {
                    "S",
                    "P",
                    "A",
                    "T",
                    "K",
                    "P",
                    "\u0160",
                }
            },
            { "standalone.DayNarrows",
                new String[] {
                    "S",
                    "P",
                    "A",
                    "T",
                    "K",
                    "P",
                    "\u0160",
                }
            },
            { "Eras",
                new String[] { // era strings
                    "pr.Kr.",
                    "po.Kr."
                }
            },
            { "short.Eras",
                new String[] {
                    "pr. Kr.",
                    "po Kr.",
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
                    "HH.mm.ss z", // full time pattern
                    "HH.mm.ss z", // long time pattern
                    "HH.mm.ss", // medium time pattern
                    "HH.mm", // short time pattern
                }
            },
            { "DatePatterns",
                new String[] {
                    "EEEE, yyyy, MMMM d", // full date pattern
                    "EEEE, yyyy, MMMM d", // long date pattern
                    "yyyy-MM-dd", // medium date pattern
                    "yy.M.d", // short date pattern
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
