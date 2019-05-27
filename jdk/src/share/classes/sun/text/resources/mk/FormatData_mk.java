package sun.text.resources.mk;

import sun.util.resources.ParallelListResourceBundle;

public class FormatData_mk extends ParallelListResourceBundle {
    /**
     * Overrides ParallelListResourceBundle
     */
    protected final Object[][] getContents() {
        return new Object[][] {
            { "MonthNames",
                new String[] {
                    "\u0458\u0430\u043d\u0443\u0430\u0440\u0438", // january
                    "\u0444\u0435\u0432\u0440\u0443\u0430\u0440\u0438", // february
                    "\u043c\u0430\u0440\u0442", // march
                    "\u0430\u043f\u0440\u0438\u043b", // april
                    "\u043c\u0430\u0458", // may
                    "\u0458\u0443\u043d\u0438", // june
                    "\u0458\u0443\u043b\u0438", // july
                    "\u0430\u0432\u0433\u0443\u0441\u0442", // august
                    "\u0441\u0435\u043f\u0442\u0435\u043c\u0432\u0440\u0438", // september
                    "\u043e\u043a\u0442\u043e\u043c\u0432\u0440\u0438", // october
                    "\u043d\u043e\u0435\u043c\u0432\u0440\u0438", // november
                    "\u0434\u0435\u043a\u0435\u043c\u0432\u0440\u0438", // december
                    "" // month 13 if applicable
                }
            },
            { "MonthAbbreviations",
                new String[] {
                    "\u0458\u0430\u043d.", // abb january
                    "\u0444\u0435\u0432.", // abb february
                    "\u043c\u0430\u0440.", // abb march
                    "\u0430\u043f\u0440.", // abb april
                    "\u043c\u0430\u0458.", // abb may
                    "\u0458\u0443\u043d.", // abb june
                    "\u0458\u0443\u043b.", // abb july
                    "\u0430\u0432\u0433.", // abb august
                    "\u0441\u0435\u043f\u0442.", // abb september
                    "\u043e\u043a\u0442.", // abb october
                    "\u043d\u043e\u0435\u043c.", // abb november
                    "\u0434\u0435\u043a\u0435\u043c.", // abb december
                    "" // abb month 13 if applicable
                }
            },
            { "MonthNarrows",
                new String[] {
                    "\u0458",
                    "\u0444",
                    "\u043c",
                    "\u0430",
                    "\u043c",
                    "\u0458",
                    "\u0458",
                    "\u0430",
                    "\u0441",
                    "\u043e",
                    "\u043d",
                    "\u0434",
                    "",
                }
            },
            { "DayNames",
                new String[] {
                    "\u043d\u0435\u0434\u0435\u043b\u0430", // Sunday
                    "\u043f\u043e\u043d\u0435\u0434\u0435\u043b\u043d\u0438\u043a", // Monday
                    "\u0432\u0442\u043e\u0440\u043d\u0438\u043a", // Tuesday
                    "\u0441\u0440\u0435\u0434\u0430", // Wednesday
                    "\u0447\u0435\u0442\u0432\u0440\u0442\u043e\u043a", // Thursday
                    "\u043f\u0435\u0442\u043e\u043a", // Friday
                    "\u0441\u0430\u0431\u043e\u0442\u0430" // Saturday
                }
            },
            { "DayAbbreviations",
                new String[] {
                    "\u043d\u0435\u0434.", // abb Sunday
                    "\u043f\u043e\u043d.", // abb Monday
                    "\u0432\u0442.", // abb Tuesday
                    "\u0441\u0440\u0435.", // abb Wednesday
                    "\u0447\u0435\u0442.", // abb Thursday
                    "\u043f\u0435\u0442.", // abb Friday
                    "\u0441\u0430\u0431." // abb Saturday
                }
            },
            { "DayNarrows",
                new String[] {
                    "\u043d",
                    "\u043f",
                    "\u0432",
                    "\u0441",
                    "\u0447",
                    "\u043f",
                    "\u0441",
                }
            },
            { "Eras",
                new String[] { // era strings
                    "\u043f\u0440.\u043d.\u0435.",
                    "\u0430\u0435."
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
                    "HH:mm:", // medium time pattern
                    "HH:mm", // short time pattern
                }
            },
            { "DatePatterns",
                new String[] {
                    "EEEE, d, MMMM yyyy", // full date pattern
                    "d, MMMM yyyy", // long date pattern
                    "d.M.yyyy", // medium date pattern
                    "d.M.yy", // short date pattern
                }
            },
            { "DateTimePatterns",
                new String[] {
                    "{1} {0}" // date-time pattern
                }
            },
            { "DateTimePatternChars", "GuMtkHmsSEDFwWahKzZ" },
        };
    }
}
