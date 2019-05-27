package sun.text.resources.bg;

import sun.util.resources.ParallelListResourceBundle;

public class FormatData_bg extends ParallelListResourceBundle {
    /**
     * Overrides ParallelListResourceBundle
     */
    protected final Object[][] getContents() {
        return new Object[][] {
            { "MonthNames",
                new String[] {
                    "\u042f\u043d\u0443\u0430\u0440\u0438", // january
                    "\u0424\u0435\u0432\u0440\u0443\u0430\u0440\u0438", // february
                    "\u041c\u0430\u0440\u0442", // march
                    "\u0410\u043f\u0440\u0438\u043b", // april
                    "\u041c\u0430\u0439", // may
                    "\u042e\u043d\u0438", // june
                    "\u042e\u043b\u0438", // july
                    "\u0410\u0432\u0433\u0443\u0441\u0442", // august
                    "\u0421\u0435\u043f\u0442\u0435\u043c\u0432\u0440\u0438", // september
                    "\u041e\u043a\u0442\u043e\u043c\u0432\u0440\u0438", // october
                    "\u041d\u043e\u0435\u043c\u0432\u0440\u0438", // november
                    "\u0414\u0435\u043a\u0435\u043c\u0432\u0440\u0438", // december
                    "" // month 13 if applicable
                }
            },
            { "MonthAbbreviations",
                new String[] {
                    "I", // abb january
                    "II", // abb february
                    "III", // abb march
                    "IV", // abb april
                    "V", // abb may
                    "VI", // abb june
                    "VII", // abb july
                    "VIII", // abb august
                    "IX", // abb september
                    "X", // abb october
                    "XI", // abb november
                    "XII", // abb december
                    "" // abb month 13 if applicable
                }
            },
            { "MonthNarrows",
                new String[] {
                    "\u044f",
                    "\u0444",
                    "\u043c",
                    "\u0430",
                    "\u043c",
                    "\u044e",
                    "\u044e",
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
                    "\u041d\u0435\u0434\u0435\u043b\u044f", // Sunday
                    "\u041f\u043e\u043d\u0435\u0434\u0435\u043b\u043d\u0438\u043a", // Monday
                    "\u0412\u0442\u043e\u0440\u043d\u0438\u043a", // Tuesday
                    "\u0421\u0440\u044f\u0434\u0430", // Wednesday
                    "\u0427\u0435\u0442\u0432\u044a\u0440\u0442\u044a\u043a", // Thursday
                    "\u041f\u0435\u0442\u044a\u043a", // Friday
                    "\u0421\u044a\u0431\u043e\u0442\u0430" // Saturday
                }
            },
            { "DayAbbreviations",
                new String[] {
                    "\u041d\u0434", // abb Sunday
                    "\u041f\u043d", // abb Monday
                    "\u0412\u0442", // abb Tuesday
                    "\u0421\u0440", // abb Wednesday
                    "\u0427\u0442", // abb Thursday
                    "\u041f\u0442", // abb Friday
                    "\u0421\u0431" // abb Saturday
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
                    "\u043d.\u0435."
                }
            },
            { "short.Eras",
                new String[] {
                    "\u043f\u0440. \u043d. \u0435.",
                    "\u043e\u0442 \u043d. \u0435.",
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
                    "HH:mm:ss zzzz", // full time pattern
                    "HH:mm:ss z", // long time pattern
                    "HH:mm:ss", // medium time pattern
                    "HH:mm", // short time pattern
                }
            },
            { "DatePatterns",
                new String[] {
                    "dd MMMM y, EEEE", // full date pattern
                    "dd MMMM y", // long date pattern
                    "dd.MM.yyyy", // medium date pattern
                    "dd.MM.yy", // short date pattern
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
