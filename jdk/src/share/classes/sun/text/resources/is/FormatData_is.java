package sun.text.resources.is;

import sun.util.resources.ParallelListResourceBundle;

public class FormatData_is extends ParallelListResourceBundle {
    /**
     * Overrides ParallelListResourceBundle
     */
    protected final Object[][] getContents() {
        return new Object[][] {
            { "MonthNames",
                new String[] {
                    "jan\u00faar", // january
                    "febr\u00faar", // february
                    "mars", // march
                    "apr\u00edl", // april
                    "ma\u00ed", // may
                    "j\u00fan\u00ed", // june
                    "j\u00fal\u00ed", // july
                    "\u00e1g\u00fast", // august
                    "september", // september
                    "okt\u00f3ber", // october
                    "n\u00f3vember", // november
                    "desember", // december
                    "" // month 13 if applicable
                }
            },
            { "MonthAbbreviations",
                new String[] {
                    "jan.", // abb january
                    "feb.", // abb february
                    "mar.", // abb march
                    "apr.", // abb april
                    "ma\u00ed", // abb may
                    "j\u00fan.", // abb june
                    "j\u00fal.", // abb july
                    "\u00e1g\u00fa.", // abb august
                    "sep.", // abb september
                    "okt.", // abb october
                    "n\u00f3v.", // abb november
                    "des.", // abb december
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
                    "\u00c1",
                    "L",
                    "O",
                    "N",
                    "D",
                    "",
                }
            },
            { "standalone.MonthNarrows",
                new String[] {
                    "j",
                    "f",
                    "m",
                    "a",
                    "m",
                    "j",
                    "j",
                    "\u00e1",
                    "s",
                    "o",
                    "n",
                    "d",
                    "",
                }
            },
            { "DayNames",
                new String[] {
                    "sunnudagur", // Sunday
                    "m\u00e1nudagur", // Monday
                    "\u00feri\u00f0judagur", // Tuesday
                    "mi\u00f0vikudagur", // Wednesday
                    "fimmtudagur", // Thursday
                    "f\u00f6studagur", // Friday
                    "laugardagur" // Saturday
                }
            },
            { "DayAbbreviations",
                new String[] {
                    "sun.", // abb Sunday
                    "m\u00e1n.", // abb Monday
                    "\u00feri.", // abb Tuesday
                    "mi\u00f0.", // abb Wednesday
                    "fim.", // abb Thursday
                    "f\u00f6s.", // abb Friday
                    "lau." // abb Saturday
                }
            },
            { "DayNarrows",
                new String[] {
                    "S",
                    "M",
                    "\u00de",
                    "M",
                    "F",
                    "F",
                    "L",
                }
            },
            { "standalone.DayNarrows",
                new String[] {
                    "s",
                    "m",
                    "\u00fe",
                    "m",
                    "f",
                    "f",
                    "l",
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
                    "HH:mm:ss z", // full time pattern
                    "HH:mm:ss z", // long time pattern
                    "HH:mm:ss", // medium time pattern
                    "HH:mm", // short time pattern
                }
            },
            { "DatePatterns",
                new String[] {
                    "d. MMMM yyyy", // full date pattern
                    "d. MMMM yyyy", // long date pattern
                    "d.M.yyyy", // medium date pattern
                    "d.M.yyyy", // short date pattern
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
