package sun.text.resources.ms;

import sun.util.resources.ParallelListResourceBundle;

public class FormatData_ms_MY extends ParallelListResourceBundle {
    protected final Object[][] getContents() {
        return new Object[][] {
            { "NumberPatterns",
                new String[] {
                    "#,##0.###",
                    "\u00a4#,##0.00;(\u00a4#,##0.00)",
                    "#,##0%",
                }
            },
            { "TimePatterns",
                new String[] {
                    "h:mm:ss a z",
                    "h:mm:ss a z",
                    "h:mm:ss a",
                    "h:mm",
                }
            },
            { "DatePatterns",
                new String[] {
                    "EEEE dd MMM yyyy",
                    "dd MMMM yyyy",
                    "dd MMMM yyyy",
                    "dd/MM/yyyy",
                }
            },
            { "DateTimePatterns",
                new String[] {
                    "{1} {0}",
                }
            },
        };
    }
}
