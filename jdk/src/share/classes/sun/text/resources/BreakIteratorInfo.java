package sun.text.resources;

import java.util.ListResourceBundle;

public class BreakIteratorInfo extends ListResourceBundle {
    protected final Object[][] getContents() {
        return new Object[][] {
            // BreakIteratorClasses lists the class names to instantiate for each
            // built-in type of BreakIterator
            {"BreakIteratorClasses",
                new String[] {
                    "RuleBasedBreakIterator",  // character-break iterator class
                    "RuleBasedBreakIterator",  // word-break iterator class
                    "RuleBasedBreakIterator",  // line-break iterator class
                    "RuleBasedBreakIterator"   // sentence-break iterator class
                }
            },

            // Rules filename for each break-iterator
            {"CharacterData", "CharacterBreakIteratorData"},
            {"WordData",      "WordBreakIteratorData"},
            {"LineData",      "LineBreakIteratorData"},
            {"SentenceData",  "SentenceBreakIteratorData"},
        };
    }
}
