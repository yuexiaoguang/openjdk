package sun.text.resources.ja;

import sun.util.resources.ParallelListResourceBundle;

public class FormatData_ja extends ParallelListResourceBundle {
    /**
     * Overrides ParallelListResourceBundle
     */
    @Override
    protected final Object[][] getContents() {
        // era strings for Japanese imperial calendar
        final String[] japaneseEras = {
            "\u897f\u66a6", // Seireki (Gregorian)
            "\u660e\u6cbb", // Meiji
            "\u5927\u6b63", // Taisho
            "\u662d\u548c", // Showa
            "\u5e73\u6210", // Heisei
        };
        final String[] rocEras = {
            "\u6c11\u56fd\u524d",
            "\u6c11\u56fd",
        };
        return new Object[][] {
            { "MonthNames",
                new String[] {
                    "1\u6708", // january
                    "2\u6708", // february
                    "3\u6708", // march
                    "4\u6708", // april
                    "5\u6708", // may
                    "6\u6708", // june
                    "7\u6708", // july
                    "8\u6708", // august
                    "9\u6708", // september
                    "10\u6708", // october
                    "11\u6708", // november
                    "12\u6708", // december
                    ""          // month 13 if applicable
                }
            },
            { "MonthAbbreviations",
                new String[] {
                    "1", // abb january
                    "2", // abb february
                    "3", // abb march
                    "4", // abb april
                    "5", // abb may
                    "6", // abb june
                    "7", // abb july
                    "8", // abb august
                    "9", // abb september
                    "10", // abb october
                    "11", // abb november
                    "12", // abb december
                    ""    // abb month 13 if applicable
                }
            },
            { "DayNames",
                new String[] {
                    "\u65e5\u66dc\u65e5", // Sunday
                    "\u6708\u66dc\u65e5", // Monday
                    "\u706b\u66dc\u65e5", // Tuesday
                    "\u6c34\u66dc\u65e5", // Wednesday
                    "\u6728\u66dc\u65e5", // Thursday
                    "\u91d1\u66dc\u65e5", // Friday
                    "\u571f\u66dc\u65e5"  // Saturday
                }
            },
            { "DayAbbreviations",
                new String[] {
                    "\u65e5", // abb Sunday
                    "\u6708", // abb Monday
                    "\u706b", // abb Tuesday
                    "\u6c34", // abb Wednesday
                    "\u6728", // abb Thursday
                    "\u91d1", // abb Friday
                    "\u571f"  // abb Saturday
                }
            },
            { "DayNarrows",
                new String[] {
                    "\u65e5",
                    "\u6708",
                    "\u706b",
                    "\u6c34",
                    "\u6728",
                    "\u91d1",
                    "\u571f",
                }
            },
            { "AmPmMarkers",
                new String[] {
                    "\u5348\u524d", // am marker
                    "\u5348\u5f8c" // pm marker
                }
            },
            { "Eras",
                new String[] { // era strings for GregorianCalendar
                    "\u7d00\u5143\u524d",
                    "\u897f\u66a6"
                }
            },
            { "buddhist.Eras",
                new String[] { // era strings for Thai Buddhist calendar
                    "\u7d00\u5143\u524d", // Kigenzen
                    "\u4ecf\u66a6",       // Butsureki
                }
            },
            { "japanese.Eras", japaneseEras },
            { "japanese.FirstYear",
                new String[] {  // first year name
                    "\u5143",   // "Gan"-nen
                }
            },
            { "NumberElements",
                new String[] {
                    ".",        // decimal separator
                    ",",        // group (thousands) separator
                    ";",        // list separator
                    "%",        // percent sign
                    "0",        // native 0 digit
                    "#",        // pattern digit
                    "-",        // minus sign
                    "E",        // exponential
                    "\u2030",   // per mille
                    "\u221e",   // infinity
                    "\ufffd"    // NaN
                }
            },
            { "TimePatterns",
                new String[] {
                    "H'\u6642'mm'\u5206'ss'\u79d2' z", // full time pattern
                    "H:mm:ss z",                       // long time pattern
                    "H:mm:ss",                         // medium time pattern
                    "H:mm",                            // short time pattern
                }
            },
            { "DatePatterns",
                new String[] {
                    "yyyy'\u5e74'M'\u6708'd'\u65e5'",  // full date pattern
                    "yyyy/MM/dd",                      // long date pattern
                    "yyyy/MM/dd",                      // medium date pattern
                    "yy/MM/dd",                        // short date pattern
                }
            },
            { "DateTimePatterns",
                new String[] {
                    "{1} {0}"                          // date-time pattern
                }
            },
            { "japanese.DatePatterns",
                new String[] {
                    "GGGGyyyy'\u5e74'M'\u6708'd'\u65e5'", // full date pattern
                    "Gy.MM.dd",  // long date pattern
                    "Gy.MM.dd",  // medium date pattern
                    "Gy.MM.dd",  // short date pattern
                }
            },
            { "japanese.TimePatterns",
                new String[] {
                    "H'\u6642'mm'\u5206'ss'\u79d2' z", // full time pattern
                    "H:mm:ss z", // long time pattern
                    "H:mm:ss",   // medium time pattern
                    "H:mm",      // short time pattern
                }
            },
            { "japanese.DateTimePatterns",
                new String[] {
                    "{1} {0}"    // date-time pattern
                }
            },
            { "DateTimePatternChars", "GyMdkHmsSEDFwWahKzZ" },
        };
    }
}
