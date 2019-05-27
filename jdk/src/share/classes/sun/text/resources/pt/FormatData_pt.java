package sun.text.resources.pt;

import sun.util.resources.ParallelListResourceBundle;

public class FormatData_pt extends ParallelListResourceBundle {
    /**
     * Overrides ParallelListResourceBundle
     */
    protected final Object[][] getContents() {
        return new Object[][] {
            { "MonthNames",
                new String[] {
                    "Janeiro", // january
                    "Fevereiro", // february
                    "Mar\u00e7o", // march
                    "Abril", // april
                    "Maio", // may
                    "Junho", // june
                    "Julho", // july
                    "Agosto", // august
                    "Setembro", // september
                    "Outubro", // october
                    "Novembro", // november
                    "Dezembro", // december
                    "" // month 13 if applicable
                }
            },
            { "MonthAbbreviations",
                new String[] {
                    "jan", // abb january
                    "fev", // abb february
                    "mar", // abb march
                    "abr", // abb april
                    "mai", // abb may
                    "jun", // abb june
                    "jul", // abb july
                    "ago", // abb august
                    "set", // abb september
                    "out", // abb october
                    "nov", // abb november
                    "dez", // abb december
                    "" // abb month 13 if applicable
                }
            },
            { "MonthNarrows",
                new String[] {
                    "J",
                    "F",
                    "M",
                    "A",
                    "M",
                    "J",
                    "J",
                    "A",
                    "S",
                    "O",
                    "N",
                    "D",
                    "",
                }
            },
            { "DayNames",
                new String[] {
                    "Domingo", // Sunday
                    "Segunda-feira", // Monday
                    "Ter\u00e7a-feira", // Tuesday
                    "Quarta-feira", // Wednesday
                    "Quinta-feira", // Thursday
                    "Sexta-feira", // Friday
                    "S\u00e1bado" // Saturday
                }
            },
            { "DayAbbreviations",
                new String[] {
                    "Dom", // abb Sunday
                    "Seg", // abb Monday
                    "Ter", // abb Tuesday
                    "Qua", // abb Wednesday
                    "Qui", // abb Thursday
                    "Sex", // abb Friday
                    "S\u00e1b" // abb Saturday
                }
            },
            { "DayNarrows",
                new String[] {
                    "D",
                    "S",
                    "T",
                    "Q",
                    "Q",
                    "S",
                    "S",
                }
            },
            { "long.Eras",
                new String[] {
                    "Antes de Cristo",
                    "Ano do Senhor",
                }
            },
            { "Eras",
                new String[] {
                    "a.C.",
                    "d.C.",
                }
            },
            { "NumberElements",
                new String[] {
                    ",", // decimal al separator
                    ".", // group (thousands) separator
                    ";", // list separator
                    "%", // percent sign
                    "0", // native 0 digit
                    "#", // pattern digit
                    "-", // minus sign
                    "E", // exponential
                    "\u2030", // per mille
                    "\u221e", // infinity
                    "\ufffd" // NaN
                }
            },
            { "TimePatterns",
                new String[] {
                    "HH'H'mm'm' z", // full time pattern
                    "H:mm:ss z", // long time pattern
                    "H:mm:ss", // medium time pattern
                    "H:mm", // short time pattern
                }
            },
            { "DatePatterns",
                new String[] {
                    "EEEE, d' de 'MMMM' de 'yyyy", // full date pattern
                    "d' de 'MMMM' de 'yyyy", // long date pattern
                    "d/MMM/yyyy", // medium date pattern
                    "dd-MM-yyyy", // short date pattern
                }
            },
            { "DateTimePatterns",
                new String[] {
                    "{1} {0}" // date-time pattern
                }
            },
            { "DateTimePatternChars", "GyMdkHmsSEDFwWahKzZ" },
        };
    }
}
