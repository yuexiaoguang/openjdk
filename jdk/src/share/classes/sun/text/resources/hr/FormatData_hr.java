package sun.text.resources.hr;

import sun.util.resources.ParallelListResourceBundle;

public class FormatData_hr extends ParallelListResourceBundle {
    /**
     * Overrides ParallelListResourceBundle
     */
    @Override
    protected final Object[][] getContents() {
        final String[] rocEras ={
            "prije R.O.C.",
            "R.O.C.",
        };
        return new Object[][] {
            { "MonthNames",
                new String[] {
                    "sije\u010dnja",
                    "velja\u010de",
                    "o\u017eujka",
                    "travnja",
                    "svibnja",
                    "lipnja",
                    "srpnja",
                    "kolovoza",
                    "rujna",
                    "listopada",
                    "studenoga",
                    "prosinca",
                    "",
                }
            },
            { "standalone.MonthNames",
                new String[] {
                    "sije\u010danj", // january
                    "velja\u010da", // february
                    "o\u017eujak", // march
                    "travanj", // april
                    "svibanj", // may
                    "lipanj", // june
                    "srpanj", // july
                    "kolovoz", // august
                    "rujan", // september
                    "listopad", // october
                    "studeni", // november
                    "prosinac", // december
                    "" // month 13 if applicable
                }
            },
            { "MonthAbbreviations",
                new String[] {
                    "sij",
                    "velj",
                    "o\u017eu",
                    "tra",
                    "svi",
                    "lip",
                    "srp",
                    "kol",
                    "ruj",
                    "lis",
                    "stu",
                    "pro",
                    "",
                }
            },
            { "standalone.MonthAbbreviations",
                new String[] {
                    "sij", // abb january
                    "vel", // abb february
                    "o\u017eu", // abb march
                    "tra", // abb april
                    "svi", // abb may
                    "lip", // abb june
                    "srp", // abb july
                    "kol", // abb august
                    "ruj", // abb september
                    "lis", // abb october
                    "stu", // abb november
                    "pro", // abb december
                    "" // abb month 13 if applicable
                }
            },
            { "MonthNarrows",
                new String[] {
                    "1.",
                    "2.",
                    "3.",
                    "4.",
                    "5.",
                    "6.",
                    "7.",
                    "8.",
                    "9.",
                    "10.",
                    "11.",
                    "12.",
                    "",
                }
            },
            { "standalone.MonthNarrows",
                new String[] {
                    "1.",
                    "2.",
                    "3.",
                    "4.",
                    "5.",
                    "6.",
                    "7.",
                    "8.",
                    "9.",
                    "10.",
                    "11.",
                    "12.",
                    "",
                }
            },
            { "DayNames",
                new String[] {
                    "nedjelja", // Sunday
                    "ponedjeljak", // Monday
                    "utorak", // Tuesday
                    "srijeda", // Wednesday
                    "\u010detvrtak", // Thursday
                    "petak", // Friday
                    "subota" // Saturday
                }
            },
            { "standalone.DayNames",
                new String[] {
                    "nedjelja",
                    "ponedjeljak",
                    "utorak",
                    "srijeda",
                    "\u010detvrtak",
                    "petak",
                    "subota",
                }
            },
            { "DayAbbreviations",
                new String[] {
                    "ned", // abb Sunday
                    "pon", // abb Monday
                    "uto", // abb Tuesday
                    "sri", // abb Wednesday
                    "\u010det", // abb Thursday
                    "pet", // abb Friday
                    "sub" // abb Saturday
                }
            },
            { "standalone.DayAbbreviations",
                new String[] {
                    "ned",
                    "pon",
                    "uto",
                    "sri",
                    "\u010det",
                    "pet",
                    "sub",
                }
            },
            { "DayNarrows",
                new String[] {
                    "N",
                    "P",
                    "U",
                    "S",
                    "\u010c",
                    "P",
                    "S",
                }
            },
            { "standalone.DayNarrows",
                new String[] {
                    "n",
                    "p",
                    "u",
                    "s",
                    "\u010d",
                    "p",
                    "s",
                }
            },
            { "Eras",
                new String[] {
                    "Prije Krista",
                    "Poslije Krista",
                }
            },
            { "short.Eras",
                new String[] {
                    "p. n. e.",
                    "A. D.",
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
                    "yyyy. MMMM dd", // full date pattern
                    "yyyy. MMMM dd", // long date pattern
                    "yyyy.MM.dd", // medium date pattern
                    "yyyy.MM.dd", // short date pattern
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
