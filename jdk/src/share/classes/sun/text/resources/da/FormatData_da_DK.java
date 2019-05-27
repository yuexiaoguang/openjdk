package sun.text.resources.da;

import sun.util.resources.ParallelListResourceBundle;

public class FormatData_da_DK extends ParallelListResourceBundle {
    /**
     * Overrides ParallelListResourceBundle
     */
    protected final Object[][] getContents() {
        return new Object[][] {
            { "NumberPatterns",
                new String[] {
                    "#,##0.###;-#,##0.###", // decimal pattern
                    "\u00A4 #,##0.00;\u00A4 -#,##0.00", // currency pattern
                    "#,##0%" // percent pattern
                }
            },
        };
    }
}
