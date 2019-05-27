package jdk.nashorn.internal.runtime.regexp.joni.constants;

public interface AsmConstants {
    final int THIS = 0;

    // argument indexes
    final int RANGE             = 1;
    final int SSTART            = 2;
    final int SPREV             = 3;

    // local var indexes
    final int S                 = 4;            // current index
    final int BYTES             = 5;            // string
    final int LAST_INDEX        = BYTES + 1;

    // frequently used field names (all ints)
    final String STR            = "str";
    final String END            = "end";
    final String MSA_START      = "msaStart";
    final String MSA_OPTONS     = "msaOptions";
    final String MSA_BEST_LEN   = "msaBestLen";
    final String MSA_BEST_S     = "msaBestS";
    final String MSA_BEGIN      = "msaBegin";
    final String MSA_END        = "msaEnd";

    // generated field names
    final String BITSET         = "bitset";
    final String CODERANGE      = "range";
    final String TEMPLATE       = "template";
}
