package sun.text.resources.th;

import java.util.ListResourceBundle;

public class BreakIteratorInfo_th extends ListResourceBundle {
    protected final Object[][] getContents() {
        return new Object[][] {
            // BreakIteratorClasses lists the class names to instantiate for each
            // built-in type of BreakIterator
            {"BreakIteratorClasses",
                new String[] {
                    "RuleBasedBreakIterator",  // character-break iterator class
                    "DictionaryBasedBreakIterator",  // word-break iterator class
                    "DictionaryBasedBreakIterator",  // line-break iterator class
                    "RuleBasedBreakIterator"   // sentence-break iterator class
                }
            },

            // Data filename for each break-iterator
            {"WordData", "th/WordBreakIteratorData_th"},
            {"LineData", "th/LineBreakIteratorData_th"},

            // Dictionary filename for each dictionary-based break-iterator
            {"WordDictionary", "th/thai_dict"},
            {"LineDictionary", "th/thai_dict"},
        };
    }
}
