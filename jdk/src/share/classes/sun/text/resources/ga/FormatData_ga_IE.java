package sun.text.resources.ga;

import sun.util.resources.ParallelListResourceBundle;

public class FormatData_ga_IE extends ParallelListResourceBundle {
    protected final Object[][] getContents() {
        return new Object[][] {
            { "NumberPatterns",
                new String[] {
                    "#,##0.###",
                    "\u00a4#,##0.00",
                    "#,##0%",
                }
            },
            { "TimePatterns",
                new String[] {
                    "HH:mm:ss z",
                    "HH:mm:ss z",
                    "HH:mm:ss",
                    "HH:mm",
                }
            },
            { "DatePatterns",
                new String[] {
                    "EEEE d MMMM yyyy",
                    "d MMMM yyyy",
                    "d MMM yyyy",
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
