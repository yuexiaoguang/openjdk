package sun.text.resources.hi;

import sun.util.resources.ParallelListResourceBundle;

/**
 * The locale elements for Hindi.
 */
public class FormatData_hi_IN extends ParallelListResourceBundle {
    /**
     * Overrides ParallelListResourceBundle
     */
    protected final Object[][] getContents() {
        return new Object[][] {
            { "MonthNames",
                new String[] {
                    "\u091c\u0928\u0935\u0930\u0940", // january
                    "\u092b\u093c\u0930\u0935\u0930\u0940", // february
                    "\u092e\u093e\u0930\u094d\u091a", // march
                    "\u0905\u092a\u094d\u0930\u0948\u0932", // april
                    "\u092e\u0908", // may
                    "\u091c\u0942\u0928", // june
                    "\u091c\u0941\u0932\u093e\u0908", // july
                    "\u0905\u0917\u0938\u094d\u0924", // august
                    "\u0938\u093f\u0924\u0902\u092c\u0930", // september
                    "\u0905\u0915\u094d\u200d\u0924\u0942\u092c\u0930", // october
                    "\u0928\u0935\u0902\u092c\u0930", // november
                    "\u0926\u093f\u0938\u0902\u092c\u0930", // december
                    "" // month 13 if applicable
                }
            },
            { "MonthAbbreviations",   // These are same as the long ones.
                new String[] {
                    "\u091c\u0928\u0935\u0930\u0940", // abb january
                    "\u092b\u093c\u0930\u0935\u0930\u0940", // abb february
                    "\u092e\u093e\u0930\u094d\u091a", // abb march
                    "\u0905\u092a\u094d\u0930\u0948\u0932", // abb april
                    "\u092e\u0908", // abb may
                    "\u091c\u0942\u0928", // abb june
                    "\u091c\u0941\u0932\u093e\u0908", // abb july
                    "\u0905\u0917\u0938\u094d\u0924", // abb august
                    "\u0938\u093f\u0924\u0902\u092c\u0930", // abb september
                    "\u0905\u0915\u094d\u200d\u0924\u0942\u092c\u0930", // abb october
                    "\u0928\u0935\u0902\u092c\u0930", // abb november
                    "\u0926\u093f\u0938\u0902\u092c\u0930", // abb december
                    "" // abb month 13 if applicable
                }
            },
            { "MonthNarrows",
                new String[] {
                    "\u091c",
                    "\u092b\u093c",
                    "\u092e\u093e",
                    "\u0905",
                    "\u092e",
                    "\u091c\u0942",
                    "\u091c\u0941",
                    "\u0905",
                    "\u0938\u093f",
                    "\u0905",
                    "\u0928",
                    "\u0926\u093f",
                    "",
                }
            },
            { "DayNames",
                new String[] {
                    "\u0930\u0935\u093f\u0935\u093e\u0930", // Sunday
                    "\u0938\u094b\u092e\u0935\u093e\u0930", // Monday
                    "\u092e\u0902\u0917\u0932\u0935\u093e\u0930", // Tuesday
                    "\u092c\u0941\u0927\u0935\u093e\u0930", // Wednesday
                    "\u0917\u0941\u0930\u0941\u0935\u093e\u0930", // Thursday
                    "\u0936\u0941\u0915\u094d\u0930\u0935\u093e\u0930", // Friday
                    "\u0936\u0928\u093f\u0935\u093e\u0930" // Saturday
                }
            },
            { "DayAbbreviations",
                new String[] {
                    "\u0930\u0935\u093f", // abb Sunday
                    "\u0938\u094b\u092e", // abb Monday
                    "\u092e\u0902\u0917\u0932", // abb Tuesday
                    "\u092c\u0941\u0927", // abb Wednesday
                    "\u0917\u0941\u0930\u0941", // abb Thursday
                    "\u0936\u0941\u0915\u094d\u0930", // abb Friday
                    "\u0936\u0928\u093f" // abb Saturday
                }
            },
            { "DayNarrows",
                new String[] {
                    "\u0930",
                    "\u0938\u094b",
                    "\u092e\u0902",
                    "\u092c\u0941",
                    "\u0917\u0941",
                    "\u0936\u0941",
                    "\u0936",
                }
            },
            { "AmPmMarkers",
                new String[] {
                    "\u092a\u0942\u0930\u094d\u0935\u093e\u0939\u094d\u0928", // am marker
                    "\u0905\u092a\u0930\u093e\u0939\u094d\u0928" // pm marker
                }
            },
            { "Eras",
                new String[] { // era strings
                    "\u0908\u0938\u093e\u092a\u0942\u0930\u094d\u0935",
                    "\u0938\u0928"
                }
            },
            { "short.Eras",
                new String[] {
                    "\u0908\u0938\u093e\u092a\u0942\u0930\u094d\u0935",
                    "\u0938\u0928",
                }
            },
            { "NumberElements",
                new String[] {
                    ".", // decimal separator
                    ",", // group (thousands) separator
                    ";", // list separator
                    "%", // percent sign
                    "\u0966", // native 0 digit
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
                    "h:mm:ss a z", // full time pattern
                    "h:mm:ss a z", // long time pattern
                    "h:mm:ss a", // medium time pattern
                    "h:mm a", // short time pattern
                }
            },
            { "DatePatterns",
                new String[] {
                    "EEEE, d MMMM, yyyy", // full date pattern
                    "d MMMM, yyyy", // long date pattern
                    "d MMM, yyyy", // medium date pattern
                    "d/M/yy", // short date pattern
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
