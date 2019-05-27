package sun.text.resources.en;

import sun.util.resources.ParallelListResourceBundle;

public class FormatData_en extends ParallelListResourceBundle {
    /**
     * Overrides ParallelListResourceBundle
     */
    protected final Object[][] getContents() {
        // This locale inherits almost everything from the root default locale.  However,
        // even if it inherited everything, we would still need this locale to exist
        // to make the resource-bundle lookup mechanism work right.  In that case, we'd
        // define this method as follows:
        //    return new Object[][] { };
        return new Object[][] {
            { "NumberPatterns",
                new String[] {
                    "#,##0.###;-#,##0.###", // decimal pattern
                    "\u00A4#,##0.00;-\u00A4#,##0.00", // currency pattern
                    "#,##0%" // percent pattern
                }
            },
            { "DateTimePatternChars", "GyMdkHmsSEDFwWahKzZ" },
        };
    }
}
