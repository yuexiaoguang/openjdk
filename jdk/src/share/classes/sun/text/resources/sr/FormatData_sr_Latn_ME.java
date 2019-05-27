package sun.text.resources.sr;

import sun.util.resources.ParallelListResourceBundle;

public class FormatData_sr_Latn_ME extends ParallelListResourceBundle {
    protected final Object[][] getContents() {
        return new Object[][] {
            { "TimePatterns",
                new String[] {
                    "HH.mm.ss zzzz",
                    "HH.mm.ss z",
                    "HH.mm.ss",
                    "HH.mm",
                }
            },
            { "DatePatterns",
                new String[] {
                    "EEEE, dd. MMMM y.",
                    "d.MM.yyyy.",
                    "dd.MM.y.",
                    "d.M.yy.",
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
