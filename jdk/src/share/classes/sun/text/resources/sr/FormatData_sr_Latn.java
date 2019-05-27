package sun.text.resources.sr;

import sun.util.resources.ParallelListResourceBundle;

public class FormatData_sr_Latn extends ParallelListResourceBundle {
    protected final Object[][] getContents() {
        return new Object[][] {
            { "MonthNames",
                new String[] {
                    "januar",
                    "februar",
                    "mart",
                    "april",
                    "maj",
                    "jun",
                    "jul",
                    "avgust",
                    "septembar",
                    "oktobar",
                    "novembar",
                    "decembar",
                    "",
                }
            },
            { "MonthAbbreviations",
                new String[] {
                    "jan",
                    "feb",
                    "mar",
                    "apr",
                    "maj",
                    "jun",
                    "jul",
                    "avg",
                    "sep",
                    "okt",
                    "nov",
                    "dec",
                    "",
                }
            },
            { "MonthNarrows",
                new String[] {
                    "j",
                    "f",
                    "m",
                    "a",
                    "m",
                    "j",
                    "j",
                    "a",
                    "s",
                    "o",
                    "n",
                    "d",
                    "",
                }
            },
            { "DayNames",
                new String[] {
                    "nedelja",
                    "ponedeljak",
                    "utorak",
                    "sreda",
                    "\u010detvrtak",
                    "petak",
                    "subota",
                }
            },
            { "DayAbbreviations",
                new String[] {
                    "ned",
                    "pon",
                    "uto",
                    "sre",
                    "\u010det",
                    "pet",
                    "sub",
                }
            },
            { "DayNarrows",
                new String[] {
                    "n",
                    "p",
                    "u",
                    "s",
                    "\u010d",
                    "p",
                    "s",
                }
            },
            { "Eras",
                new String[] {
                    "p. n. e.",
                    "n. e",
                }
            },
            { "NumberPatterns",
                new String[] {
                    "#,##0.###",
                    "\u00a4\u00a0#,##0.00",
                    "#,##0%",
                }
            },
            { "NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "0",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NaN",
                }
            },
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
                    "dd. MMMM y.",
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
