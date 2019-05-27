package sun.text.resources.cs;

import sun.util.resources.ParallelListResourceBundle;

public class FormatData_cs_CZ extends ParallelListResourceBundle {
    /**
     * Overrides ParallelListResourceBundle
     */
    protected final Object[][] getContents() {
        return new Object[][] {
            { "NumberPatterns",
                new String[] {
                    "#,##0.##;-#,##0.##", // decimal pattern
                    "#,##0.## \u00A4;-#,##0.## \u00A4", // currency pattern
                    "#,##0%" // percent pattern
                }
            },
        };
    }
}
