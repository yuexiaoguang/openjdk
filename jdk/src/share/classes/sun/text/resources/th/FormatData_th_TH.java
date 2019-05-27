package sun.text.resources.th;

import sun.util.resources.ParallelListResourceBundle;

public class FormatData_th_TH extends ParallelListResourceBundle {
    /**
     * Overrides ParallelListResourceBundle
     */
    protected final Object[][] getContents() {
        return new Object[][] {
            { "NumberPatterns",
                new String[] {
                    "#,##0.###;-#,##0.###", // decimal pattern
                    "\u00a4#,##0.00;\u00a4-#,##0.00", // currency pattern
                    "#,##0%" // percent pattern
                }
            },

        };
    }
}
