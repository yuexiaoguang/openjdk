package sun.text.resources.lt;

import sun.util.resources.ParallelListResourceBundle;

public class FormatData_lt_LT extends ParallelListResourceBundle {
    /**
     * Overrides ParallelListResourceBundle
     */
    protected final Object[][] getContents() {
        return new Object[][] {
            { "NumberPatterns",
                new String[] {
                    "##,##0.##;-##,##0.##", // decimal pattern
                    "#,##0.## \u00A4;-#,##0.## \u00A4", // currency pattern
                    "#,##%" // percent pattern
                }
            },
        };
    }
}
