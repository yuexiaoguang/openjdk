package sun.text.resources.sv;

import sun.util.resources.ParallelListResourceBundle;

public class FormatData_sv extends ParallelListResourceBundle {
    /**
     * Overrides ParallelListResourceBundle
     */
    @Override
    protected final Object[][] getContents() {
        final String[] rocEras = {
            "f\u00f6re R.K.",
            "R.K.",
        };
        return new Object[][] {
            { "MonthNames",
                new String[] {
                    "januari", // january
                    "februari", // february
                    "mars", // march
                    "april", // april
                    "maj", // may
                    "juni", // june
                    "juli", // july
                    "augusti", // august
                    "september", // september
                    "oktober", // october
                    "november", // november
                    "december", // december
                    "" // month 13 if applicable
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
            { "MonthAbbreviations",
                new String[] {
                    "jan", // abb january
                    "feb", // abb february
                    "mar", // abb march
                    "apr", // abb april
                    "maj", // abb may
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
            { "standalone.MonthAbbreviations",
                new String[] {
                    "jan",
                    "feb",
                    "mar",
                    "apr",
                    "maj",
                    "jun",
                    "jul",
                    "aug",
                    "sep",
                    "okt",
                    "nov",
                    "dec",
                    "",
                }
            },
            { "standalone.MonthNarrows",
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
                    "s\u00f6ndag", // Sunday
                    "m\u00e5ndag", // Monday
                    "tisdag", // Tuesday
                    "onsdag", // Wednesday
                    "torsdag", // Thursday
                    "fredag", // Friday
                    "l\u00f6rdag" // Saturday
                }
            },
            { "DayAbbreviations",
                new String[] {
                    "s\u00f6", // abb Sunday
                    "m\u00e5", // abb Monday
                    "ti", // abb Tuesday
                    "on", // abb Wednesday
                    "to", // abb Thursday
                    "fr", // abb Friday
                    "l\u00f6" // abb Saturday
                }
            },
            { "standalone.DayAbbreviations",
                new String[] {
                    "s\u00f6n",
                    "m\u00e5n",
                    "tis",
                    "ons",
                    "tor",
                    "fre",
                    "l\u00f6r",
                }
            },
            { "DayNarrows",
                new String[] {
                    "S",
                    "M",
                    "T",
                    "O",
                    "T",
                    "F",
                    "L",
                }
            },
            { "standalone.DayNames",
                new String[] {
                    "s\u00f6ndag",
                    "m\u00e5ndag",
                    "tisdag",
                    "onsdag",
                    "torsdag",
                    "fredag",
                    "l\u00f6rdag",
                }
            },
            { "standalone.DayNarrows",
                new String[] {
                    "S",
                    "M",
                    "T",
                    "O",
                    "T",
                    "F",
                    "L",
                }
            },
            { "Eras",
                new String[] {
                    "f\u00f6re Kristus",
                    "efter Kristus",
                }
            },
            { "short.Eras",
                new String[] {
                    "f.Kr.",
                    "e.Kr.",
                }
            },
            { "narrow.Eras",
                new String[] {
                    "f.Kr.",
                    "e.Kr.",
                }
            },
            { "AmPmMarkers",
                new String[] {
                    "fm", // am marker
                    "em" // pm marker
                }
            },
            { "narrow.AmPmMarkers",
                new String[] {
                    "f",
                    "e",
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
                    "'kl 'H:mm z", // full time pattern
                    "HH:mm:ss z", // long time pattern
                    "HH:mm:ss", // medium time pattern
                    "HH:mm", // short time pattern
                }
            },
            { "DatePatterns",
                new String[] {
                    "'den 'd MMMM yyyy", // full date pattern
                    "'den 'd MMMM yyyy", // long date pattern
                    "yyyy-MMM-dd", // medium date pattern
                    "yyyy-MM-dd", // short date pattern
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
