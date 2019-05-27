package sun.text.resources.zh;

import sun.util.resources.ParallelListResourceBundle;

public class FormatData_zh extends ParallelListResourceBundle {
    /**
     * Overrides ParallelListResourceBundle
     */
    @Override
    protected final Object[][] getContents() {
        final String[] rocEras = {
            "\u6c11\u56fd\u524d",
            "\u6c11\u56fd",
        };
        return new Object[][] {
            { "MonthNames",
                new String[] {
                    "\u4e00\u6708", // january
                    "\u4e8c\u6708", // february
                    "\u4e09\u6708", // march
                    "\u56db\u6708", // april
                    "\u4e94\u6708", // may
                    "\u516d\u6708", // june
                    "\u4e03\u6708", // july
                    "\u516b\u6708", // august
                    "\u4e5d\u6708", // september
                    "\u5341\u6708", // october
                    "\u5341\u4e00\u6708", // november
                    "\u5341\u4e8c\u6708", // december
                    "" // month 13 if applicable
                }
            },
            { "standalone.MonthNames",
                new String[] {
                    "\u4e00\u6708",
                    "\u4e8c\u6708",
                    "\u4e09\u6708",
                    "\u56db\u6708",
                    "\u4e94\u6708",
                    "\u516d\u6708",
                    "\u4e03\u6708",
                    "\u516b\u6708",
                    "\u4e5d\u6708",
                    "\u5341\u6708",
                    "\u5341\u4e00\u6708",
                    "\u5341\u4e8c\u6708",
                    "",
                }
            },
            { "MonthAbbreviations",
                new String[] {
                    "\u4e00\u6708", // abb january
                    "\u4e8c\u6708", // abb february
                    "\u4e09\u6708", // abb march
                    "\u56db\u6708", // abb april
                    "\u4e94\u6708", // abb may
                    "\u516d\u6708", // abb june
                    "\u4e03\u6708", // abb july
                    "\u516b\u6708", // abb august
                    "\u4e5d\u6708", // abb september
                    "\u5341\u6708", // abb october
                    "\u5341\u4e00\u6708", // abb november
                    "\u5341\u4e8c\u6708", // abb december
                    "" // abb month 13 if applicable
                }
            },
            { "standalone.MonthAbbreviations",
                new String[] {
                    "\u4e00\u6708",
                    "\u4e8c\u6708",
                    "\u4e09\u6708",
                    "\u56db\u6708",
                    "\u4e94\u6708",
                    "\u516d\u6708",
                    "\u4e03\u6708",
                    "\u516b\u6708",
                    "\u4e5d\u6708",
                    "\u5341\u6708",
                    "\u5341\u4e00\u6708",
                    "\u5341\u4e8c\u6708",
                    "",
                }
            },
            { "MonthNarrows",
                new String[] {
                    "1",
                    "2",
                    "3",
                    "4",
                    "5",
                    "6",
                    "7",
                    "8",
                    "9",
                    "10",
                    "11",
                    "12",
                    "",
                }
            },
            { "standalone.MonthNarrows",
                new String[] {
                    "1\u6708",
                    "2\u6708",
                    "3\u6708",
                    "4\u6708",
                    "5\u6708",
                    "6\u6708",
                    "7\u6708",
                    "8\u6708",
                    "9\u6708",
                    "10\u6708",
                    "11\u6708",
                    "12\u6708",
                    "",
                }
            },
            { "DayNames",
                new String[] {
                    "\u661f\u671f\u65e5", // Sunday
                    "\u661f\u671f\u4e00", // Monday
                    "\u661f\u671f\u4e8c", // Tuesday
                    "\u661f\u671f\u4e09", // Wednesday
                    "\u661f\u671f\u56db", // Thursday
                    "\u661f\u671f\u4e94", // Friday
                    "\u661f\u671f\u516d" // Saturday
                }
            },
            { "standalone.DayNames",
                new String[] {
                    "\u661f\u671f\u65e5",
                    "\u661f\u671f\u4e00",
                    "\u661f\u671f\u4e8c",
                    "\u661f\u671f\u4e09",
                    "\u661f\u671f\u56db",
                    "\u661f\u671f\u4e94",
                    "\u661f\u671f\u516d",
                }
            },
            { "DayAbbreviations",
                new String[] {
                    "\u661f\u671f\u65e5", // abb Sunday
                    "\u661f\u671f\u4e00", // abb Monday
                    "\u661f\u671f\u4e8c", // abb Tuesday
                    "\u661f\u671f\u4e09", // abb Wednesday
                    "\u661f\u671f\u56db", // abb Thursday
                    "\u661f\u671f\u4e94", // abb Friday
                    "\u661f\u671f\u516d" // abb Saturday
                }
            },
            { "standalone.DayAbbreviations",
                new String[] {
                    "\u5468\u65e5",
                    "\u5468\u4e00",
                    "\u5468\u4e8c",
                    "\u5468\u4e09",
                    "\u5468\u56db",
                    "\u5468\u4e94",
                    "\u5468\u516d",
                }
            },
            { "DayNarrows",
                new String[] {
                    "\u65e5",
                    "\u4e00",
                    "\u4e8c",
                    "\u4e09",
                    "\u56db",
                    "\u4e94",
                    "\u516d",
                }
            },
            { "standalone.DayNarrows",
                new String[] {
                    "\u65e5",
                    "\u4e00",
                    "\u4e8c",
                    "\u4e09",
                    "\u56db",
                    "\u4e94",
                    "\u516d",
                }
            },
            { "AmPmMarkers",
                new String[] {
                    "\u4e0a\u5348", // am marker
                    "\u4e0b\u5348" // pm marker
                }
            },
            { "Eras",
                new String[] { // era strings
                    "\u516c\u5143\u524d",
                    "\u516c\u5143"
                }
            },
            { "buddhist.Eras",
                new String[] {
                    "BC",
                    "\u4f5b\u5386",
                }
            },
            { "japanese.Eras",
                new String[] {
                    "\u516c\u5143",
                    "\u660e\u6cbb",
                    "\u5927\u6b63",
                    "\u662d\u548c",
                    "\u5e73\u6210",
                }
            },
            { "TimePatterns",
                new String[] {
                    "ahh'\u65f6'mm'\u5206'ss'\u79d2' z", // full time pattern
                    "ahh'\u65f6'mm'\u5206'ss'\u79d2'", // long time pattern
                    "H:mm:ss", // medium time pattern
                    "ah:mm", // short time pattern
                }
            },
            { "DatePatterns",
                new String[] {
                    "yyyy'\u5e74'M'\u6708'd'\u65e5' EEEE", // full date pattern
                    "yyyy'\u5e74'M'\u6708'd'\u65e5'", // long date pattern
                    "yyyy-M-d", // medium date pattern
                    "yy-M-d", // short date pattern
                }
            },
            { "DateTimePatterns",
                new String[] {
                    "{1} {0}" // date-time pattern
                }
            },
            { "buddhist.DatePatterns",
                new String[] {
                    "GGGGy\u5e74M\u6708d\u65e5EEEE",
                    "GGGGy\u5e74M\u6708d\u65e5",
                    "GGGGyyyy-M-d",
                    "GGGGy-M-d",
                }
            },
            { "japanese.DatePatterns",
                new String[] {
                    "GGGGy\u5e74M\u6708d\u65e5EEEE",
                    "GGGGy\u5e74M\u6708d\u65e5",
                    "GGGGy\u5e74M\u6708d\u65e5",
                    "GGGGyy-MM-dd",
                }
            },
        };
    }
}
