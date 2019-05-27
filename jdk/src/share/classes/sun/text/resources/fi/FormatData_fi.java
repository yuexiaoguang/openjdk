package sun.text.resources.fi;

import sun.util.resources.ParallelListResourceBundle;

public class FormatData_fi extends ParallelListResourceBundle {
    /**
     * Overrides ParallelListResourceBundle
     */
    protected final Object[][] getContents() {
        return new Object[][] {
            { "MonthNames",
                new String[] {
                    "tammikuuta",
                    "helmikuuta",
                    "maaliskuuta",
                    "huhtikuuta",
                    "toukokuuta",
                    "kes\u00e4kuuta",
                    "hein\u00e4kuuta",
                    "elokuuta",
                    "syyskuuta",
                    "lokakuuta",
                    "marraskuuta",
                    "joulukuuta",
                    "",
                }
            },
            { "standalone.MonthNames",
                new String[] {
                    "tammikuu", // january
                    "helmikuu", // february
                    "maaliskuu", // march
                    "huhtikuu", // april
                    "toukokuu", // may
                    "kes\u00e4kuu", // june
                    "hein\u00e4kuu", // july
                    "elokuu", // august
                    "syyskuu", // september
                    "lokakuu", // october
                    "marraskuu", // november
                    "joulukuu", // december
                    "" // month 13 if applicable
                }
            },
            { "MonthAbbreviations",
                new String[] {
                    "tammikuuta",
                    "helmikuuta",
                    "maaliskuuta",
                    "huhtikuuta",
                    "toukokuuta",
                    "kes\u00e4kuuta",
                    "hein\u00e4kuuta",
                    "elokuuta",
                    "syyskuuta",
                    "lokakuuta",
                    "marraskuuta",
                    "joulukuuta",
                    "",
                }
            },
            { "standalone.MonthAbbreviations",
                new String[] {
                    "tammi", // abb january
                    "helmi", // abb february
                    "maalis", // abb march
                    "huhti", // abb april
                    "touko", // abb may
                    "kes\u00e4", // abb june
                    "hein\u00e4", // abb july
                    "elo", // abb august
                    "syys", // abb september
                    "loka", // abb october
                    "marras", // abb november
                    "joulu", // abb december
                    "" // abb month 13 if applicable
                }
            },
            { "MonthNarrows",
                new String[] {
                    "T",
                    "H",
                    "M",
                    "H",
                    "T",
                    "K",
                    "H",
                    "E",
                    "S",
                    "L",
                    "M",
                    "J",
                    "",
                }
            },
            { "standalone.MonthNarrows",
                new String[] {
                    "T",
                    "H",
                    "M",
                    "H",
                    "T",
                    "K",
                    "H",
                    "E",
                    "S",
                    "L",
                    "M",
                    "J",
                    "",
                }
            },
            { "long.Eras",
                new String[] {
                    "ennen Kristuksen syntym\u00e4\u00e4",
                    "j\u00e4lkeen Kristuksen syntym\u00e4n",
                }
            },
            { "Eras",
                new String[] {
                    "eKr.",
                    "jKr.",
                }
            },
            { "narrow.Eras",
                new String[] {
                    "eK",
                    "jK",
                }
            },
            { "DayNames",
                new String[] {
                    "sunnuntai", // Sunday
                    "maanantai", // Monday
                    "tiistai", // Tuesday
                    "keskiviikko", // Wednesday
                    "torstai", // Thursday
                    "perjantai", // Friday
                    "lauantai" // Saturday
                }
            },
            { "standalone.DayNames",
                new String[] {
                    "sunnuntai",
                    "maanantai",
                    "tiistai",
                    "keskiviikko",
                    "torstai",
                    "perjantai",
                    "lauantai",
                }
            },
            { "DayAbbreviations",
                new String[] {
                    "su", // abb Sunday
                    "ma", // abb Monday
                    "ti", // abb Tuesday
                    "ke", // abb Wednesday
                    "to", // abb Thursday
                    "pe", // abb Friday
                    "la" // abb Saturday
                }
            },
            { "standalone.DayAbbreviations",
                new String[] {
                    "su",
                    "ma",
                    "ti",
                    "ke",
                    "to",
                    "pe",
                    "la",
                }
            },
            { "DayNarrows",
                new String[] {
                    "S",
                    "M",
                    "T",
                    "K",
                    "T",
                    "P",
                    "L",
                }
            },
            { "standalone.DayNarrows",
                new String[] {
                    "S",
                    "M",
                    "T",
                    "K",
                    "T",
                    "P",
                    "L",
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
                    "H.mm.ss z", // full time pattern
                    "'klo 'H.mm.ss", // long time pattern
                    "H:mm:ss", // medium time pattern
                    "H:mm", // short time pattern
                }
            },
            { "DatePatterns",
                new String[] {
                    "d. MMMM'ta 'yyyy", // full date pattern
                    "d. MMMM'ta 'yyyy", // long date pattern
                    "d.M.yyyy", // medium date pattern
                    "d.M.yyyy", // short date pattern
                }
            },
            { "DateTimePatterns",
                new String[] {
                    "{1} {0}" // date-time pattern
                }
            },
            { "DateTimePatternChars", "GanjkHmsSEDFwWxhKzZ" },
            { "AmPmMarkers",
                new String[] {
                    "ap.", // am marker
                    "ip."  // pm marker
                }
            },
            { "narrow.AmPmMarkers",
                new String[] {
                    "ap.",
                    "ip.",
                }
            },
        };
    }
}
