package sun.text.resources.en;

import sun.util.resources.ParallelListResourceBundle;

public class FormatData_en_ZA extends ParallelListResourceBundle {
    /**
     * Overrides ParallelListResourceBundle
     */
    protected final Object[][] getContents() {
        return new Object[][] {
            { "NumberPatterns",
                new String[] {
                    "#,##0.###;-#,##0.###", // decimal pattern
                    "\u00A4 #,##0.00;\u00A4-#,##0.00", // currency pattern
                    "#,##0%" // percent pattern
                }
            },
            { "TimePatterns",
                new String[] {
                    "h:mm:ss a", // full time pattern
                    "h:mm:ss a", // long time pattern
                    "h:mm:ss a", // medium time pattern
                    "h:mm a", // short time pattern
                }
            },
            { "DatePatterns",
                new String[] {
                    "EEEE dd MMMM yyyy", // full date pattern
                    "dd MMMM yyyy", // long date pattern
                    "dd MMM yyyy", // medium date pattern
                    "yyyy/MM/dd", // short date pattern
                }
            },
            { "DateTimePatterns",
                new String[] {
                    "{1} {0}" // date-time pattern
                }
            },
        };
    }
}
