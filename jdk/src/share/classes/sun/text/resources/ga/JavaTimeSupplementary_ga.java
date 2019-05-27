package sun.text.resources.ga;

import sun.util.resources.OpenListResourceBundle;

public class JavaTimeSupplementary_ga extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        return new Object[][] {
            { "QuarterAbbreviations",
                new String[] {
                    "R1",
                    "R2",
                    "R3",
                    "R4",
                }
            },
            { "QuarterNames",
                new String[] {
                    "1\u00fa r\u00e1ithe",
                    "2\u00fa r\u00e1ithe",
                    "3\u00fa r\u00e1ithe",
                    "4\u00fa r\u00e1ithe",
                }
            },
            { "java.time.short.Eras",
                new String[] {
                    "RC",
                    "AD",
                }
            },
        };
    }
}
