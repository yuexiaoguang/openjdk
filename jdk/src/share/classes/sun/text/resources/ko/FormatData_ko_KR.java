package sun.text.resources.ko;

import sun.util.resources.ParallelListResourceBundle;

public class FormatData_ko_KR extends ParallelListResourceBundle {
    /**
     * Overrides ParallelListResourceBundle
     */
    protected final Object[][] getContents() {
        return new Object[][] {
            { "NumberPatterns",
                new String[] {
                    "#,##0.###;-#,##0.###", // decimal pattern
                    "\u00A4#,##0;-\u00A4#,##0", // currency pattern
                    "#,##0%" // percent pattern
                }
            },
        };
    }
}
